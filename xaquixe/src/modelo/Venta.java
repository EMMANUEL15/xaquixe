/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Date;


/**
 *
 * @author MORACHEL MANIC BERNAL GONZALEZ
 * 
 */


public class Venta {
    
    private String folio;
    private Date fecha_venta;
    private String rfc_empledo;
    private String rfc_cliente;
    private int descuento;

    public Venta(String folio, String rfc_empledo, String rfc_cliente, int descuento) {
        this.folio = folio;
        this.rfc_empledo = rfc_empledo;
        this.rfc_cliente = rfc_cliente;
        this.descuento = descuento;
    }
    
    
    
    public Venta(String folio, Date fecha_venta, String rfc_empledo, String rfc_cliente, int descuento) {
        this.folio = folio;
        this.fecha_venta = fecha_venta;
        this.rfc_empledo = rfc_empledo;
        this.rfc_cliente = rfc_cliente;
        this.descuento = descuento;
    }

    

    
    /**
     * 
     * @return String con el folio de venta
     */
    public String getFolio() {
        return folio;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public String getRfc_empledo() {
        return rfc_empledo;
    }

    public String getRfc_cliente() {
        return rfc_cliente;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public void setRfc_empledo(String rfc_empledo) {
        this.rfc_empledo = rfc_empledo;
    }

    public void setRfc_cliente(String rfc_cliente) {
        this.rfc_cliente = rfc_cliente;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    
    
}
