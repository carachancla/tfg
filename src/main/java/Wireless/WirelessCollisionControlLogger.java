package Wireless;

import ch.ethz.systems.netbench.core.Simulator;
import ch.ethz.systems.netbench.core.log.LogFailureException;
import ch.ethz.systems.netbench.core.log.LoggerCallback;
import ch.ethz.systems.netbench.core.log.SimulationLogger;

import java.io.BufferedWriter;
import java.io.IOException;

public class WirelessCollisionControlLogger implements LoggerCallback {

    private final int channelId;
    private boolean channelused;
    private long laststatechange;
    private final BufferedWriter wirelessWritter;
    private long usedTime;
    private int numberCollisions;
    private int packetsSent;

    public WirelessCollisionControlLogger(int channelId){

        this.channelId = channelId;
        laststatechange = 0;
        channelused = false;
        usedTime = 0;
        numberCollisions = 0;
        packetsSent = 0;

        this.wirelessWritter = SimulationLogger.getExternalWriter("channel_usage.cvs.log");
        SimulationLogger.registerCallbackBeforeClose(this);

    }

    public void logChannelUse(boolean use){
        if(use==this.channelused)throw new IllegalArgumentException("Can't change stat to the same state");

        if(!use){
            usedTime += Simulator.getCurrentTime() - laststatechange;
        }
        this.channelused = use;
        this.laststatechange = Simulator.getCurrentTime();

    }

    public void logCollision(){
        numberCollisions++;
    }

    public void logSucces(){
        packetsSent++;
    }


    @Override
    public void callBeforeClose() {

        try {
            wirelessWritter.write(channelId + "," + (float)usedTime/Simulator.getCurrentTime() + "," + numberCollisions + "," + packetsSent + '\n');
        }
        catch (IOException e){
            throw new LogFailureException(e);
        }

    }
}
