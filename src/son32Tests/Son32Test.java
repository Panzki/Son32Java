/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32Tests;

import son32reader.Son32Reader;
import son32reader.Son32Channel;
import son32Exceptions.*;

/**
 * Atm, this is the place for simple manual unit and implemenation test.
 * In the future proper testing should be added.
 * @author Matthias Steffen
 */
public class Son32Test {
    public static void main(String args[]){
        //String path = "C:\\Users\\matthias\\Documents\\NetBeansProjects\\Son32Reader\\test_data\\sample.smr";
        String path = "C:\\Users\\matthias\\Documents\\NetBeansProjects\\Son32Reader\\test_data\\chan1_1sec.smr";
        Son32Reader reader = new Son32Reader(path, 1);
        timeEpochLoading(reader);
        //System.exit(0);
        try{
            Son32Channel chan = reader.getChannel(0);
            chan.getEpoch(1);
            chan.getEpoch(chan.numberOfEpochs);
        } catch(Exception e){
            System.out.println(e);
        }
      
//        long  duration = timeEpochLoading(reader);
//        System.out.format("It took %d ms to fetch ~30sec of data.%n",duration);
    }
    
    public static void printAllChannleKinds(Son32Reader reader){
        try{
            for(int i=0;i<reader.getNumberOfChannels();i++){
                System.out.println("Channel " + i + ": " + reader.getChannel(i).getChannelKind());
            }
        } catch(NoChannelException ex){
            System.out.println(ex);
        }
    }
    
    public static void printChannelData(double[][] data){
        for(int i=0;i<data[0].length;i++){
            double time = data[0][i];
            double val = data[1][i];
            System.out.format("%.6f  %.6f%n",time,val);
        }
    }
    
    public static long timeEpochLoading(Son32Reader reader){
        try{
            Son32Channel channel = reader.getChannel(0);
            long sTime = System.nanoTime();
            //one waveform conversion takes about 0.004915s for this test file
            //the fetch ~30s we need to get 6106 data points
            //double[][] channel0Data = channel.getRealData(6106,0,channel.getChanMaxTime());
            double[][] channel0Data = channel.getRealData(10000,0,6106);
            long eTime = System.nanoTime();
            long duration = (eTime - sTime)/1000000;
            printChannelData(channel0Data);
            return duration;
            //System.out.format("It took %d ms to fetch ~30sec of data.%n",duration);
        } catch(NoChannelException ex){
            System.out.println(ex);
            System.exit(1);
            return 0;
        }
    }
}
