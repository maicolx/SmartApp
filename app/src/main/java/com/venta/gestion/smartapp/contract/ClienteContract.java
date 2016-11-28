package com.venta.gestion.smartapp.contract;

import android.provider.BaseColumns;

/**
 * Esta clase guarda como constantes todas las características de la base de datos.
 */
public class ClienteContract {
    //Esta clase interna sirve para guardar el nombre de las columnas de la tabla.
    //Se implementó la interfaz BaseColumns con el fin de agregar una columna extra
    public static abstract class EntradaCliente implements BaseColumns {
        public static final String TABLE_NAME ="cliente";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String TELEFONO = "telefono";
        public static final String DIRECCION = "direccion";
        public static final String RUC = "ruc";
        public static final String CEDULA = "cedula";

    }
}