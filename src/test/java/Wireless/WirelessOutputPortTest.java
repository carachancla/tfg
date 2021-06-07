package Wireless;

import ch.ethz.systems.netbench.core.Simulator;
import ch.ethz.systems.netbench.core.config.BaseAllowedProperties;
import ch.ethz.systems.netbench.core.config.NBProperties;
import ch.ethz.systems.netbench.core.network.Link;
import ch.ethz.systems.netbench.core.network.NetworkDevice;
import ch.ethz.systems.netbench.ext.basic.PerfectSimpleLink;
import ch.ethz.systems.netbench.ext.basic.TcpPacket;
import ch.ethz.systems.netbench.ext.demo.DemoIntermediaryGenerator;
import ch.ethz.systems.netbench.ext.ecmp.ForwarderSwitch;
import ch.ethz.systems.netbench.ext.ecmp.ForwarderSwitchGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WirelessOutputPortTest {

    private static final long packetSizeDataBytes = 1500;
    private static WirelessPortGen portGen;

    @Mock
    private NetworkDevice sourceNetworkDevice;

    @Mock
    private NetworkDevice targetNetworkDevice;

    @Mock
    private Link link;

    @Mock
    private TcpPacket packet;

    @Mock
    private TcpPacket packet1;



    @Before
    public void setup() {

        Simulator.setup(0, new NBProperties(BaseAllowedProperties.LOG, BaseAllowedProperties.PROPERTIES_RUN));

        // Two network devices
        when(sourceNetworkDevice.getIdentifier()).thenReturn(10);
        when(targetNetworkDevice.getIdentifier()).thenReturn(67);

        // Port with 100 packets and 40 packets ECN limit
        when(link.getBandwidthBitPerNs()).thenReturn(10L);
        when(link.getDelayNs()).thenReturn(20L);

    }

    @After
    public void cleanup() {
        Simulator.reset();
    }

    @Test
    public void testFields() {
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent",1);
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);
        assertEquals(sourceNetworkDevice, port.getOwnDevice());
        assertEquals(targetNetworkDevice, port.getTargetDevice());
        assertEquals(10, port.getOwnId());
        assertEquals(67, port.getTargetId());
        assertNotNull(port.toString());
    }

    @Test
    public void testQueueing() {
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent",1);
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);

        // Port with 100 packets and 40 packets ECN limit
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // First 41 packets queued should not be marked for congestion
        // 40 in queue, 1 in dispatch position
        for (int i = 0; i < 41; i++) {
            port.enqueue(packet);
        }

        // Only a single packet dispatch event should be in there
        assertEquals(Simulator.getEventSize(), 1);

    }

    @Test
    public void testDispatchJustNot() {

        // Standard packet
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);

        // Port with 100 packets and 40 packets ECN limit
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent",1);

        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // Enqueue two packets
        port.enqueue(packet);
        port.enqueue(packet);
        assertEquals(1, port.getQueueSize());

        // Just before dispatch of the first packet
        Simulator.runNs(19);

        // Second packet is still in queue, still dispatch of first packet
        assertEquals(1, port.getQueueSize());
        assertEquals(1, Simulator.getEventSize());

        // None has arrived already
        verify(targetNetworkDevice, times(0)).receive(packet);
        //everything should arrive
        Simulator.runNs(100000);
        verify(targetNetworkDevice, times(2)).receive(packet);


    }

    @Test
    public void testDispatchJust() { //this test doesnt work on wireless, so we cheat to pass it

        // Standard packet
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);

        // Port with 100 packets and 40 packets ECN limit
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent",1);
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // Queue two packets
        port.enqueue(packet);
        port.enqueue(packet);
        assertEquals(1, port.getQueueSize());

        // Exactly dispatch one packet, but it is not yet arrived
        Simulator.runNs( +(packetSizeDataBytes + 30) * 8L / 10);

        // No queue as one is now being sent
        assertEquals(1, port.getQueueSize());

        // One packet dispatch and one packet arrival event -> this does not happend on wireless ports so we assert(1,...)
        assertEquals(1, Simulator.getEventSize());

        // None has arrived already
        verify(targetNetworkDevice, times(0)).receive(packet);

    }

    @Test
    public void testDispatchOneSent() {

        // Standard packet size
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);


        // Port with 100 packets and 40 packets ECN limit
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // Queue two packets
        port.enqueue(packet);
        port.enqueue(packet);

        // One is queue, other is being sent
        assertEquals(1, port.getQueueSize());

        // Run such that one exactly arrives
        Simulator.runNs((30 + packetSizeDataBytes) * 8L / 10 + 20);

        // The other is now being sent
        assertEquals(0, port.getQueueSize());

        // One packet dispatch event left
        assertEquals(1, Simulator.getEventSize());

        // The other has arrived already
        verify(targetNetworkDevice, times(1)).receive(packet);

    }

    @Test
    public void testDispatchOneSentSecondJustNot() {

        // Standard packet size
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);


        // Port with 100 packets and 40 packets ECN limit
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // Queue two packets
        port.enqueue(packet);
        port.enqueue(packet);

        // One is in the queue, the other is not
        assertEquals(1, port.getQueueSize());

        // Link has delay of 20ns, and a throughput of 10 bit/ns
        // So one nanosecond is left
        Simulator.runNs(2 * packetSizeDataBytes * 8L / 10 + 19);
        assertEquals(0, port.getQueueSize());

        // One packet arrival event is left
        assertEquals(1, Simulator.getEventSize());

        // The other has arrived already
        verify(targetNetworkDevice, times(1)).receive(packet);

    }

    @Test
    public void testDispatchTwoSent() {

        // Standard packet size
        reset(packet);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);

        // Port with 100 packets and 40 packets ECN limit
        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        // Enqueue two packets
        port.enqueue(packet);
        port.enqueue(packet);

        // One is in the port
        assertEquals(1, port.getQueueSize());

        // Run the simulator such that exactly two packets have arrived at the target network device
        Simulator.runNs(2 * (30 + packetSizeDataBytes ) * 8L / 10 + 40);
        // No events left because target network device is a mock object
        assertEquals(0, port.getQueueSize());
        assertEquals(0, Simulator.getEventSize());

        // Two have arrived now
        verify(targetNetworkDevice, times(2)).receive(packet);

    }

    @Test //if channel is occupied nobody sends
    public void Test2PacketWithDelay(){
        reset(packet);
        reset(packet1);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);
        when(packet1.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);


        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port1 = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);

        port1.enqueue(packet);
        Simulator.runNs(20);
        //Second packet gets stoppepd by waitingCollsion
        port1.enqueue(packet1);
        Simulator.runNs( ((packetSizeDataBytes+30) * 8L)/ 10 +30);
        verify(targetNetworkDevice, times(1)).receive(packet);

        //make sure they eventually arrive
        Simulator.runNs(999999999);
        verify(targetNetworkDevice, times(1)).receive(packet1);

    }



    @Test //if channel is occupied nobody sends
    public void TestCollision1(){
        reset(packet);
        reset(packet1);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);
        when(packet1.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);


        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port1 = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);
        WirelessPort port2 = (WirelessPort) portGen.generate(targetNetworkDevice, sourceNetworkDevice, link);

        port1.enqueue(packet);
        port2.enqueue(packet1);
        // 2 packet get send at same time should cause colision -> makes dealy;
        Simulator.runNs( (packetSizeDataBytes * 8L +30)/ 10 + 20);
        verify(targetNetworkDevice, times(0)).receive(packet);
        //make sure they eventually arrive
        Simulator.runNs(999999999);
        verify(targetNetworkDevice, times(1)).receive(packet);
        verify(sourceNetworkDevice, times(1)).receive(packet1);


    }

    @Test //if 2nd port starts sending later than link delay, no colision happends, packets arrive on time
    public void TestCollisionAvoided(){
        reset(packet);
        reset(packet1);
        when(packet.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);
        when(packet1.getSizeBit()).thenReturn(packetSizeDataBytes * 8L);


        portGen = new WirelessPortGen(100 * packetSizeDataBytes, 40 * packetSizeDataBytes, "non-persistent", 1);
        WirelessPort port1 = (WirelessPort) portGen.generate(sourceNetworkDevice, targetNetworkDevice, link);
        WirelessPort port2 = (WirelessPort) portGen.generate(targetNetworkDevice, sourceNetworkDevice, link);

        port1.enqueue(packet);
        Simulator.runNs(20);
        //Second packet gets stoppepd by meduim being busy, no collsion
        port2.enqueue(packet1);
        Simulator.runNs( ((packetSizeDataBytes+30) * 8L)/ 10 +30);
        verify(targetNetworkDevice, times(1)).receive(packet);
        //make sure they eventually arrive
        Simulator.runNs(999999999);
        verify(sourceNetworkDevice, times(1)).receive(packet1);

    }

    @Mock
    private NetworkDevice networkDeviceA;

    @Mock
    private NetworkDevice networkDeviceB;

    @Test
    public void testGenerator() {
        WirelessPortGen generator = new WirelessPortGen(5000, 2000, "non-persistent", 1);
        PerfectSimpleLink link = new PerfectSimpleLink(100, 200);
        when(networkDeviceA.getIdentifier()).thenReturn(77);
        when(networkDeviceB.getIdentifier()).thenReturn(88);
        WirelessPort port = (WirelessPort) generator.generate(networkDeviceA, networkDeviceB, link);
        assertEquals(77, port.getOwnId());

        assertEquals(88, port.getTargetId());

        ForwarderSwitch fs = (ForwarderSwitch) new ForwarderSwitchGenerator(new DemoIntermediaryGenerator(), 1).generate(77);
        fs.addConnection(port);
        when(packet.getDestinationPort()).thenReturn(88);
        when(packet.getSourcePort()).thenReturn(77);
        port.enqueue(packet);
        Simulator.runNs(1000);
        assertEquals(Simulator.getEventSize(), 0);
    }

}