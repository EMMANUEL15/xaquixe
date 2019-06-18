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
public class empleadoTest extends TestCase {
    private modelo.Empleado empleado;
    private modelo.ModeloEmpleado modelo;
    private vista.panel_empleado vista;
    
    Conexion c= new Conexion("xaquixe");
    public empleadoTest(String name) {
         super(name);
         preparacion();
    }
    /**
     * configuracion de prueba.
     */
    protected void preparacion() {
        empleado = new modelo.Empleado("LOlE542659623","Pedro","Hernadez","Hernadez","Avila","156","Jardines","Oaxaca","Oaxaca","68000");
        modelo =   new modelo.ModeloEmpleado();
        modelo.conectar(c.getConexion());
    }
    /**
     * gets.
     */
    public void testEmpleadoGets() {
        assertEquals("LOlE542659623",empleado.getRfc());
        assertEquals("Pedro",empleado.getNombre());
        assertEquals("Hernadez",empleado.getApellido1());
        assertEquals("Hernadez",empleado.getApellido2());
        assertEquals("Avila",empleado.getCalle());
        assertEquals("156",empleado.getNumero());
        assertEquals("Jardines",empleado.getColonia());
        assertEquals("Oaxaca",empleado.getMunicipio());
        assertEquals("Oaxaca",empleado.getEstado());
        assertEquals("68000",empleado.getCodigo());
    }
    /**
     
    /**
     * insertar un producto a la base de datos
     */
    public void testInsertEmpleado()throws Exception{
        String r = modelo.insertEmpleados(empleado);
        assertEquals("",r);
    }
    /**
     * elminar un producto a la base de datos
     */
    public void testDeletEmpleado()throws Exception{
        String r = modelo.deleteEmpleados("LOlE542659623");
        assertEquals("",r);
    }
    /**
     * editar un producto a la base de datos
     */
    public void testUpdateEmpleado()throws Exception{
        modelo.Empleado prueba = new modelo.Empleado("MalE542659615","Martin","Reyes","Garcia","Avila","156","Jardines","Oaxaca","Oaxaca","68000");
        String r = modelo.updateEmpleados(prueba,"LOlE542659623");
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
        this.vista.setCampo6("");
        this.vista.setCampo7("");
        this.vista.setCampo8("");
        this.vista.setCampo9("");
        assertEquals("",this.vista.getCampo1());
        assertEquals("",this.vista.getCampo2());
        assertEquals("",this.vista.getCampo3());
        assertEquals("",this.vista.getCampo4());
        assertEquals("",this.vista.getCampo5());
        assertEquals("",this.vista.getCampo6());
        assertEquals("",this.vista.getCampo7());
        assertEquals("",this.vista.getCampo8());
        assertEquals("",this.vista.getCampo9());
        assertEquals("",this.vista.getCampo10());
    }catch(Exception e){}
    }
    /**
     * iniciar text
     * @return 
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(empleadoTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}