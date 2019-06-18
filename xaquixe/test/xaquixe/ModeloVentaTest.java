/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xaquixe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

//import junit.framework.Test;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import junit.framework.TestSuite;

/**
 *
 * @author manic
 */
public class ModeloVentaTest extends TestCase{

    modelo.ModeloVenta modeloVenta = new modelo.ModeloVenta();
    xaquixe.Conexion c = new xaquixe.Conexion("xaquixe");
    modelo.Venta ventaReal;
    modelo.Venta ventaFalso;
    modelo.DetalleVenta detalleVenta;
    String rfc_Empleado_Real = "BEGM960510UN2";
    String rfc_Empleado_Falso = "BEGM960510U";
    String rfc_Cliente_Real = "xaquixe";
    String folioVentaReal = "";
    String folioVentaFalso = "";
    String sku_real = "XA-JI-2";

    private Object[][] registro;
    private String[] columnas = {"", "", "", "", ""};

    public ModeloVentaTest(String name) {
        super(name);
        modeloVenta.conectar(c.getConexion());
    }

    /**
     * Test del metodo insertar venta en esta prueba se inserta un venta de
     * manera correcta
     */
    @Test
    public void testInsertVenta() {
        try {
            Thread.sleep(1000);
            folioVentaReal = modeloVenta.getFolio();
            ventaReal = new modelo.Venta(folioVentaReal, rfc_Empleado_Real, rfc_Cliente_Real, 10);
            String resultado = modeloVenta.insertVenta(ventaReal);
            String resultadoEsperado = "";
            assertEquals(resultadoEsperado, resultado);
        } catch (InterruptedException ex) {
            Logger.getLogger(ModeloVentaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test del metodo insertarVenta en esta prueba se insertara un venta
     * incorrecta
     */
    @Test
    public void testInsertVentaFail() {
        try {
            Thread.sleep(1000);
            folioVentaFalso = modeloVenta.getFolio();
            ventaFalso = new modelo.Venta(folioVentaFalso, rfc_Empleado_Falso, rfc_Cliente_Real, 10);
            String resultado = modeloVenta.insertVenta(ventaFalso);
            String resultadoEsperado = "";
            assertNotEquals(resultadoEsperado, resultado);
        } catch (InterruptedException ex) {
            // Logger.getLogger(ModeloVentaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test insertar Detalle venta; en esta prueba se inserta el detaller venta
     * de forma correcta
     */
    @Test
    public void testInsertDetalleVenta() {
        try {
            Thread.sleep(2000);

            folioVentaReal = modeloVenta.getFolio();
            ventaReal = new modelo.Venta(folioVentaReal, rfc_Empleado_Real, rfc_Cliente_Real, 10);
            String resultado = modeloVenta.insertVenta(ventaReal);
            String resultadoEsperado = "";
            assertEquals(resultadoEsperado, resultado);

            Object[][] registros = {{sku_real, "a", (Object) 5, (Object) 200.00, (Object) 1000.00}};
            DefaultTableModel dtm = new DefaultTableModel(registros, columnas);

            String resultadoDetalle = modeloVenta.insertDetalleVenta(ventaReal, dtm);
            String resultadoEsperadoDetalle = "";
            assertEquals(resultadoEsperadoDetalle, resultadoDetalle);

        } catch (InterruptedException ex) {
            Logger.getLogger(ModeloVentaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test uctualizar cantidad del productos al realizar una venta en esta
     * prueba hace la actualizacion de forma correcta esperando que exista
     * ningun error
     */
    @Test
    public void testUpdateProducto() {
        Object[][] registros = {{sku_real, "a", (Object) 2, (Object) 200.00, (Object) 1000.00}};
        DefaultTableModel dtm = new DefaultTableModel(registros, columnas);

        String resultadoEsperado = "";
        String resultado = modeloVenta.updateProducto(dtm);

        assertEquals(resultadoEsperado, resultado);
    }

    /**
     * Test actualizar cantidad de productos al realizar una venta 
     * en esta prueba hace la actualizacion de forma incorrecta esperando que exista un error
     * 
     */
    @Test(expected = NumberFormatException.class)
    public void testUpdateProductoFail() {

        Object[][] registros = {{"XA-SH-NA", "a", (Object)"fd", (Object) 200.00, (Object) 1000.00}};
        DefaultTableModel dtm = new DefaultTableModel(registros, columnas);

        String resultadoEsperado = "";
        String resultado = modeloVenta.updateProducto(dtm);

        //assertNotEquals(resultadoEsperado, resultado);
    }

    /**
     * Test buscar imagen del producto
     * en esta prueba se pasa un sku correcto pero que no tiene registro de una imagen
     * se espera que que el sesultado de la busqueda no tenga exito
     */
    @Test
    public void testSearchImage() {
        String resultadoEsperado="imagenes/NoFound.png";
        String resultado=modeloVenta.searchImage(sku_real);
        assertEquals(resultadoEsperado,resultado);
    }

    /**
     * Test of getFolio method, of class ModeloVenta.
     */
    @Test
    public void testGetFolio() {
        java.util.Date date = new java.util.Date();
        DateFormat hourdateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String folioEsperdado = hourdateFormat.format(date).toString();
        String folio = modeloVenta.getFolio();
        assertEquals(folioEsperdado, folio);

    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite(ModeloVentaTest.class);
        return suite;
    }
    /**
     * Runs the test case.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}