package xaquixe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import junit.framework.TestSuite;
/**
 * @author Emanuel Lopez
 */
public class ProductoTestSuite {
    
   public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite();

        return suite;
    }
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}