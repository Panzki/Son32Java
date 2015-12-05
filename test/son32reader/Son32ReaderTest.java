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
import son32Exceptions.*;

/**
 *
 * @author matthias
 */
public class Son32ReaderTest {
    
    private Son32Reader file00;
    private  Son32Reader file01;
    
    public Son32ReaderTest() {
        //String path1 = System.getProperty("user.dir") + "\\test_data\\abc.SMR";
        String path1 = "C:\\Users\\matthias\\Documents\\NetBeansProjects\\Son32Java\\test_data\\abc.smr";
        this.file00 = new Son32Reader(path1, 0);
        String path2 = System.getProperty("user.dir") + "\\test_data\\sample01.smr";
        //this.file01 = new Son32Reader(path2, 0);
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
    public void testSample00SamplingRateChannel0(){
        try{
            Son32Channel channel = this.file00.getChannel(0);
            System.out.println(channel.getSamplingRateHz());
        } catch(Exception e){}
    }

//    /**
//     * Test of SONGetRealData method, of class Son32Reader.
//     */
//    @Test
//    public void testSONGetRealData() {
//        System.out.println("SONGetRealData");
//        short chan = 0;
//        long max = 0L;
//        long sTime = 0L;
//        long eTime = 0L;
//        double[] target = null;
//        Son32Reader instance = null;
//        instance.SONGetRealData(chan, max, sTime, eTime, target);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCTFromSec method, of class Son32Reader.
//     */
//    @Test
//    public void testGetCTFromSec() {
//        System.out.println("getCTFromSec");
//        double timeInSec = 0.0;
//        Son32Reader instance = null;
//        long expResult = 0L;
//        long result = instance.getCTFromSec(timeInSec);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSecFromCT method, of class Son32Reader.
//     */
//    @Test
//    public void testGetSecFromCT() {
//        System.out.println("getSecFromCT");
//        long timeInCT = 0L;
//        Son32Reader instance = null;
//        double expResult = 0.0;
//        double result = instance.getSecFromCT(timeInCT);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNumberOfChannels method, of class Son32Reader.
//     */
//    @Test
//    public void testGetNumberOfChannels() {
//        System.out.println("getNumberOfChannels");
//        Son32Reader instance = null;
//        int expResult = 0;
//        int result = instance.getNumberOfChannels();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChannel method, of class Son32Reader.
//     */
//    @Test
//    public void testGetChannel() throws Exception {
//        System.out.println("getChannel");
//        int channelNumber = 0;
//        Son32Reader instance = null;
//        Son32Channel expResult = null;
//        Son32Channel result = instance.getChannel(channelNumber);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTimeBase method, of class Son32Reader.
//     */
//    @Test
//    public void testGetTimeBase() {
//        System.out.println("getTimeBase");
//        Son32Reader instance = null;
//        double expResult = 0.0;
//        double result = instance.getTimeBase();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsPerTime method, of class Son32Reader.
//     */
//    @Test
//    public void testGetUsPerTime() {
//        System.out.println("getUsPerTime");
//        Son32Reader instance = null;
//        short expResult = 0;
//        short result = instance.getUsPerTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
