package xaquixe;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import junit.framework.Test;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import junit.framework.TestSuite;

/**
 * @author equipo
 */
public class ClienteTest extends TestCase {
    
    private modelo.Cliente cliente;
    /**
     * constructor
     * @param name nombre de la prueba 
     */
    public ClienteTest(String name) {
         super(name);
          cliente = new modelo.Cliente("LOlE542659623","Pedro","Hernadez","Hernadez");
    }
    /**
     * gets. calle, numero,colonia,municipio,estado,codigo;
     */
    public void testProductoGets() {
        assertEquals("LOlE542659623",cliente.getRfc());
        assertEquals("Pedro",cliente.getNombre());
        assertEquals("Hernadez",cliente.getApellido1());
        assertEquals("Hernadez",cliente.getApellido2());
    }
    /**
     * sets. 
     */
    public void testProductoSets() {
        modelo.Cliente prueba = new modelo.Cliente();
        prueba.setRfc("MalE542659615");
        prueba.setNombre("Martin");
        prueba.setApellido1("Reyes");
        prueba.setApellido2("Garcia");
        
        assertEquals("MalE542659615", prueba.getRfc());
        assertEquals("Martin",prueba.getNombre());
        assertEquals("Reyes", prueba.getApellido1());
        assertEquals("Garcia",prueba.getApellido2());
    }
    /**
     * iniciar text
     * @return 
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(ClienteTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
