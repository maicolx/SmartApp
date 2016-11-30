package com.venta.gestion.smartapp.entities;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private  String telefono;
    private String direccion;
    private String cargo;
    private int cedula;

    public Empleado() {
    }

    public Empleado(int cedula, int id, String nombre, String apellido, String telefono, String direccion, String cargo) {
        this.cedula = cedula;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cargo = cargo;
    }


    public void setId(int id) {this.id = id;}

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

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }



    public int getId() {return id;}

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

    public String getCargo() {
        return cargo;
    }

    public int getCedula() {
        return cedula;
    }



    @Override
    public String toString() {
        return nombre+" "+apellido;
    }
}
