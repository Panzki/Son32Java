/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32reader;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;

/**
 *This interface represents the "map" between the son32.cll (c code) and the
 * Son32Reader (java code). All used methodes are declared here according to their
 * signature in the son32.h header file. The interface can not be implemented, because
 * this would force to override the methodes.
 * @author Matthias Steffen
 */
public interface Son32JavaInterface extends Library{
    /**
     * Create a Singelton instance of the interface. This instance will be used
     * to make all function calls.
     */
    public Son32JavaInterface INSTANCE = (Son32JavaInterface)Native.loadLibrary(
            "C:\\\\SON Library\\son32.dll", Son32JavaInterface.class);
    
     /**
     * Opens a .smr file.
     * @param path Path to the .smr file
     * @param mode File open mode. 0 write/read 1 read 2 attempt write/read
     * @return fh Returns a file decriptor. If an error occured this is negative.
     */
    short SONOpenOldFile(String path, int mode);
    
    /**
     * Returns the number of channels for the .smf file. This can be between
     * 32 and 451.
     * @param fh The file handle to the .smr file
     * @return The number of channels for the .smr file.
     */
    int SONMaxChans(short fh);
    
    /**
     * Returns the kind of the given chanel as a short. The mapping between the
     * short value and the string description of the chanel takes place in a 
     * separate enum.
     * @param fh The file handle to the .smr file.
     * @param chan The number of the channel (counting starts with 0).
     * @return The code for the channel kind
     */
    short SONChanKind(short fh, short chan);
    
    /**
     *  This function gets and/or sets the base time units for the file (default
     * is 1.0E-6 seconds).
     * @param fh The file handle to the .smr file
     * @param dTB New value for the time base, ignored if <=0.0
     * @return The base time unit of the .smr file.
     */
    double SONTimeBase(short fh, double dTB);

    /**
     * This function returns the file clock tick interval in base time units as defined by 
     * SONTimeBase()
     * @param fh File descriptor for the .smr file.
     * @return The number of base time units in the clock tick interval.
     */
    short SONGetusPerTime(short fh);
    
    /**
     * Returns  the interval, in clock ticks, between waveform conversions on
     * this channel. If this is not a waveform channel, then the return 
     * value is 1. 
     * @param fh The file descriptor for the .smr file
     * @param chan The number of the channel (counting starts with 0). 
     * @return Interval for waveform conversion in clock ticks.
     */
    NativeLong SONChanDivide(short fh, short chan);
}
