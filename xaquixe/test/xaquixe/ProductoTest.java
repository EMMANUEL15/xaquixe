package xaquixe;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * @author equipo
 */
public class ProductoTest extends TestCase {   
    private modelo.Producto producto;
    /**
     * constructor
     * @param name nombre de la prueba 
     */
    public ProductoTest(String name) {
         super(name);
         iniciar();
    }
    /**
    * configuracion de pruba.
    */
    protected void iniciar() {
        producto = new modelo.Producto("sku","item","20 x 10",5,80.00);
    }
    /**
     * gets.
     */
    public void testProductoGets() {
        assertEquals("sku", producto.getSku());
        assertEquals("item", producto.getItem());
        assertEquals("20 x 10", producto.getMedida());
        assertEquals(5, producto.getCantidad());
        assertEquals(80.00, producto.getPrecio());
    }
    /**
     * sets. 
     */
    public void testProductoSets() {
        modelo.Producto prueba = new modelo.Producto();
        prueba.setSku("sku2");
        prueba.setItem("item2");
        prueba.setMedida("20 x 20");
        prueba.setCantidad(8);
        prueba.setPrecio(40.00);
        
        assertEquals("sku2", prueba.getSku());
        assertEquals("item2", prueba.getItem());
        assertEquals("20 x 20", prueba.getMedida());
        assertEquals(8,prueba.getCantidad());
        assertEquals(40.00,prueba.getPrecio());
    }
    /**
     * iniciar text
     * @return 
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(ProductoTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}