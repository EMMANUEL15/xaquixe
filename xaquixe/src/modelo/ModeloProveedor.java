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
public class ModeloProveedor {
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
     * @param p- recibe un objecto de tipo proveedor
     */
    public String insertProveedor(Proveedor p){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.proveedor(rfc_p, nombre, razon_social, calle, numero, colonia, municipio, estado, codigo_p) values(?,?,?,?,?,?,?,?,?)");  
                    pstm.setString(1,p.getRfc());
                    pstm.setString(2,p.getNombre());
                    pstm.setString(3,p.getRazon());
                    pstm.setString(4,p.getCalle());
                    pstm.setString(5,p.getNumero());
                    pstm.setString(6,p.getColonia());
                    pstm.setString(7,p.getMunicipio());
                    pstm.setString(8,p.getEstado());
                    pstm.setString(9,p.getCodigo());
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
    public String deleteProveedor(String rfc){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.proveedor where rfc_p = ?"); 
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
     * @param p- recibe un objecto de tipo proveedor
     * @param s recibe un cadena para identificar la tupla
     */
    public String updateProveedor(Proveedor p, String rfc){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.proveedor set rfc_p = ?, nombre= ?, razon_social= ?, calle= ?, numero= ?, colonia= ?, municipio= ?, estado= ?, codigo_p= ?  where rfc_p = ?"); 
                    pstm.setString(1,p.getRfc());
                    pstm.setString(2,p.getNombre());
                    pstm.setString(3,p.getRazon());
                    pstm.setString(4,p.getCalle());
                    pstm.setString(5,p.getNumero());
                    pstm.setString(6,p.getColonia());
                    pstm.setString(7,p.getMunicipio());
                    pstm.setString(8,p.getEstado());
                    pstm.setString(9,p.getCodigo());
                    pstm.setString(10,rfc);
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
    public DefaultTableModel Proveedores(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.proveedor");  
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
     * busca los proveedores
     * @param dato- recibe un cadena para buscar proveedores
     */
    public DefaultTableModel searchProveedores(String dato){
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
    /**
     * busca los telefono de los proveedores
     * @param dato- recibe un cadena para buscar
     */
    public ArrayList<String> TelefonoProveedores(String dato){
        ArrayList<String> Telefonos = new ArrayList<String>();
        try{
            PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.tel_proveedor WHERE rfc_p = ? ");            
            pstm.setString(1,dato);
            ResultSet resultado = pstm.executeQuery();
            ResultSetMetaData datos = pstm.getMetaData();
        //REGISTROS
            while (resultado.next()) {
               Telefonos.add(resultado.getString(2));
            }
        }catch(Exception e){ }
        return Telefonos;
    }
    /**
     * busca los correo de los proveedores
     * @param dato- recibe un cadena para buscar
     */
    public ArrayList<String> CorreoProveedores(String dato){
        ArrayList<String> Correos = new ArrayList<String>();
        try{
            PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.mail_proveedor WHERE rfc_p = ? ");            
            pstm.setString(1,dato);
            ResultSet resultado = pstm.executeQuery();
            ResultSetMetaData datos = pstm.getMetaData();
        //REGISTROS
            while (resultado.next()) {
               Correos.add(resultado.getString(2));
            }
        }catch(Exception e){ }
        return Correos;
    }
}
