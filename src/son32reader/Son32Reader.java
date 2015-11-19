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
    private final int numberOfChannels;
    private final double timeBase;
    
    public Son32Reader(String path, int mode){
        try{
            this.fileHandle = this.SONOpenOldFile(path, mode);
        } catch(SonNoFileException|SonOutOfMemoryException|SonNoAccessException ex){
           System.out.println(ex);
        }
        this.numberOfChannels = this.SONMaxChans();
        this.timeBase = this.SONTimeBase(0.0);
    }
    
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
            SonOutOfMemoryException, SonNoAccessException{
        short fh = INSTANCE.SONOpenOldFile(path, mode);
        
        switch(fh){
            case -1: throw new SonNoFileException(path);
            case -4: throw new SonNoAccessException(path);
            case -8: throw new SonOutOfMemoryException();
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
     * Returns the base time unit of the .smr file.
     * @param newTimeBase Sets a new time base, ignored if <= 0.9
     * @return The time base unit of the .smr file.
     */
    private double SONTimeBase(double newTimeBase){
        return INSTANCE.SONTimeBase(this.fileHandle, newTimeBase);
    }
    
    /**
     * @return The number of channels for the Son32Reader instance.
     */
    public int getNumberOfChannels(){
        return this.numberOfChannels;
    }
    /**
     * @return The time base for the Son32Reader instance.
     */
    public double getTimeBase(){
        return this.timeBase;
    }
}
