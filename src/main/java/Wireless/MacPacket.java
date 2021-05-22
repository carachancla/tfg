package Wireless;

import ch.ethz.systems.netbench.core.network.Packet;

public class MacPacket extends Packet {

    private static final int MAC_PACKET_HEADER_BITS = 30*8;

    private Packet packetContent;
    private WirelessPort sourcePort;

    private int sourceId;
    private int destId;

    /**
     * Construct a fixed-size packet belonging to a particular flow.
     * The {@link #getDepartureTime() departure time} is set to the current simulator time.
     *
     * @param flowId  Flow identifier
     * @param sizeBit Total size of the packet in bits (must be total, as it is used by the {@link Link link}).
     */
    public MacPacket(long flowId, long sizeBit, int sourceId, int destId, WirelessPort source, Packet packetContent) {
        super(flowId, sizeBit + MAC_PACKET_HEADER_BITS);
        this.sourceId = sourceId;
        this.destId = destId;
        this.packetContent = packetContent;
        this.sourcePort = source;
    }

    public int getDestId() {
        return destId;
    }

    public int getSourceId() { return sourceId; }

    public WirelessPort getSourcePort(){return sourcePort;}

    public Packet getPacketContent(){return packetContent;}

}
