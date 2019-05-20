package modelo;
/**
 * @author Emanuel lopez
 */
public class Producto {
    private String sku;
    private String item;
    private String medida;
    private int cantidad;
    private double precio;
    /**
    *contructor de la clase producto
    * @param sku- cadena de 15 caratres que contendra sku del producto
    * @param item- cadena que contendra nombre del producto
    * @param medida- cadena que contendra medidas del producto
    * @param cantidad- cadena que contendra cantidad en existencia del producto
    * @param precio- cadena que contendra precio del producto
    */
    public Producto(String sku, String item, String medida, int cantidad, double precio) {
        this.sku = sku;
        this.item = item;
        this.medida = medida;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    /**
    *sobrecarga del constructor
    */
    public Producto(){
        this.sku = null;
        this.item = null;
        this.medida = null;
        this.cantidad = 0;
        this.precio = 0.00;
    }
    /**
    * getter and setter
    */
    public String getSku() {
        return sku;
    }

    public String getItem() {
        return item;
    }

    public String getMedida() {
        return medida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
