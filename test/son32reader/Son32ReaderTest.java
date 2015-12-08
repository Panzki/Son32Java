/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package son32reader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import son32java.son32Exceptions.*;
import son32java.son32reader.Son32Reader;
import son32java.son32reader.Son32Channel;

/**
 *
 * @author matthias
 */
public class Son32ReaderTest {
    
    private final Son32Reader file00;
    private final Son32Reader file01;
    
    public Son32ReaderTest() {
        String path1 = System.getProperty("user.dir") + "\\test_data\\sample00.smr";
        this.file00 = new Son32Reader(path1, 1);
        String path2 = System.getProperty("user.dir") + "\\test_data\\sample01.smr";
        this.file01 = new Son32Reader(path2, 1);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSample00TimeBase(){
        double timeBase = this.file00.getTimeBase();
        double expectedValue = 1e-6;
        assertEquals(expectedValue, timeBase, 0);
    }
    
    @Test
    public void testSample01TimeBase(){
        double timeBase = this.file01.getTimeBase();
        double expectedValue = 1e-6;
        assertEquals(expectedValue, timeBase, 0);
    }
    
    @Test
    public void testSample00usPerTime(){
        short usPerTime = this.file00.getUsPerTime();
        short expectedValue = (short)27;
        assertEquals(expectedValue, usPerTime);
    }
    
    @Test
    public void testSample01usPerTime(){
        short usPerTime = this.file01.getUsPerTime();
        short expectedValue = (short)2000;
        assertEquals(expectedValue, usPerTime);
    }
    
    @Test
    public void testSample00SamplingRateChannel0(){
        try{
            Son32Channel channel = this.file00.getChannel(0);
            double sampleRate = (double)Math.round(channel.getSamplingRateHz()*100)/100;
            double expectedValue = 203.50;
            assertEquals(expectedValue, sampleRate, 0);
        } catch(Exception e){
            System.out.println(e);
            fail();
        }
    }
    
    @Test
    public void testSample01SamplingRateChannel0(){
        try{
            Son32Channel channel = this.file01.getChannel(0);
            double sampleRate = (double)Math.round(channel.getSamplingRateHz()*100)/100;
            double expectedValue = 500;
            assertEquals(expectedValue, sampleRate, 0);
        } catch(Exception e){
            System.out.println(e);
            fail();
        }
    }
    
    @Test
    public void testSample00Channel5GetRealDataByDP(){
        try{
            Son32Channel channel = this.file00.getChannel(5);
            double[] target = new double[10];
            channel.getRealDataByDP(0, target);
            double[] expectedValue = new double[10];
            expectedValue[0] = 0.029296875;
            expectedValue[1] = 0.025634765625;
            expectedValue[2] = 0.0146484375;
            expectedValue[3] = 0.03662109375;
            expectedValue[4] = 0.03662109375;
            expectedValue[5] = 0.02197265625;
            expectedValue[6] = 0.02197265625;
            expectedValue[7] = 0.03662109375;
            expectedValue[8] = 0.03662109375;
            expectedValue[9] = 0.02197265625;
 
            assertArrayEquals(expectedValue, target, 0);        
        } catch(Exception e){
            System.out.println(e);
            fail();
        }
    }
    
    @Test
    public void testSample01Channel3GetRealDataByTime(){
        try{
            Son32Channel channel = this.file01.getChannel(3);
            int arraySize = channel.calculateArraySizeByTime(0.01);
            double[] target = new double[arraySize];
            channel.getRealDataByTime(30, 30.01, target);
            double[] expectedValue = new double[arraySize];
            expectedValue[0] = -5835.4384765625;
            expectedValue[1] = -5849.02294921875;
            expectedValue[2] = -5836.63720703125;
            expectedValue[3] = -5841.431640625;
            expectedValue[4] = -5831.04345703125;
            
            assertArrayEquals(expectedValue, target, 0);
        } catch(Exception e){
            System.out.println(e);
            fail();
        } 
    }
    
    @Test
    public void testSample00Channel2getChannelTitle(){
        try{
            Son32Channel channel = this.file00.getChannel(2);
            String title = channel.getChannelTitle();
            String expected = "EEG1_1";
            assertEquals(expected, title);
        } catch(NoChannelException e){
            System.out.println(e);
            fail();
        }
    }
    
    @Test
    public void testSample01Channel5getChannelTitle(){
        try{
            Son32Channel channel = this.file01.getChannel(5);
            String title = channel.getChannelTitle();
            String expected = "EMG";
            assertEquals(expected, title);
        } catch(NoChannelException e){
            System.out.println(e);
            fail();
        }
    }
}
