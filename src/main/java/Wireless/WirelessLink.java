package Wireless;

import ch.ethz.systems.netbench.core.network.Link;

public class WirelessLink extends Link{
    long delay, bandwith;

    WirelessLink(long delay, long bandwith){
        this.bandwith = bandwith;
        this.delay = delay;
    }

    @Override
    public long getDelayNs() {
        return delay;
    }

    @Override
    public long getBandwidthBitPerNs() {
        return bandwith;
    }

    @Override
    public boolean doesNextTransmissionFail(long packetSizeBits) {
        return false;
    }
}
