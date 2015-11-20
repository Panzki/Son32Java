/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32reader;

import java.util.HashMap; 
import java.util.Map; 

/**
 * This class is a model for a single Son32 channel. The Son32Reader will contian
 * a reference to all channels.
 * @author Matthias Steffen
 */
public class Son32Channel {
    /**
     * This enum represents the relation between the channel type and the
     * short value it is represented by. It is the java equivalent of the
     * TDataKind from the son32.h
     */
    public static enum ChannelKind{
        ChanOff((short)0),            /* the channel is OFF - */
        Adc((short)1),                /* a 16-bit waveform channel */
        EventFall((short)2),          /* Event times (falling edges) */
        EventRise((short)3),          /* Event times (rising edges) */
        EventBoth((short)4),          /* Event times (both edges) */
        Marker((short)5),             /* Event time plus 4 8-bit codes */
        AdcMark((short)6),            /* Marker plus Adc waveform data */
        RealMark((short)7),           /* Marker plus float numbers */
        TextMark((short)8),           /* Marker plus text string */
        RealWave((short)9);           /* waveform of float numbers */
        
        private short channelCode;
        
        /**
         * Channel code is returned by Son32Reader.SONChanKind().
         * @param channelCode 
         */
        ChannelKind(short channelCode) {
            this.channelCode = channelCode;
        }
        /*
        Create a hashmap so that we can get the constants from the enum by the
        short value.
        */
        final static Map<Short, ChannelKind> map = new HashMap<>();

        static {
           for (ChannelKind channelKind : ChannelKind.values()) {
              map.put(channelKind.channelCode, channelKind);
           }
        }
        /**
         * Get the sting description for the channel by the channel code.
         * @param channelCode
         * @return 
         */
        public static ChannelKind getByChannelCode(short channelCode) {
           return map.get(channelCode);
        }
        
        /**
         * Get the channel code from the enum constant.
         * @return 
         */
        public short getChannelCode(){
            return this.channelCode;
        }
        
        /**
         * Get the description of the channel kind.
         * @return 
         */
        public String getChannelKindDescription(){
            return this.name();
        }
    }
    
    private final Son32Reader parentReader;
    private final int channelNumber;
    private final ChannelKind channelKind;
    private final long channelDivide;
    private final long chanMaxTime;
    
    public Son32Channel(Son32Reader reader, int channelNumber,
            short channelKind, long chanDiv, long chanMaxTime){
        this.parentReader = reader;
        this.channelNumber = channelNumber;
        this.channelKind = ChannelKind.getByChannelCode(channelKind);
        this.channelDivide = chanDiv;
        this.chanMaxTime = chanMaxTime;
    }
    
    /**
     * Get the enum object that decribes the kind of this channel.
     * @return
     */
    public ChannelKind getChannelKind(){
        return this.channelKind;
    }
    
    /**
     * @return The cannel divide value.
     */
    public long getChannelDivide(){
        return this.channelDivide;
    }
    
    /**
    * This function reads contiguous waveform data from this channel 
     * between two set times.
     * @param max The maximum number of data point to be returned
     * @param sTime The strat time in clock ticks.
     * @param eTime The end time in clock ticks.
     * @return A 2d double array where [0][n] represents the x value (time)
     *         and [1][n] represents the y value (data) of the nth data point.
     */
    public double[][] getRealData(long max, long sTime, long eTime){
        return this.parentReader.SONGetRealData((short)this.channelNumber, max,
                sTime, eTime);
    }
    
    /**
     * @return The max time for this channel.
     */
    public long getChanMaxTime(){
        return this.chanMaxTime;
    }
}
