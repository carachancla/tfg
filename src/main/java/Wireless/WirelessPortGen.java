package Wireless;

import ch.ethz.systems.netbench.core.log.SimulationLogger;
import ch.ethz.systems.netbench.core.network.Link;
import ch.ethz.systems.netbench.core.network.NetworkDevice;
import ch.ethz.systems.netbench.core.network.OutputPort;
import ch.ethz.systems.netbench.core.run.infrastructure.OutputPortGenerator;
import ch.ethz.systems.netbench.ext.basic.PerfectSimpleLink;
import ch.ethz.systems.netbench.ext.flowlet.IdentityFlowletIntermediary;

public class WirelessPortGen extends OutputPortGenerator {

    private CollisionDetSwitch collisionDetSwitch;

    private final long maxQueueSizeBytes;
    private final long ecnThresholdKBytes;
    boolean firstGen = true;

    public WirelessPortGen(long maxQueueSizeBytes, long ecnThresholdKBytes) {
        this.maxQueueSizeBytes = maxQueueSizeBytes;
        this.ecnThresholdKBytes = ecnThresholdKBytes;
        SimulationLogger.logInfo("Port", "WirelessPort");
    }

    @Override
    public OutputPort generate(NetworkDevice ownNetworkDevice, NetworkDevice towardsNetworkDevice, Link link) {
        if(firstGen){
            collisionDetSwitch = new CollisionDetSwitch(-1,null, new IdentityFlowletIntermediary(),link);
            firstGen = false;

        }
        Link link0 = new PerfectSimpleLink(link.getDelayNs(), 999999999);
        OutputPort r = new WirelessPort(ownNetworkDevice,towardsNetworkDevice, collisionDetSwitch,link0,maxQueueSizeBytes,ecnThresholdKBytes);
        Link link1 = new PerfectSimpleLink(0,999999999);
        if(!collisionDetSwitch.hasConnection(towardsNetworkDevice.getIdentifier())) {
            WirelessPortAbstract hiddenPort = new WirelessPortAbstract(collisionDetSwitch, towardsNetworkDevice, link1);
            collisionDetSwitch.addConnection(hiddenPort);
        }
        return r;
    }
}
