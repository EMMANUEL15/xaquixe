/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */
public class ModeloVerCotizacion {
    
    private Connection  Conexion;
    private Object [][] registro;
    private String[] columnas;
    private String imagen = "imagenes/NoFound.png";
    /**
     * conexion de la base de datos
     * @param con- recibe una connection de la base de datos
     */
    public void conectar(Connection con){
        this.Conexion = con;
    }
    
    
    public DefaultTableModel Cotizacion(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.Cotizacion");  
        ResultSet resultado = pstm.executeQuery();
        ResultSetMetaData datos = pstm.getMetaData();
    
        int num_Colum= datos.getColumnCount();
    //COLUMNAS
        for (int i = 1; i <= num_Colum; i++ ) {
            dtm.addColumn(datos.getColumnName(i));
        }
    //REGISTROS
        while (resultado.next()) {
            Object [] nuevo =new Object[num_Colum];
                for (int i = 1; i <= num_Colum; i++) {
                    nuevo[i-1] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
            //acrualiza los datas de la tabla
    }catch(Exception e){}
        return dtm;
    }
    
    public DefaultTableModel detalleCotizacion(String folio_venta){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.detalle_cotizacion where num_cotizacion= ?");  
        pstm.setString(1,folio_venta);
        ResultSet resultado = pstm.executeQuery();
        ResultSetMetaData datos = pstm.getMetaData();
    
        int num_Colum= datos.getColumnCount();
    //COLUMNAS
        for (int i = 1; i <= num_Colum; i++ ) {
            dtm.addColumn(datos.getColumnName(i));
        }
    //REGISTROS
        while (resultado.next()) {
            Object [] nuevo =new Object[num_Colum];
                for (int i = 1; i <= num_Colum; i++) {
                    nuevo[i-1] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
            //acrualiza los datas de la tabla
    }catch(Exception e){}
        return dtm;
    }
    
    public DefaultTableModel searchCotizacion(String dato){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.cotizacion WHERE UPPER(num_cotizacion) like ? or UPPER(rfc_e) like ? or UPPER(rfc_c) like ?");            
    pstm.setString(1,"%"+dato.toUpperCase()+"%");
    pstm.setString(2,"%"+dato.toUpperCase()+"%");
    pstm.setString(3,"%"+dato.toUpperCase()+"%");
    ResultSet resultado = pstm.executeQuery();
    ResultSetMetaData datos = pstm.getMetaData();
    //COLUMNA
    int num_Colum= datos.getColumnCount();
    for (int i = 1; i <= num_Colum; i++ ) {
        dtm.addColumn(datos.getColumnName(i));
    }
    //REGISTROS
    while (resultado.next()) {
        Object [] nuevo =new Object[num_Colum];
                for (int i = 1; i <= num_Colum; i++) {
                    nuevo[i-1] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
    
    System.out.println(resultado.toString());
    }catch(Exception e){ System.out.println(e.getMessage()); }
   
     return dtm;
     
    }
}
