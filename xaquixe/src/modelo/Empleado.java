package modelo;
/**
 * @author Emanuel Lopez
 */
public class Empleado {
     private String rfc,nombre,apellido1,apellido2,calle, numero,colonia,municipio,estado,codigo;

    public Empleado(String rfc, String nombre, String apellido1, String apellido2, String calle, String numero, String colonia, String municipio, String estado, String codigo) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.codigo = codigo;
    }
    public Empleado() {
        this.rfc = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.calle = null;
        this.numero = null;
        this.colonia = null;
        this.municipio = null;
        this.estado = null;
        this.codigo = null;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
     
}
