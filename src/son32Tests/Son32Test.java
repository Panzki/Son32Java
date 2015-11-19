/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32Tests;

import son32reader.Son32Reader;
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
}
