package Wireless;

import ch.ethz.systems.netbench.core.log.SimulationLogger;
import ch.ethz.systems.netbench.core.network.Link;
import ch.ethz.systems.netbench.core.network.NetworkDevice;
import ch.ethz.systems.netbench.core.network.OutputPort;
import ch.ethz.systems.netbench.core.run.infrastructure.OutputPortGenerator;
import ch.ethz.systems.netbench.ext.basic.PerfectSimpleLink;
import ch.ethz.systems.netbench.ext.flowlet.IdentityFlowletIntermediary;

import java.util.Random;

public class WirelessPortGen extends OutputPortGenerator {

    private CollisionDetSwitch[] collisionDetSwitch;
    int numChannels;
    Random random = new Random();

    private final long maxQueueSizeBytes;
    private final long ecnThresholdKBytes;
    boolean firstGen = true;
    private String accessMode;

    public WirelessPortGen(long maxQueueSizeBytes, long ecnThresholdKBytes, String accessMode, int numChannels) {
        this.maxQueueSizeBytes = maxQueueSizeBytes;
        this.ecnThresholdKBytes = ecnThresholdKBytes;
        this.accessMode = accessMode;
        this.numChannels = numChannels;
        SimulationLogger.logInfo("Port", "WirelessPort");

    }

    @Override
    public OutputPort generate(NetworkDevice ownNetworkDevice, NetworkDevice towardsNetworkDevice, Link link) {
        if(firstGen){
            collisionDetSwitch = new CollisionDetSwitch[numChannels];
            for(int i = 0; i<numChannels; i++)collisionDetSwitch[i] = new CollisionDetSwitch(-1*i,null, new IdentityFlowletIntermediary(),link);
            firstGen = false;

        }
        Link link0 = new PerfectSimpleLink(link.getDelayNs(), 999999999);
        int selectedChannel = random.nextInt(numChannels);
        OutputPort r = new WirelessPort(ownNetworkDevice,towardsNetworkDevice, collisionDetSwitch[selectedChannel],link0,maxQueueSizeBytes,ecnThresholdKBytes, accessMode);
        Link link1 = new PerfectSimpleLink(0,999999999);
        if(!collisionDetSwitch[selectedChannel].hasConnection(towardsNetworkDevice.getIdentifier())) {
            WirelessPortAbstract hiddenPort = new WirelessPortAbstract(collisionDetSwitch[selectedChannel], towardsNetworkDevice, link1);
            collisionDetSwitch[selectedChannel].addConnection(hiddenPort);
        }
        return r;
    }
}
