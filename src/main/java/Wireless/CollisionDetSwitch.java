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
        //System.out.println(genericPacket.getFlowId() + ", recieved, collsion state: " + collisionHappened);

        MacPacket mPacket = (MacPacket) genericPacket;
        if(!waitCollisionQueue.isEmpty())collisionHappened = true;
        else logger.logChannelUse(true);
        waitPacketsCollided++;
        waitCollisionQueue.offer(mPacket);

        Simulator.registerEvent(new WaitSwitchEvent(mPacket.getSizeBit()/sendLink.getBandwidthBitPerNs(),mPacket,this));
    }


    void endOfPacketTransmission(MacPacket macPacket){
        if(!collisionHappened){// no collision, transmit packet
            //System.out.println(macPacket.getFlowId() + ", succesful transmission, collsion state: " + collisionHappened + ", " + waitCollisionQueue.size());

            this.targetIdToOutputPort.get(macPacket.getDestId()).enqueue(macPacket.getPacketContent());
            waitCollisionQueue = new LinkedBlockingQueue<>();
            waitPacketsCollided = 0;
            freeMedium();
            macPacket.getSourcePort().packetSent();
            macPacket.getSourcePort().successfulTransmission(macPacket);
            logger.logSucces();
        }
        else{// a collision happened, wait for last packet to do collision protocol
            //System.out.println(macPacket.getFlowId() + ",  a colision happened, collsion state: " + collisionHappened + ", " + waitCollisionQueue.size());
            if(waitPacketsCollided == 1) { //last packet that gets transmitted
                //System.out.println(macPacket.getFlowId() + ",  last packet on colision, collsion state: " + collisionHappened + ", " + waitCollisionQueue.size());

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
