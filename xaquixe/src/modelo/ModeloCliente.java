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
public class ModeloCliente {
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
     * @param p- recibe un objecto de tipo client
     */
    public String insertCliente(Cliente c){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.cliente(rfc_c, nombre, apellido1, apellido2) values(?,?,?,?)");  
                    pstm.setString(1,c.getRfc());
                    pstm.setString(2,c.getNombre());
                    pstm.setString(3,c.getApellido1());
                    pstm.setString(4,c.getApellido2());
            pstm.execute();
            pstm.close();  
            
        }catch(Exception e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
    /**
     * elimina una nueva tupla en la base de datos
     * @param rfc- recibe un cadena para identificar la tupla a eliminar
     */
    public String deleteCliente(String rfc){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.cliente where rfc_c = ?"); 
                pstm.setString(1,rfc);                   
                pstm.execute();
                pstm.close(); 
            }catch(Exception e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
    /**
     * Actualiza un tupla en la base de datos
     * @param p- recibe un objecto de tipo client
     * @param s recibe un cadena para identificar la tupla
     */
    public String updateCliente(Cliente c, String rfc){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.cliente set rfc_c = ?, nombre= ?, apellido1= ?, apellido2= ? where rfc_c = ?"); 
                    pstm.setString(1,c.getRfc());
                    pstm.setString(2,c.getNombre());
                    pstm.setString(3,c.getApellido1());
                    pstm.setString(4,c.getApellido2());
                    pstm.setString(5,rfc);
            pstm.executeUpdate();
            pstm.close();  
         }catch(Exception e){
             resulatdo = String.valueOf(e.getMessage());
         }
        return resulatdo;
    }
    /**
     * consulta todo el contenido de la tabla
     */
    public DefaultTableModel Clientes(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.cliente");  
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
     * busca los clientes
     * @param dato- recibe un cadena para buscar clientes
     */
    public DefaultTableModel searchClientes(String dato){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.cliente WHERE UPPER(rfc_c) like ? or UPPER(nombre) like ?");            
    pstm.setString(1,"%"+dato.toUpperCase()+"%");
    pstm.setString(2,"%"+dato.toUpperCase()+"%");
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
     * busca los telefono de los clientes
     * @param dato- recibe un cadena para buscar
     */
    public ArrayList<String> TelefonoCliente(String dato){
        ArrayList<String> Telefonos = new ArrayList<String>();
        try{
            PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.tel_cliente WHERE rfc_c = ? ");            
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
     * busca los correo de los clientes
     * @param dato- recibe un cadena para buscar
     */
    public ArrayList<String> DireccionCliente(String dato){
        ArrayList<String> Correos = new ArrayList<String>();
        try{
            PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.dir_cliente WHERE rfc_p = ? ");            
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
