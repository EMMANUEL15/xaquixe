package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * @author Emanuel Lopez
 */
public class ModeloAutorizacion {
    private Connection  Conexion;
    private Object [][] registro;
    private String[] columnas = { "Rfc_p","Nombre","Razon_social","Direccion"};
    public void conectar(Connection con){
        this.Conexion = con;
    }
    public String insertAutorizacion(String id, String rfc){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.autorizacion(id_material, rfc_p) values(?,?)");  
                pstm.setString(1,id);
                pstm.setString(2,rfc);
                pstm.execute();
                pstm.close();
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
    public String deleteAutorizcion(String id, String rfc){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.autorizacion where id_material = ? AND rfc_p = ?"); 
                pstm.setString(1,id);
                pstm.setString(2,rfc);
                pstm.execute();
                pstm.close(); 
            }catch(SQLException e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
    
    public DefaultTableModel searchAutorizacion(String dato){
        
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.proveedor_autorizado where id_material = ?");            
    pstm.setString(1,dato);
    ResultSet resultado = pstm.executeQuery();
    ResultSetMetaData datos = pstm.getMetaData();
    //COLUMNA
    int num_Colum= datos.getColumnCount();
    //REGISTROS
    while (resultado.next()) {
        Object [] nuevo =new Object[num_Colum];
                for (int i = 2; i <= num_Colum; i++) {
                    nuevo[i-2] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
    }catch(Exception e){ System.out.println(" *-* "+e);}
     return dtm;
    } 
    public DefaultTableModel searchProveedor(String dato){
    DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.proveedor WHERE UPPER(rfc_p) like ? or UPPER(nombre) like ? or UPPER(calle) like ?");            
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
    }catch(Exception e){ }
     return dtm;
    } 
}
