/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xaquixe;
import junit.framework.Test;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import junit.framework.TestSuite;

/**
 * @author equipo
 */
public class controlproductoTest extends TestCase {
    private modelo.Producto producto;
    private modelo.ModeloProducto modelo;
    private vista.panel_producto vista;
    
    Conexion c= new Conexion("xaquixe");
    public controlproductoTest(String name) {
         super(name);
    }
    /**
     * configuracion de prueba.
     */
    protected void setUp() {
        producto = new modelo.Producto("sku","item","20 x 10",5,80.00);
        modelo =   new modelo.ModeloProducto();
        modelo.conectar(c.getConexion());
    }
    /**
     * insertar un producto a la base de datos
     */
    public void testInsertPorducto()throws Exception{
        String r = modelo.insertProducto(producto);
        assertEquals("",r);
    }
    /**
     * elminar un producto a la base de datos
     */
    public void testDeletPorducto()throws Exception{
        String r = modelo.deleteProducto("sku");
        assertEquals("",r);
    }
    /**
     * editar un producto a la base de datos
     */
    public void testUpdatePorducto()throws Exception{
        modelo.Producto prueba = new modelo.Producto("sku2","item2","20 x 20",8,40.00);
        String r = modelo.updateProducto(prueba,"sku");
        assertEquals("",r);
    }
    /**
     * camos vacios
     */
    public void testCampos(){
    try{
        this.vista.setCampo1("");
        this.vista.setCampo2("");
        this.vista.setCampo3("");
        this.vista.setCampo4("");
        this.vista.setCampo5("");
        assertEquals("",this.vista.getCampo1());
        assertEquals("",this.vista.getCampo2());
        assertEquals("",this.vista.getCampo3());
        assertEquals("",this.vista.getCampo4());
        assertEquals("",this.vista.getCampo5());
    }catch(Exception e){}
    }
    /**
     * iniciar text
     * @return 
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(controlproductoTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}