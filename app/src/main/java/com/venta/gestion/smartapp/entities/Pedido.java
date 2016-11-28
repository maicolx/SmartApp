package com.venta.gestion.smartapp.entities;



public class Pedido {
    private int id;
    private int idCliente;
    private int idEmpleado;
    private int idProducto;
    private int cantidad;
    private int precioVenta;
    private int montoTotal;

    public Pedido() {
    }

    public Pedido(int id, int idCliente, int idEmpleado, int idProducto, int cantidad, int precioVenta, int montoTotal) {
        this.id = id;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.montoTotal = montoTotal;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public int getMontoTotal() {
        return montoTotal;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioVenta=" + precioVenta +
                ", montoTotal=" + montoTotal +
                '}';
    }
}

