/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Bernal Gonzalez Moarchel Manic
 */
public class DetalleVenta {
    
    private String folio_venta;
    private String sku;
    private int cantidad;
    private float precio;

    public DetalleVenta(String sku, int cantidad, float precio) {
        this.sku = sku;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleVenta(String folio_venta, String sku, int cantidad, float precio) {
        this.folio_venta = folio_venta;
        this.sku = sku;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getFolio_venta() {
        return folio_venta;
    }

    public void setFolio_venta(String folio_venta) {
        this.folio_venta = folio_venta;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
    
    
}
