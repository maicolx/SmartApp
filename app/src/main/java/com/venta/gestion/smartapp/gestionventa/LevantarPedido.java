package com.venta.gestion.smartapp.gestionventa;

/**
 * Created by michael on 27/11/16.
 */


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.venta.gestion.smartapp.R;
import com.venta.gestion.smartapp.conectivity.BDVentas;
import com.venta.gestion.smartapp.contract.PedidoContract.*;
import com.venta.gestion.smartapp.entities.Producto;

import java.util.ArrayList;


public class LevantarPedido extends AppCompatActivity implements View.OnClickListener{
    String[] arrayCantidad = {"1","2","3","4","5","6","7","8","9","10"};
    Spinner spinnerProducto, spinnerCantidadCompra;
    TextView textPrecioUnitario, textMontoTotal;
    Button btnRegistrarPedido, btnConfirmarPedido;
    int idCliente,idEmpleado;
    String cantidadSeleccionada;
    int cantidad;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levantar_pedido);
        cargarProducto();
        cargarCantidad();

        spinnerProducto = (Spinner) findViewById(R.id.spinnerProducto);
        spinnerCantidadCompra = (Spinner) findViewById(R.id.spinnerCantidadCompra);

        textPrecioUnitario = (TextView) findViewById(R.id.textPrecioUnitario);
        textMontoTotal = (TextView) findViewById(R.id.textMontoTotal);

        btnRegistrarPedido = (Button) findViewById(R.id.btnRegistrarPedido);
        btnConfirmarPedido = (Button) findViewById(R.id.btnConfirmarPedido);
        btnRegistrarPedido.setOnClickListener(this);
        btnConfirmarPedido.setOnClickListener(this);

        //idCliente = this.getIntent().getExtras().getInt("clienteEnviado");

    }

    public void cargarProducto(){
        ArrayList<Producto> productoList = new ArrayList<Producto>();
        try {
            //El objeto conn establece una conexion
            BDVentas conn = new BDVentas(this);
            //db es un objeto que provee metodos para manipular y acceder a la base de datos
            db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select id,nombre,precio_venta,porcentaje_iva from producto", null);
            Producto producto;
            while (cursor.moveToNext()){
                producto= new Producto();

                producto.setId(cursor.getInt(0));
                producto.setNombre(cursor.getString(1));
                //producto.setPrecioCosto(cursor.getInt(2));
                producto.setPrecioVenta(cursor.getInt(2));
                producto.setPorcentajeIVA(cursor.getInt(3));
                //producto.setUltimaCompra(cursor.getString(5));
                //producto.setStockMinimo(cursor.getInt(6));
                //producto.setStockActual(cursor.getInt(7));

                productoList.add(producto);
            }
        } catch (Exception e){

        }
        spinnerProducto = (Spinner) findViewById(R.id.spinnerProducto);
        ArrayAdapter<Producto> adaptadorProducto = new ArrayAdapter<Producto>(this,android.R.layout.simple_expandable_list_item_1,productoList);

        //Se carga el Spinner con el adaptador
        spinnerProducto.setAdapter(adaptadorProducto);
    }

    public void cargarCantidad(){
        spinnerCantidadCompra = (Spinner) findViewById(R.id.spinnerCantidadCompra);
        ArrayAdapter<String> adaptadorCantidad = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayCantidad);
        //Se carga el spinner con el adaptador
        spinnerCantidadCompra.setAdapter(adaptadorCantidad);
    }

    @Override
    public void onClick(View view) {
        Producto productoSelecccionado = (Producto) spinnerProducto.getSelectedItem();
        cantidadSeleccionada = (String) spinnerCantidadCompra.getSelectedItem();
        cantidad = Integer.parseInt(cantidadSeleccionada);
        switch (view.getId()) {

            case R.id.btnRegistrarPedido:

                idCliente = this.getIntent().getExtras().getInt("clienteEnviado");
                idEmpleado = this.getIntent().getExtras().getInt("empleadoEnviado");

                // ContentValues es como un diccionario de datos que sirve para guardar datos a la BD
                ContentValues values = new ContentValues();

                //consultar ultimo valor del id pedido
                if (db != null) {
                    Cursor c = db.rawQuery("select * from pedido", null);
                    int cantidadFilas = 0;
                    cantidadFilas=c.getCount();

                    // Pares clave-valor
                    values.put(EntradaPedido.ID, cantidadFilas + 1);
                    values.put(EntradaPedido.ID_CLIENTE, idCliente);
                    values.put(EntradaPedido.ID_EMPLEADO, idEmpleado);
                    values.put(EntradaPedido.ID_PRODUCTO, productoSelecccionado.getId());
                    values.put(EntradaPedido.CANTIDAD, cantidadSeleccionada);
                    values.put(EntradaPedido.PRECIO_VENTA, productoSelecccionado.getPrecioVenta());
                    values.put(EntradaPedido.MONTO_TOTAL, productoSelecccionado.getPrecioVenta() * cantidad);
                }
                // Inserta un Pedido
                db.insert(EntradaPedido.TABLE_NAME, null, values);
                //Pedido pedido = new Pedido(1,idCliente,idEmpleado,productoSelecccionado.getId(),cantidad,productoSelecccionado.getPrecioVenta(),productoSelecccionado.getPrecioVenta()*cantidad);

                Intent intent = new Intent(this, ListarPedido.class);
                startActivity(intent);
                break;

            case R.id.btnConfirmarPedido:
                Toast.makeText(this, "Producto " + productoSelecccionado.getNombre()+" seleccionado",Toast.LENGTH_LONG).show();
                textPrecioUnitario.setText(Integer.toString(productoSelecccionado.getPrecioVenta()));
                textMontoTotal.setText(Integer.toString(productoSelecccionado.getPrecioVenta()*cantidad));

                break;
        }

    }


}
