/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */
public class Cotizacion {
    
    String folio_Cotizacion;
    String rfc_empleado;
    String rfc_cliente;
    private Date fecha_venta;
    int descuento;

    public Cotizacion(String folio_Cotizacion, String rfc_empleado, String rfc_cliente, int descuento) {
        this.folio_Cotizacion = folio_Cotizacion;
        this.rfc_empleado = rfc_empleado;
        this.rfc_cliente = rfc_cliente;
       // this.fecha_venta = fecha_venta;
        this.descuento = descuento;
    }

    public Cotizacion(String rfc_empleado, String rfc_cliente, Date fecha_venta, int descuento) {
        this.rfc_empleado = rfc_empleado;
        this.rfc_cliente = rfc_cliente;
        this.fecha_venta = fecha_venta;
        this.descuento = descuento;
    }

    public String getFolio_Cotizacion() {
        return folio_Cotizacion;
    }

    public void setFolio_Cotizacion(String folio_Cotizacion) {
        this.folio_Cotizacion = folio_Cotizacion;
    }

    public String getRfc_empleado() {
        return rfc_empleado;
    }

    public void setRfc_empleado(String rfc_empleado) {
        this.rfc_empleado = rfc_empleado;
    }

    public String getRfc_cliente() {
        return rfc_cliente;
    }

    public void setRfc_cliente(String rfc_cliente) {
        this.rfc_cliente = rfc_cliente;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
   
    
    
}
