package com.venta.gestion.smartapp.contract;



import android.provider.BaseColumns;


public class PedidoContract {
    public static abstract class EntradaPedido implements BaseColumns {
        public static final String TABLE_NAME ="pedido";

        public static final String ID = "id";
        public static final String ID_CLIENTE= "id_cliente";
        public static final String ID_EMPLEADO = "id_empleado";
        public static final String ID_PRODUCTO = "id_producto";
        public static final String CANTIDAD = "cantidad";
        public static final String PRECIO_VENTA = "precio_venta";
        public static final String MONTO_TOTAL = "monto_total";

    }
}
