package com.venta.gestion.smartapp.contract;

import android.provider.BaseColumns;

/**
 * Created by root on 26/10/16.
 */
public class ProductoContract {
    public static abstract class EntradaProducto implements BaseColumns {
        public static final String TABLE_NAME ="producto";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String PRECIO_COSTO = "precio_costo";
        public static final String PRECIO_VENTA = "precio_venta";
        public static final String PORCENTAJE_IVA = "porcentaje_iva";
        public static final String ULTIMA_COMPRA = "ultima_compra";
        public static final String STOCK_MINIMO = "stock_minimo";
        public static final String STOCK_ACTUAL = "stock_actual";

    }
}
