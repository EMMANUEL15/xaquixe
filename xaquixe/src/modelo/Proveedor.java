package modelo;
/**
 * @author Emanuel Lopez
 */
public class Proveedor {
    private String  rfc, nombre,razon,calle,numero,colonia,municipio,estado,codigo;
    /*
    *contructor de la clase proveedor
    * @param rfc- cadena de 15 caratres que contendra rfc del proveedor
    * @param nombre- cadena que contendra nombre del proveedor
    * @param razon- cadena que contendra razon social del proveedor
    * @param calle- cadena que contendra calle del proveedor
    * @param numero- cadena que contendra numero de calle del proveedor
    * @param municipio- cadena que contendra municipio del proveedor
    * @param estado- cadena que contendra estado del proveedor
    * @param codigo- cadena que contendra codigo postal del proveedor
    */
    public Proveedor(String rfc,String nombre,String razon,String calle,String numero,String colonia,String municipio,String estado,String codigo) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.razon = razon;
        this.calle = calle;
        this.numero = numero;
        this.colonia =colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.codigo = codigo;
    }
    /*
    * consustructor de proveedor (sobrecarga)
    */
    public Proveedor(){
        this.rfc = null;
        this.nombre = null;
        this.razon = null;
        this.calle = null;
        this.numero = null;
        this.colonia =null;
        this.municipio = null;
        this.estado = null;
        this.codigo = null;
    }
    /*
    * getter and setter
    */
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

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
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
