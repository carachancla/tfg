package Wireless;

import ch.ethz.systems.netbench.core.Simulator;
import ch.ethz.systems.netbench.core.network.*;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


class CollisionDetSwitch extends NetworkDevice {

    private int waitPacketsCollided = 0;
    private Queue<MacPacket> waitCollisionQueue = new LinkedBlockingQueue<>();
    private final Link sendLink;

    private boolean collisionHappened;
    private Queue<WirelessPort> mediumRequests;

    private WirelessCollisionControlLogger logger;

    /**
     * Constructor of a network device.
     *
     * @param identifier     Network device identifier
     * @param transportLayer Transport layer instance (null, if only router and not a server)
     * @param intermediary   Flowlet intermediary instance (takes care of flowlet support)
     */
    protected CollisionDetSwitch(int identifier, TransportLayer transportLayer, Intermediary intermediary, Link sendLink) {
        super(identifier, transportLayer, intermediary);
        mediumRequests = new LinkedBlockingQueue<>();
        collisionHappened = false;
        this.sendLink = sendLink;
        logger = new WirelessCollisionControlLogger(identifier);
    }

    @Override
    public void receive(Packet genericPacket){ //wait bandwidth time before doing transnission
        MacPacket mPacket = (MacPacket) genericPacket;
        if(!waitCollisionQueue.isEmpty())collisionHappened = true;
        else logger.logChannelUse(true);
        waitPacketsCollided++;
        waitCollisionQueue.offer(mPacket);

        Simulator.registerEvent(new WaitSwitchEvent(mPacket.getSizeBit()/sendLink.getBandwidthBitPerNs(),mPacket,this));
    }


    void endOfPacketTransmission(MacPacket macPacket){
        if(!collisionHappened){// no collision, transmit packet
            this.targetIdToOutputPort.get(macPacket.getDestId()).enqueue(macPacket.getPacketContent());
            waitCollisionQueue = new LinkedBlockingQueue<>();
            waitPacketsCollided = 0;
            freeMedium();
            macPacket.getSourcePort().packetSent();
            macPacket.getSourcePort().successfulTransmission(macPacket);
            //System.out.println("succes!!!!");
            logger.logSucces();
        }
        else{// a collision happened, wait for last packet to do collision protocol
            if(waitPacketsCollided == 1) { //last packet that gets transmitted
                for (MacPacket mPacket:waitCollisionQueue) {
                        mPacket.getSourcePort().packetCollision(mPacket, false);
                    }
                //System.out.println("collsion!!!!");
                waitCollisionQueue = new LinkedBlockingQueue<>();
                freeMedium();

                logger.logCollision();
            }
            --waitPacketsCollided;
        }

    }



    @Override
    protected void receiveFromIntermediary(Packet genericPacket) {
    }

    public boolean isMediumOccupied(WirelessPort port) {
        boolean result = !(waitCollisionQueue.isEmpty());
        if(result)mediumRequests.offer(port);
        //System.out.println("Meduim req Port: " + port.getOwnId() + ", " + result);
        return result;
    }

    public void freeMedium() {
        //all ports trying to access the medium try accessing again and restart requests

        for (WirelessPort port : mediumRequests) port.meduimFreed();
        mediumRequests = new LinkedBlockingQueue<>();
        collisionHappened = false;

        logger.logChannelUse(false);
    }


}
