package Wireless;

import ch.ethz.systems.netbench.core.Simulator;
import ch.ethz.systems.netbench.core.config.BaseAllowedProperties;
import ch.ethz.systems.netbench.core.config.NBProperties;
import ch.ethz.systems.netbench.core.network.NetworkDevice;
import ch.ethz.systems.netbench.ext.basic.PerfectSimpleLink;
import ch.ethz.systems.netbench.ext.demo.DemoIntermediaryGenerator;
import ch.ethz.systems.netbench.ext.ecmp.ForwarderSwitchGenerator;

public class WirelessTest
{
    public static void main(String[] args){

        WirelessPort wp1,wp2;
        NetworkDevice colisionDetSwitch;
        NetworkDevice forwarderSwitch1,forwarderSwitch2;
        ForwarderSwitchGenerator caca;

        WirelessPortGen wirelessPortGen;

        Simulator.setup(0, new NBProperties(BaseAllowedProperties.LOG, BaseAllowedProperties.PROPERTIES_RUN));
        caca = new ForwarderSwitchGenerator(new DemoIntermediaryGenerator(), 2);


        forwarderSwitch1 = caca.generate(0);
        forwarderSwitch2 = caca.generate(1);
        wirelessPortGen = new WirelessPortGen(1000,1000, "1-persistent");
        wp1 = (WirelessPort) wirelessPortGen.generate(forwarderSwitch1,forwarderSwitch2, new PerfectSimpleLink(10, 100));
        wp2 = (WirelessPort) wirelessPortGen.generate(forwarderSwitch2, forwarderSwitch1, new PerfectSimpleLink(10, 100));






    }
}
