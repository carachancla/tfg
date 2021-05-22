package Wireless;

import ch.ethz.systems.netbench.core.network.Event;

public class MacDelayEvent extends Event {

    WirelessPortAbstract wirelessPort;
    MacPacket macPacket;

    public MacDelayEvent(long timeFromNowNs, MacPacket macPacket) {
        super(timeFromNowNs);
        this.macPacket = macPacket;
    }

    @Override
    public void trigger() {
        macPacket.getSourcePort().retryCollisionDispatch(macPacket);
    }
}
