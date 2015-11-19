/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32Tests;

import son32reader.Son32Reader;
import son32reader.Son32Channel;

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
    }
    
//    public static void printAllChannleKinds(Son32Reader reader){
//        int maxChans = reader.SONMaxChans();
//        Son32Channel[] channels = new Son32Channel[maxChans];
//        for(short i=0;i<maxChans;i++){
//            channels[i] = new Son32Channel(reader.SONChanKind(i));
//            short channelCode = channels[i].getChannelKind().getChannelCode();
//            String channelDes = channels[i].getChannelKind().getChannelKindDescription();
//            System.out.println("Channel: "+(i+1)+" Type: "+channelDes+" TypeNumber: "+channelCode);
//        }
//    }
}
