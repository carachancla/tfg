package Wireless;

import ch.ethz.systems.netbench.core.network.Event;

public class WaitSwitchEvent extends Event {

    CollisionDetSwitch collisionDetSwitch;
    MacPacket macPacket;

    public WaitSwitchEvent(long timeFromNow, MacPacket macPacket, CollisionDetSwitch collisionDetSwitch){
        super(timeFromNow);
        this.collisionDetSwitch = collisionDetSwitch;
        this.macPacket = macPacket;
    }


    @Override
    public void trigger() {
        collisionDetSwitch.endOfPacketTransmission(macPacket);
    }
}
