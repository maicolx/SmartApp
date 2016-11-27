package com.venta.gestion.smartapp.entities;

/**
 * Created by michael on 27/11/16.
 */

public class Producto {
    private int id;
    private String nombre;
    private int precioCosto;
    private int precioVenta;
    private int porcentajeIVA;
    private String ultimaCompra;
    private int stockMinimo;
    private int stockActual;

    public Producto() {
    }

    public Producto(int id, String nombre, int precioCosto, int precioVenta, int porcentajeIVA, String ultimaCompra, int stockMinimo, int stockActual) {
        this.id = id;
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.porcentajeIVA = porcentajeIVA;
        this.ultimaCompra = ultimaCompra;
        this.stockMinimo = stockMinimo;
        this.stockActual = stockActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(int precioCosto) {
        this.precioCosto = precioCosto;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getPorcentajeIVA() {
        return porcentajeIVA;
    }

    public void setPorcentajeIVA(int porcentajeIVA) {
        this.porcentajeIVA = porcentajeIVA;
    }

    public String getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(String ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    @Override
    public String toString() {
        return id+ "  "+nombre;
    }
}
