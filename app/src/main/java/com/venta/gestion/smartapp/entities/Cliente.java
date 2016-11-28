package com.venta.gestion.smartapp.entities;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String ruc;
    private int cedula;

    public Cliente() {
    }

    public Cliente(int cedula, int id, String nombre, String apellido, String telefono, String direccion, String ruc) {
        this.cedula = cedula;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public int getCedula() {
        return cedula;
    }

    @Override
    public String toString() {
        return id + " "+nombre+" "+apellido;
    }
}
