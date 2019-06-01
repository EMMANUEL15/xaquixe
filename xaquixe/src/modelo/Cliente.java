package modelo;
/**
 *
 * @author Emanuel Lopez
 */
public class Cliente {
    private String rfc,nombre,apellido1,apellido2;

    public Cliente(String rfc, String nombre, String apellido1, String apellido2) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
    public Cliente() {
        this.rfc = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
}
