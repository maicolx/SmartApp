package com.venta.gestion.smartapp.contract;

/**
 * Created by michael on 27/11/16.
 */

import android.provider.BaseColumns;

/**
 * Created by root on 26/10/16.
 */
public class EmpleadoContract {
    public static abstract class EntradaEmpleado implements BaseColumns {
        public static final String TABLE_NAME ="empleado";

        public static final String CEDULA = "cedula";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String TELEFONO = "telefono";
        public static final String DIRECCION = "direccion";
        public static final String CARGO = "cargo";


    }

}
