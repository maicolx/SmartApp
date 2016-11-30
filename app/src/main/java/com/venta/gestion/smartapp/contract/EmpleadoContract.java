package com.venta.gestion.smartapp.contract;

import android.provider.BaseColumns;

public class EmpleadoContract {
    public static abstract class EntradaEmpleado implements BaseColumns {
        public static final String TABLE_NAME ="empleado";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String TELEFONO = "telefono";
        public static final String DIRECCION = "direccion";
        public static final String CARGO = "cargo";
        public static final String CEDULA = "cedula";


    }

}
