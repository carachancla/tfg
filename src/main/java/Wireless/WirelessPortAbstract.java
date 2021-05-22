package Wireless;

import ch.ethz.systems.netbench.core.network.Link;
import ch.ethz.systems.netbench.core.network.NetworkDevice;
import ch.ethz.systems.netbench.core.network.OutputPort;
import ch.ethz.systems.netbench.core.network.Packet;

import java.util.concurrent.LinkedBlockingQueue;

public class WirelessPortAbstract extends OutputPort {


    WirelessPortAbstract(NetworkDevice ownNetworkDevice, NetworkDevice targetNetworkDevice, Link link) {
        super(ownNetworkDevice, targetNetworkDevice, link, new LinkedBlockingQueue<>());
        disableLog();
    }

    /**
     * Enqueue the given packet.
     * Drops it if the queue is full (tail drop).
     *
     * @param packet    Packet instance
     */
    @Override
    public void enqueue(Packet packet) {
        guaranteedEnqueue(packet);

    }

}
