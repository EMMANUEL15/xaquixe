package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * @author Emanuel Lopez
 */
public class ModeloEmpleado  {
    private Connection  Conexion;
    private Object [][] registro;
    private String[] columnas;
    /**
     * conexion de la base de datos
     * @param con- recibe una connection de la base de datos
     */
    public void conectar(Connection con){
        this.Conexion = con;
    }
    /**
     * agrega una nueva tupla en la base de datos
     * @param p- recibe un objecto de tipo Empleados 
     */
    public String insertEmpleados(Empleado  p){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.empleado (rfc_e, nombre,apellido1,apellido2, calle, numero, colonia, municipio, estado, codigo_p) values(?,?,?,?,?,?,?,?,?,?)");  
                    pstm.setString(1,p.getRfc());
                    pstm.setString(2,p.getNombre());
                    pstm.setString(3,p.getApellido1());
                    pstm.setString(4,p.getApellido2());
                    pstm.setString(5,p.getCalle());
                    pstm.setString(6,p.getNumero());
                    pstm.setString(7,p.getColonia());
                    pstm.setString(8,p.getMunicipio());
                    pstm.setString(9,p.getEstado());
                    pstm.setString(10,p.getCodigo());
            pstm.execute();
            pstm.close();  
            
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
    /**
     * elimina una nueva tupla en la base de datos
     * @param rfc- recibe un cadena para identificar la tupla a eliminar
     */
    public String deleteEmpleados (String rfc){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.empleado  where rfc_e = ?"); 
                pstm.setString(1,rfc);                   
                pstm.execute();
                pstm.close(); 
            }catch(SQLException e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
    /**
     * Actualiza un tupla en la base de datos
     * @param p- recibe un objecto de tipo Empleados 
     * @param s recibe un cadena para identificar la tupla
     */
    public String updateEmpleados (Empleado  p, String rfc){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.empleado  set rfc_e = ?, nombre= ?, apellido1= ?, apellido2= ?, calle= ?, numero= ?, colonia= ?, municipio= ?, estado= ?, codigo_p= ?  where rfc_e = ?"); 
                    pstm.setString(1,p.getRfc());
                    pstm.setString(2,p.getNombre());
                    pstm.setString(3,p.getApellido1());
                    pstm.setString(4,p.getApellido2());
                    pstm.setString(5,p.getCalle());
                    pstm.setString(6,p.getNumero());
                    pstm.setString(7,p.getColonia());
                    pstm.setString(8,p.getMunicipio());
                    pstm.setString(9,p.getEstado());
                    pstm.setString(10,p.getCodigo());
                    pstm.setString(11,rfc);
            pstm.executeUpdate();
            pstm.close();  
         }catch(SQLException e){
             resulatdo = String.valueOf(e.getMessage());
         }
        return resulatdo;
    }
    /**
     * consulta todo el contenido de la tabla
     */
    public DefaultTableModel Empleados(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.empleado ");  
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
    /**
     * busca los Empleados es
     * @param dato- recibe un cadena para buscar Empleados es
     */
    public DefaultTableModel searchEmpleados(String dato){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.empleado  WHERE UPPER(rfc_e) like ? or UPPER(nombre) like ? or UPPER(calle) like ?");            
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