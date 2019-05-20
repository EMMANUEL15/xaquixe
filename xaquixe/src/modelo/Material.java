package modelo;
/**
 * @author Emanuel Lopez
 */
public class Material{
   private String id_material;
   private String nombre;
   private int cantidad;
   /**
    *contructor de la clase material
    * @param id_material- cadena de 15 caratres que contendra rfc del material
    * @param nombre- cadena que contendra nombre del material
    * @param cantidad- entero que contendra razon social del material
    */
    public Material(String id_material, String nombre, int cantidad) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    /**
     *sobrecarga del contructor Material
    */
    public Material() {
        this.id_material = null;
        this.nombre = null;
        this.cantidad = 0;
    }
    /**
    *getter and setter
    */
    public String getIdmaterial() {
        return id_material;
    }

    public void setIdmaterial(String id_material) {
        this.id_material = id_material;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
