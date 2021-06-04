package Wireless;

import ch.ethz.systems.netbench.core.Simulator;
import ch.ethz.systems.netbench.core.log.SimulationLogger;
import ch.ethz.systems.netbench.core.network.*;
import ch.ethz.systems.netbench.ext.basic.IpHeader;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class WirelessPort extends OutputPort {

    private final long ecnThresholdKBits;
    private final long maxQueueSizeBits;

    private boolean waitingMedium = false;
    private boolean waitingCollision = false;
    private Packet waitingMediumPacket;
    private Packet waitingCollisionPacket;

    private int collisionBackoffLevel = 1;

    NetworkDevice realTargetDevice;

    String accesMode;


    WirelessPort(NetworkDevice ownNetworkDevice, NetworkDevice targetNetworkDevice, CollisionDetSwitch collisionDetection, Link link, long maxQueueSizeBytes, long ecnThresholdKBytes, String accesMode) {
        super(ownNetworkDevice, targetNetworkDevice, link, new LinkedBlockingQueue<>());
        this.maxQueueSizeBits = maxQueueSizeBytes * 8L;
        this.ecnThresholdKBits = ecnThresholdKBytes * 8L;

        this.realTargetDevice = targetNetworkDevice;
        change_target(collisionDetection);
        this.accesMode = accesMode;

    }

    /**
     * Enqueue the given packet.
     * Drops it if the queue is full (tail drop).
     *
     * @param packet    Packet instance
     */
    @Override
    public void enqueue(Packet packet) {
        enqueue(packet,this.getTargetId());
    }

    // this to allow multiple targets on the port, targetId becomes unused.
    /**
     * Enqueue the given packet to wanted destiny.
     * Drops it if the queue is full (tail drop).
     *
     * @param packet    Packet instance
     * @param destId    Wireless receiver destiny
     */
    public void enqueue(Packet packet, int destId) {
        // Convert to IP packet
        IpHeader ipHeader = (IpHeader) packet;
        MacPacket macPacket = new MacPacket(packet.getFlowId(), packet.getSizeBit(), this.getOwnDevice().getIdentifier(), destId, this, packet);

        // Mark congestion flag if size of the queue is too big
        if (getBufferOccupiedBits() >= ecnThresholdKBits) {
            ipHeader.markCongestionEncountered();
        }


        // Tail-drop enqueue
        if (getBufferOccupiedBits() + ipHeader.getSizeBit() <= maxQueueSizeBits) {
            System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " guaranteed enqueued packet (" + getQueueSize() + ") + sending? " + waitingCollision + " waiting Medium? " + ((CollisionDetSwitch)super.getTargetDevice()).isMediumOccupied(this));
            guaranteedEnqueue(macPacket);
        } else {
            SimulationLogger.increaseStatisticCounter("PACKETS_DROPPED");
            if (ipHeader.getSourceId() == this.getOwnId()) {
                SimulationLogger.increaseStatisticCounter("PACKETS_DROPPED_AT_SOURCE");
                System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Dropping packet");

            }
        }

    }

    @Override
    protected void dispatch(Packet packet){ // dispatching new packets
        System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Dispatching");
        CollisionDetSwitch detSwitch = (CollisionDetSwitch)super.getTargetDevice();
        if(waitingCollision){ //wait til PacketSent()
            waitingCollisionPacket = packet;
        }
        else if (!detSwitch.isMediumOccupied(this)){ //send packet start waiting for its collision
            System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Sending packet" + " at " + Simulator.getCurrentTime());
            waitingMediumPacket = null; // we sent the packet
            waitingCollisionPacket = null;
            waitingMedium = false;
            waitingCollision = true;
            super.dispatch(packet);
        }
        else { // wait for medium to be free, then send
            busyMedium(packet);
        }
    }

    public void retryCollisionDispatch(Packet packet) { // collision happened retry sending
        CollisionDetSwitch detSwitch = (CollisionDetSwitch) super.getTargetDevice();
        if (!detSwitch.isMediumOccupied(this) & !link.doesNextTransmissionFail(packet.getSizeBit())) {
            Simulator.registerEvent(
                    new PacketArrivalEvent(
                            link.getDelayNs(),
                            packet,
                            targetNetworkDevice
                    )
            );
            waitingMedium = false;
            waitingMediumPacket = null;
        }
        else { //medium is occupied wait till free medium
            busyMedium(packet);
        }
    }

    private void busyMedium(Packet packet){
        switch (accesMode){
            case "1-persistent":
                waitingMedium = true;
                waitingMediumPacket = packet;
                break;
            case "non-persistent":
                packetCollision((MacPacket) packet, true);
                //System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Waiting medium packet");
                break;
            default:
                throw new IllegalArgumentException("wireless port access mode not permitted");
        }
    }

    void meduimFreed(){
        //applies on 1-persistent only
        if(waitingCollision & waitingMedium){
            retryCollisionDispatch(waitingMediumPacket);
        }
        if(waitingMedium) {
            waitingMedium = false;
            this.dispatch(waitingMediumPacket);
        }
    }

    void packetSent(){///check if packet waiting
        waitingCollision = false;
        System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Successful transmission, more to send? " + waitingCollision + " queue: " + getQueueSize());
        if(waitingCollisionPacket != null)dispatch(waitingCollisionPacket);
        else if(getQueueSize()!=0){
            Packet packetFromQ = getQueue().poll();
            decreaseBufferOccupiedBits(packetFromQ.getSizeBit());
            dispatch(packetFromQ);
        }
        else isSending = false; // if there is nothing to send we can mark the link as free
    }



    @Override
    public NetworkDevice getTargetDevice(){
        //Port logger calls this method on super creator, realTargetDevice IS NOT SET YET
        if(realTargetDevice == null) return super.getTargetDevice();
        return realTargetDevice;
    }

    @Override
    public int getTargetId() {
        //Port logger calls this method on super creator, realTargetDevice IS NOT SET YET
        if(realTargetDevice == null)return super.getTargetId();
        return realTargetDevice.getIdentifier();
    }

    @Override
    public int getQueueSize(){ //add waiting packet if there is one
        if(waitingCollisionPacket != null)return super.getQueueSize() + 1;
        return super.getQueueSize();
    }


    // collision handling failure
    protected void packetCollision(MacPacket mPacket, boolean waitMedium){
        int backoffArc = (int) Math.pow(2, collisionBackoffLevel);
        if(!waitMedium) {
            if (collisionBackoffLevel < 30) ++collisionBackoffLevel; // avoid  integer overflow
        }
        else {
            backoffArc=Math.max(5, backoffArc);
        }
        int roundTripTime =(int) mPacket.getSourcePort().getLink().getDelayNs();
        int delay = (new Random().nextInt(backoffArc + 1) + 1) * roundTripTime;
        //make sourcePort resend packet after a backoff delay
        Simulator.registerEvent(new MacDelayEvent(delay, mPacket));
        //System.out.println(this.getOwnDevice().getIdentifier() + " -> " + this.getTargetId() + " Retry sending after" + delay);

    }

    // collision handling success
    protected void successfulTransmission(MacPacket macPacket){
        collisionBackoffLevel = 1;
    }



}