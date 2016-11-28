package com.venta.gestion.smartapp.conectivity;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.venta.gestion.smartapp.contract.ClienteContract.*;
import com.venta.gestion.smartapp.contract.ProductoContract.*;
import com.venta.gestion.smartapp.contract.EmpleadoContract.*;
import com.venta.gestion.smartapp.contract.PedidoContract.*;

/**
 * Clase destinada para la conexion a al base de datos, manejador de la conexion.
 */
public class BDVentas extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "GestionVenta.db";
    //Es recomendable que la llave primaria sea EntradaUsuario._ID, ya que el framework de Android
    //usa esta referencia internamente en varios procesos.
    //Sin embargo puedes usar tu propio ID y añadirle un índice UNIQUE para mantener la unicidad
    // de tus filas según tus reglas de negocio.
/*    String sqlCreateUsuario = "CREATE TABLE "+ EntradaUsuario.TABLE_NAME+" (" +
            EntradaUsuario._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EntradaUsuario.ID + " INTEGER NOT NULL, " +
            EntradaUsuario.NOMBRE + " TEXT NOT NULL, " +
            EntradaUsuario.CLAVE + " TEXT NOT NULL," +
            " UNIQUE ("+EntradaUsuario.ID+"))";
*/
    String sqlCreateProducto = "CREATE TABLE " + EntradaProducto.TABLE_NAME+" ("+
            EntradaProducto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EntradaProducto.ID + " INTEGER NOT NULL, " +
            EntradaProducto.NOMBRE+" TEXT NOT NULL, " +
            EntradaProducto.PRECIO_COSTO +" INTEGER NOT NULL, " +
            EntradaProducto.PRECIO_VENTA +" INTEGER NOT NULL, " +
            EntradaProducto.PORCENTAJE_IVA +" INTEGER NOT NULL, " +
            EntradaProducto.ULTIMA_COMPRA + " TEXT NOT NULL, " +
            EntradaProducto.STOCK_MINIMO +" INTEGER NOT NULL, " +
            EntradaProducto.STOCK_ACTUAL +" INTEGER NOT NULL," +
            " UNIQUE ("+EntradaProducto.ID+"))";

    String sqlCreateCliente = "CREATE TABLE "+ EntradaCliente.TABLE_NAME+" ("+
            EntradaCliente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EntradaCliente.ID + " INTEGER NOT NULL, " +
            EntradaCliente.NOMBRE +" TEXT NOT NULL, " +
            EntradaCliente.APELLIDO +" TEXT, " +
            EntradaCliente.TELEFONO +" TEXT, " +
            EntradaCliente.DIRECCION +" TEXT, " +
            EntradaCliente.RUC +" TEXT, " +
            EntradaCliente.CEDULA +" INTEGER," +
            " UNIQUE ("+EntradaCliente.ID+"))";

    String sqlCreateEmpleado = "CREATE TABLE "+ EntradaEmpleado.TABLE_NAME+" ("+
            EntradaEmpleado._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EntradaEmpleado.CEDULA +" INTEGER NOT NULL, " +
            EntradaEmpleado.NOMBRE +" TEXT NOT NULL, " +
            EntradaEmpleado.APELLIDO +" TEXT NOT NULL, " +
            EntradaEmpleado.TELEFONO +" TEXT, " +
            EntradaEmpleado.DIRECCION +" TEXT, " +
            EntradaEmpleado.CARGO +" TEXT NOT NULL,"+
            " UNIQUE ("+EntradaEmpleado.CEDULA+"))";

    String sqlCreatePedido = "CREATE TABLE "+ EntradaPedido.TABLE_NAME+" ("+
            EntradaPedido._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EntradaPedido.ID +" INTEGER NOT NULL, " +
            EntradaPedido.ID_CLIENTE +" INTEGER NOT NULL, " +
            EntradaPedido.ID_EMPLEADO +" INTEGER NOT NULL, " +
            EntradaPedido.ID_PRODUCTO +" INTEGER NOT NULL, " +
            EntradaPedido.CANTIDAD +" INTEGER NOT NULL, " +
            EntradaPedido.PRECIO_VENTA +" INTEGER NOT NULL, " +
            EntradaPedido.MONTO_TOTAL + " INTEGER NOT NULL," +
            " FOREIGN KEY ("+EntradaPedido.ID_CLIENTE+") REFERENCES "+EntradaCliente.TABLE_NAME+" ("+EntradaCliente.ID+") ON DELETE CASCADE," +
            " FOREIGN KEY ("+EntradaPedido.ID_EMPLEADO+") REFERENCES "+EntradaEmpleado.TABLE_NAME+" ("+EntradaEmpleado.CEDULA+") ON DELETE CASCADE," +
            " FOREIGN KEY ("+EntradaPedido.ID_PRODUCTO+") REFERENCES "+EntradaProducto.TABLE_NAME+"("+EntradaProducto.ID+") ON DELETE CASCADE," +
            " UNIQUE ("+EntradaPedido.ID+"))";



    /**
     *
     * @param context Contexto de acción para el helper, normalmente referencia de la activity que
     *                estamos llamando
     * DATABASE_NAME  Nombre del archivo con extensión .db, donde se almacenará la base de datos,
     *              que a su vez corresponde al nombre de la base de datos.
     * factory Asignamos null, por ahora no es necesario comprender el funcionamiento
     *                de este parámetro.
     * DATABASE_VERSION Entero que representa la versión de la base de datos. Su valor inicial por
     *                defecto es 1. Si en algún momento la versión es mayor se llama al método
     *                onUpgrade() para actualizar la base de datos a la nueva versión. Si es menor,
     *                se llama a downUpgrade() para volver a una versión previa.
     */
    public BDVentas(Context context) {

        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }


    /**
     *
     * @param sqLiteDatabase es el manejador de conexiones de la BD
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
           Si no existe la Base de datos la crea y ejecuta los siguientes comandos
           para la creacion de las tablas.
         */

        //  sqLiteDatabase.execSQL(sqlCreateUsuario);
        sqLiteDatabase.execSQL(sqlCreateProducto);
        sqLiteDatabase.execSQL(sqlCreateCliente);
        sqLiteDatabase.execSQL(sqlCreateEmpleado);
        sqLiteDatabase.execSQL(sqlCreatePedido);

        // ContentValues es como un diccionario de datos que sirve para guardar datos a la BD
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();

        // Pares clave-valor
        values.put(EntradaCliente.ID, 1);
        values.put(EntradaCliente.NOMBRE, "Carlos");
        values.put(EntradaCliente.APELLIDO, "Florentin");
        values.put(EntradaCliente.TELEFONO, "0991-305-079");
        values.put(EntradaCliente.DIRECCION, "Cadete Sisa 818 Fernando Zona Norte");
        values.put(EntradaCliente.CEDULA, 4556446);
        values.put(EntradaCliente.RUC, "4556446-9");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaCliente.TABLE_NAME, null, values);

        // Pares clave-valor
        values.put(EntradaCliente.ID, 2);
        values.put(EntradaCliente.NOMBRE, "Analia");
        values.put(EntradaCliente.APELLIDO, "Baglieri");
        values.put(EntradaCliente.TELEFONO, "0971-143-134");
        values.put(EntradaCliente.DIRECCION, "Dr. Montanaro 410 Asuncion");
        values.put(EntradaCliente.CEDULA, 35154008);
        values.put(EntradaCliente.RUC, "35154008-0");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaCliente.TABLE_NAME, null, values);

        // Pares clave-valor
        values.put(EntradaCliente.ID, 3);
        values.put(EntradaCliente.NOMBRE, "Lissi");
        values.put(EntradaCliente.APELLIDO, "Sanabria");
        values.put(EntradaCliente.TELEFONO, "0971-480-250");
        values.put(EntradaCliente.DIRECCION, "Andrade 1268 Asuncion");
        values.put(EntradaCliente.CEDULA, 1929358);
        values.put(EntradaCliente.RUC, "1929358-8");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaCliente.TABLE_NAME, null, values);

        // Pares clave-valor
        values.put(EntradaCliente.ID, 4);
        values.put(EntradaCliente.NOMBRE, "Jorge");
        values.put(EntradaCliente.APELLIDO, "Maciel");
        values.put(EntradaCliente.TELEFONO, "0962-025-450");
        values.put(EntradaCliente.DIRECCION, "Herminio Gimenez 2046 CDE");
        values.put(EntradaCliente.CEDULA, 4562978);
        values.put(EntradaCliente.RUC, "4562978-3");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaCliente.TABLE_NAME, null, values);

        // Pares clave-valor
        values.put(EntradaCliente.ID, 5);
        values.put(EntradaCliente.NOMBRE, "Pedro");
        values.put(EntradaCliente.APELLIDO, "Gonzalez");
        values.put(EntradaCliente.TELEFONO, "0984-501-408");
        values.put(EntradaCliente.DIRECCION, "Av. Peron 1352 Lambare");
        values.put(EntradaCliente.CEDULA, 4845753);
        values.put(EntradaCliente.RUC, "4845753-5");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaCliente.TABLE_NAME, null, values);

        // Pares clave-valor
        values2.put(EntradaEmpleado.CEDULA, 4358942);
        values2.put(EntradaEmpleado.NOMBRE, "David");
        values2.put(EntradaEmpleado.APELLIDO, "Arrua");
        values2.put(EntradaEmpleado.TELEFONO, "0971-584-276");
        values2.put(EntradaEmpleado.DIRECCION, "Las Residentas 909 Fernando");
        values2.put(EntradaEmpleado.CARGO, "VENDEDOR");
        // Inserta un empleado
        sqLiteDatabase.insert(EntradaEmpleado.TABLE_NAME, null, values2);

        // Pares clave-valor
        values2.put(EntradaEmpleado.CEDULA, 4536879);
        values2.put(EntradaEmpleado.NOMBRE, "Michael");
        values2.put(EntradaEmpleado.APELLIDO, "Girett");
        values2.put(EntradaEmpleado.TELEFONO, "0981-277-041");
        values2.put(EntradaEmpleado.DIRECCION, "Alejo Garcia 1541 Lambare");
        values2.put(EntradaEmpleado.CARGO, "VENDEDOR");
        // Inserta un empleado
        sqLiteDatabase.insert(EntradaEmpleado.TABLE_NAME, null, values2);

        // Pares clave-valor
        values2.put(EntradaEmpleado.CEDULA, 3897920);
        values2.put(EntradaEmpleado.NOMBRE, "Naida");
        values2.put(EntradaEmpleado.APELLIDO, "Benitez");
        values2.put(EntradaEmpleado.TELEFONO, "0961-045-987");
        values2.put(EntradaEmpleado.DIRECCION, "Fulgencion Moreno 1024 Asuncion");
        values2.put(EntradaEmpleado.CARGO, "VENDEDOR");
        // Inserta un cliente
        sqLiteDatabase.insert(EntradaEmpleado.TABLE_NAME, null, values2);


        // Pares clave-valor
        values3.put(EntradaProducto.ID, 1);
        values3.put(EntradaProducto.NOMBRE, "Coca Cola 500ml");
        values3.put(EntradaProducto.PRECIO_COSTO, 3800);
        values3.put(EntradaProducto.PRECIO_VENTA, 5000);
        values3.put(EntradaProducto.PORCENTAJE_IVA, 10);
        values3.put(EntradaProducto.ULTIMA_COMPRA, "20/10/2016");
        values3.put(EntradaProducto.STOCK_MINIMO, 5);
        values3.put(EntradaProducto.STOCK_ACTUAL, 46);
        // Inserta un producto
        sqLiteDatabase.insert(EntradaProducto.TABLE_NAME, null, values3);

        // Pares clave-valor
        values3.put(EntradaProducto.ID, 2);
        values3.put(EntradaProducto.NOMBRE, "Frugos 1L");
        values3.put(EntradaProducto.PRECIO_COSTO, 5700);
        values3.put(EntradaProducto.PRECIO_VENTA, 7500);
        values3.put(EntradaProducto.PORCENTAJE_IVA, 10);
        values3.put(EntradaProducto.ULTIMA_COMPRA, "19/10/2016");
        values3.put(EntradaProducto.STOCK_MINIMO, 1);
        values3.put(EntradaProducto.STOCK_ACTUAL, 5);
        // Inserta un producto
        sqLiteDatabase.insert(EntradaProducto.TABLE_NAME, null, values3);

        // Pares clave-valor
        values3.put(EntradaProducto.ID, 3);
        values3.put(EntradaProducto.NOMBRE, "Fuller Honey Dew 500ml");
        values3.put(EntradaProducto.PRECIO_COSTO, 13000);
        values3.put(EntradaProducto.PRECIO_VENTA, 19000);
        values3.put(EntradaProducto.PORCENTAJE_IVA, 10);
        values3.put(EntradaProducto.ULTIMA_COMPRA, "15/10/2016");
        values3.put(EntradaProducto.STOCK_MINIMO, 1);
        values3.put(EntradaProducto.STOCK_ACTUAL, 16);
        // Inserta un producto
        sqLiteDatabase.insert(EntradaProducto.TABLE_NAME, null, values3);

        // Pares clave-valor
        values3.put(EntradaProducto.ID, 4);
        values3.put(EntradaProducto.NOMBRE, "HERKEN 500ml");
        values3.put(EntradaProducto.PRECIO_COSTO, 13000);
        values3.put(EntradaProducto.PRECIO_VENTA, 19000);
        values3.put(EntradaProducto.PORCENTAJE_IVA, 10);
        values3.put(EntradaProducto.ULTIMA_COMPRA, "16/10/2016");
        values3.put(EntradaProducto.STOCK_MINIMO, 2);
        values3.put(EntradaProducto.STOCK_ACTUAL, 24);
        // Inserta un producto
        sqLiteDatabase.insert(EntradaProducto.TABLE_NAME, null, values3);

        // Pares clave-valor
        values3.put(EntradaProducto.ID, 5);
        values3.put(EntradaProducto.NOMBRE, "London PORTER 500ml");
        values3.put(EntradaProducto.PRECIO_COSTO, 13000);
        values3.put(EntradaProducto.PRECIO_VENTA, 19000);
        values3.put(EntradaProducto.PORCENTAJE_IVA, 10);
        values3.put(EntradaProducto.ULTIMA_COMPRA, "13/10/2016");
        values3.put(EntradaProducto.STOCK_MINIMO, 1);
        values3.put(EntradaProducto.STOCK_ACTUAL, 14);
        // Inserta un producto
        sqLiteDatabase.insert(EntradaProducto.TABLE_NAME, null, values3);
    }

    /**
     * Este método es ejecutado si se identificó que el usuario tiene una versión antigua de
     * la base de datos.
     * En su interior establecerás instrucciones para modificar el esquema de la base de datos,
     * como por ejemplo eliminar el esquema completo y recrearlo, agregar una nueva tabla,
     * añadir una nueva columna, etc.
     * @param sqLiteDatabase Manejador de la base de datos.
     * @param oldVersion Es la version anterior de la base de datos representado como numerico.
     * @param newVersion Es la nueva version que tendra la base de datos se representa como numerico.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuario");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS producto");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cliente");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS empleado");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedido");

        //Aqui se puede volver a crear la base de datos modificada
    }
}
