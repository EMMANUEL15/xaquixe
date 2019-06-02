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
public class ModeloPedido{
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
     * @param folio- recibe el folio de pedido
     * @param id- recibe un id del material
     * @param c - la cantidad de material a pedir
     */
    public String AgregarMaterial(String folio,String id,int c){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.pedido(folio_p,id_material,cantidad) values(?,?,?)");  
                    pstm.setString(1,folio);
                    pstm.setString(2,id);
                    pstm.setInt(3,c);
            pstm.execute();
            pstm.close();  
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
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
}
