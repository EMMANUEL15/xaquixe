/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestSuite;

/**
 *
 * @author Emanuel Lopez
 */
public class ProductoTest {
    
   public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite();

        return suite;
    }
    /*
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
    }*/

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
