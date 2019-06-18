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
import static org.junit.Assert.assertNotEquals;

import static org.junit.Assert.*;
//import org.junit.Test;

/**
 * @author equipo
 */
public class controlclienteTest extends TestCase {
    private modelo.Cliente cliente;
    private modelo.ModeloCliente modelo;
    private vista.panel_cliente vista;
    
    Conexion c= new Conexion("xaquixe");
    public controlclienteTest(String name) {
         super(name);
         preparacion();
    }
    /**
     * configuracion de prueba.
     */
    protected void preparacion(){
        cliente = new modelo.Cliente("LOlE542659623","Pedro","Hernadez","Hernadez");
        modelo =   new modelo.ModeloCliente();
        modelo.conectar(c.getConexion());
    }
    /**
     * insertar un producto a la base de datos
     */
    
    public void testInsertcliente(){
        String r = modelo.insertCliente(cliente);
        assertEquals("",r);
    }
    /**
     * elminar un producto a la base de datos
     */
    
    public void testDeletcliente()throws Exception{
        String r = modelo.deleteCliente("LOlE542659623");
        assertEquals("",r);
    }
    /**
     * editar un producto a la base de datos
     */
    public void testUpdatecliente()throws Exception{
        
        modelo.Cliente prueba = new modelo.Cliente("MalE542659615","Martin","Reyes","Garcia");
        String r = modelo.updateCliente(prueba,"LOlE542659623");
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
        assertEquals("",this.vista.getCampo1());
        assertEquals("",this.vista.getCampo2());
        assertEquals("",this.vista.getCampo3());
        assertEquals("",this.vista.getCampo4());
    }catch(Exception e){}
    }
    /**
     * iniciar text
     * @return 
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(controlclienteTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}