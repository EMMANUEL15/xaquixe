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
public class ModeloMaterial {
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
     * @param m- recibe un objecto de tipo material
     */
    public String insertMaterial(Material m){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.material(id_material, nombre, cantidad) values(?,?,?)");  
                    pstm.setString(1,m.getIdmaterial());
                    pstm.setString(2,m.getNombre());
                    pstm.setInt(3,m.getCantidad());
            pstm.execute();
            pstm.close();  
            
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
     /**
     * elimina una nueva tupla en la base de datos
     * @param id- recibe un id para identificar la tupla
     */
    public String deleteMaterial(String id){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.material where id_material = ?"); 
                pstm.setString(1,id);                   
                pstm.execute();
                pstm.close(); 
            }catch(SQLException e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
     /**
     * Actualiza un tupla en la base de datos
     * @param m- recibe un objecto de tipo material
     * @param id- recibe un id para identificar la tupla
     */
    public String updateMaterial(Material m, String id){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.material set id_material = ?, nombre = ?, cantidad = ?  where id_material = ?"); 
                    pstm.setString(1,m.getIdmaterial());
                    pstm.setString(2,m.getNombre());
                    pstm.setInt(3,m.getCantidad());
                    pstm.setString(4,id);
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
    public DefaultTableModel Materiales(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.material");  
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
     * buscador los materiales
     * @param dato- recibe un cadena para buscar material
     */
    public DefaultTableModel searchMateriales(String dato){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.material WHERE UPPER(id_material) like ? or UPPER(nombre) like ?");            
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
     * busca los prveedores
     * @param dato- recibe un cadena para buscar proveedor
     */
    public ArrayList<String> ProveedoresAutorizados(String dato){
        ArrayList<String> provedores = new ArrayList<String>();
        try{
            PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.proveedor_autorizado WHERE id_material = ? ");            
            pstm.setString(1,dato);
            ResultSet resultado = pstm.executeQuery();
        //REGISTROS
            while (resultado.next()) {
               provedores.add(resultado.getString(3));
            }
        }catch(Exception e){ }
        return provedores;
    }
}
