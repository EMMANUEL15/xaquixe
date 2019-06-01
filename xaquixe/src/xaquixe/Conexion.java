package xaquixe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Emanuel lopez
 */
public class Conexion {
    private String  host        = "localhost";
    private String  usuario     = "postgres";
    private String  clave       = "lopez";
    private int     puerto      = 5434;
    private String  servidor    = "";
    private static Connection conexion  = null;
    private String baseDatos;
    
    public Conexion(String baseDatos){
        this.baseDatos = baseDatos;
        ConexionBd();
    }
    
    public void ConexionBd(){
        this.servidor="jdbc:postgresql://"+host+":"+ puerto+"/"+baseDatos;
 
        //Registrar el driver
        try {            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR AL REGISTRAR EL DRIVER " + e);
            System.exit(0); //parar la ejecución
        }
 
        //Establecer la conexión con el servidor
        try {
            conexion = DriverManager.getConnection(this.servidor,
                        this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0); //parar la ejecución
        }
        System.out.println("Conectado a "+ baseDatos);
    }
 
    //Devuelve el objeto Connection que se usará en la clase Controller
    public Connection getConexion() {
        return conexion;
    }
}
