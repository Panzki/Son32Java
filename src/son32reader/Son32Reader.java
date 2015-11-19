/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32reader;

import son32Exceptions.*;

/**
 * This class will contain an instance of the Son32JavaInterface, which contains
 * all the son32.dll functions in abstract form. The interface maps the function
 * signature to the actual implementation in the C code.
 * One of the reasons, why this class exists is to detect errros. The C functions
 * just return int error codes. In this class the methodes will check for this
 * error codes and throw the corresponding java exceptions.
 * @author Matthias steffen
 */
public final class Son32Reader {
    private final static Son32JavaInterface INSTANCE = Son32JavaInterface.INSTANCE;
    private short fileHandle;
    private final Son32Channel[] channels;
    private final int numberOfChannels;
    private final double timeBase;
    private final short usPerTime;
    
    public Son32Reader(String path, int mode){
        try{
            this.fileHandle = this.SONOpenOldFile(path, mode);
            
        } catch(SonNoFileException|SonOutOfMemoryException|SonNoAccessException
                |SonReadOnlyException ex){
           System.out.println(ex);
           System.exit(1);
        }
        this.numberOfChannels = this.SONMaxChans();
        this.channels = new Son32Channel[this.numberOfChannels];
        this.initializeAllChannels();
        this.timeBase = this.SONTimeBase(0.0);
        this.usPerTime = this.SONGetusPerTime(this.fileHandle);
    }
    
    /**
     * This mehode is used to perform a setup for all channels of the .smr file.
     * The channel objects a stored in an array. Each channel stores his own
     * channel specific informations and a reference to its parent Son32Reader
     * instance.
     */
    private void initializeAllChannels(){
        for(int i=0;i<this.numberOfChannels;i++){
            short chanKind = this.SONChanKind((short)i);
            long chanDiv = this.SONChanDivide(this.fileHandle, (short)i);
            this.channels[i] = new Son32Channel(this, chanKind, chanDiv);
        }
    }
    
    //*********************Methodes of the Son32 library************************
     /**
     * Opens a .smr file.
     * @param path Path to the .smr file
     * @param mode File open mode. 0 write/read 1 read 2 attempt write/read
     * @throws SonNoFileException
     * @throws SonOutOfMemoryException
     * @throws SonNoAccessException
     * @return fh Returns a file decriptor. If an error occured this is negative.
     */
    private short SONOpenOldFile(String path, int mode) throws SonNoFileException,
            SonOutOfMemoryException, SonNoAccessException, SonReadOnlyException{
        short fh = INSTANCE.SONOpenOldFile(path, mode);
        
        switch(fh){
            case -1: throw new SonNoFileException(path);
            case -4: throw new SonNoAccessException(path);
            case -8: throw new SonOutOfMemoryException();
            case -21: throw new SonReadOnlyException(path);
        }
        return fh;
    }
    
    /**
     * Returns the number of chancels for this .smr file.
     * @return maxChannels The number of channels for the .smr file.
     */
    private int SONMaxChans(){
        return INSTANCE.SONMaxChans(this.fileHandle);
    }
    
    /**
     * Returns the chanel kind for the given chanel, of this .smr file.
     * @param chan The channel number (counting starts with 0).
     * @return The channel kind code for the channel. 
     */
    private short SONChanKind(short chan){
        return INSTANCE.SONChanKind(this.fileHandle, chan);
    }
    
    /**
     * Returns  the interval, in clock ticks, between waveform conversions on
     * this channel. If this is not a waveform channel, then the return 
     * value is 1. 
     * This function takes care of the mapping between the C long type (32bit)
     * and the Java long type (64bit).
     * @param fh The file descriptor for the .smr file
     * @param chan The number of the channel (counting starts with 0). 
     * @return Interval for waveform conversion in clock ticks.
     */
    private long SONChanDivide(short fh, short chan){
        return INSTANCE.SONChanDivide(fh, chan).longValue();
    }
    
    /**
     * Returns the base time unit of the .smr file.
     * @param newTimeBase Sets a new time base, ignored if <= 0.9
     * @return The time base unit of the .smr file.
     */
    private double SONTimeBase(double newTimeBase){
        return INSTANCE.SONTimeBase(this.fileHandle, newTimeBase);
    }
    
    /**
     * This function returns the file clock tick interval in base time units as defined by 
     * SONTimeBase()
     * @param fh File descriptor for the .smr file.
     * @return The number of base time units in the clock tick interval.
     */
    private short SONGetusPerTime(short fh){
        return INSTANCE.SONGetusPerTime(fh);
    }
    
    
    //*******************Getter methodes for the private fields*****************
    /**
     * @return The number of channels for the Son32Reader instance.
     */
    public int getNumberOfChannels(){
        return this.numberOfChannels;
    }
    
    /**
     * Returns a channel from the .smr file by the given channel number.
     * Note: Counting starts witch channel number 0 !
     * @param channelNumber
     * @return Returns one channel of the .smr file.
     * @throws NoChannelException 
     */
    public Son32Channel getChannel(int channelNumber) throws NoChannelException{
        if(channelNumber > this.numberOfChannels){
            throw new NoChannelException(channelNumber);
        } else{
            return this.channels[channelNumber];
        }
    }
    /**
     * @return The time base for the Son32Reader instance.
     */
    public double getTimeBase(){
        return this.timeBase;
    }
    
    /**
     * @return The number of time base units in the clock tick interval.
     */
    public short getUsPerTime(){
        return this.usPerTime;
    }
}
