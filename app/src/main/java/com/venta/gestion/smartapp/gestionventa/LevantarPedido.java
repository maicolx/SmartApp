package com.venta.gestion.smartapp.gestionventa;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.venta.gestion.smartapp.R;
import com.venta.gestion.smartapp.conectivity.BDVentas;
import com.venta.gestion.smartapp.contract.PedidoContract.*;
import com.venta.gestion.smartapp.contract.ProductoContract;
import com.venta.gestion.smartapp.entities.Producto;
import java.util.ArrayList;


public class LevantarPedido extends AppCompatActivity implements View.OnClickListener{
    String[] arrayCantidad = {"0","1","2","3","4","5","6","7","8","9","10"};
    Spinner spinnerProducto, spinnerCantidadCompra;
    TextView textPrecioUnitario, textMontoTotal;
    Button btnRegistrarPedido, btnConfirmarPedido;
    int idCliente,idEmpleado;
    String cantidadSeleccionada;
    int cantidad;
    SQLiteDatabase db;
    public static final int DEFAULT_POSITION = 0;
    public final static String EXTRA_DATOS = "DATOENVIADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levantar_pedido);
        cargarProducto();
        cargarCantidad();
        cargararticulos();


        //Obteniendo una instancia de los textview y button
        spinnerProducto = (Spinner) findViewById(R.id.spinnerProducto);
        spinnerCantidadCompra = (Spinner) findViewById(R.id.spinnerCantidadCompra);
        textPrecioUnitario = (TextView) findViewById(R.id.textPrecioUnitario);
        textMontoTotal = (TextView) findViewById(R.id.textMontoTotal);
        btnRegistrarPedido = (Button) findViewById(R.id.btnRegistrarPedido);
        btnConfirmarPedido = (Button) findViewById(R.id.btnConfirmarPedido);
        //Registrando la escucha sobre la actividad Main
        btnRegistrarPedido.setOnClickListener(this);
        btnConfirmarPedido.setOnClickListener(this);
        //spinnerProducto.setOnItemSelectedListener(this);
        //spinnerCantidadCompra.setOnItemSelectedListener(this);

    }

    public void cargarProducto(){
        ArrayList<Producto> productoList = new ArrayList<Producto>();
        try {
            //El objeto conn establece una conexion
            BDVentas conn = new BDVentas(this);
            //db es un objeto que provee metodos para manipular y acceder a la base de datos
            db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select id,nombre,precio_venta,stock_actual,stock_minimo from producto", null);
            Producto producto;
            while (cursor.moveToNext()){
                producto= new Producto();

                producto.setId(cursor.getInt(0));
                producto.setNombre(cursor.getString(1));
                producto.setPrecioVenta(cursor.getInt(2));
                producto.setStockActual(cursor.getInt(3));
                producto.setStockMinimo(cursor.getInt(4));
                //producto.setPrecioCosto(cursor.getInt(3));
                //producto.setPorcentajeIVA(cursor.getInt(3));
                //producto.setUltimaCompra(cursor.getString(5));

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
        spinnerCantidadCompra.setSelection(DEFAULT_POSITION);
    }

   public void cargararticulos(){

        spinnerProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.spinnerProducto){
                    Producto dato = (Producto) parent.getItemAtPosition(position);
                    textPrecioUnitario.setText(String.valueOf(dato.getPrecioVenta()));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


      spinnerCantidadCompra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.spinnerCantidadCompra) {
                    String valor = (String) parent.getItemAtPosition(position);
                    int x, y;
                    x = Integer.valueOf(valor);
                    y = Integer.valueOf(String.valueOf(textPrecioUnitario.getText()));
                    //String cal = String.valueOf(x * y);
                    //textMontoTotal.setText("Monto Total: " + cal + " Gs");

                    Producto val = (Producto) spinnerProducto.getSelectedItem();
                    Integer min = Integer.valueOf(val.getStockMinimo());
                    Integer max = Integer.valueOf(val.getStockActual());
                        if ((min == 0) && (position != 0)) {
                            Toast.makeText(getApplicationContext(), "Stock Cero,elija otro producto", Toast.LENGTH_LONG).show();
                            textPrecioUnitario.setText("0");
                            textMontoTotal.setText("0");
                            spinnerCantidadCompra.setSelection(DEFAULT_POSITION);
                        } else if (min > max || min < max) {
                                if (x > max) {
                                    Toast.makeText(getApplicationContext(), "Cantidad no disponible de " + val.getNombre(), Toast.LENGTH_LONG).show();
                                    textPrecioUnitario.setText("0");
                                    textMontoTotal.setText("0");
                                    spinnerCantidadCompra.setSelection(DEFAULT_POSITION);
                                    spinnerProducto.setSelection(DEFAULT_POSITION);
                                }else {
                                    String cal = String.valueOf(x * y);
                                    textMontoTotal.setText("Monto Total: " + cal + " Gs");
                                }
                        }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


   @Override
    public void onClick(View view) {
        Producto productoSelecccionado = (Producto) spinnerProducto.getSelectedItem();
        cantidadSeleccionada = (String) spinnerCantidadCompra.getSelectedItem();
        cantidad = Integer.parseInt(cantidadSeleccionada);


        switch (view.getId()) {

            case R.id.btnRegistrarPedido:


                Intent intent = new Intent(this, ListarPedido.class);
                intent.putExtra(EXTRA_DATOS,idCliente);
                startActivity(intent);
                break;
            //agrega a la lista de pedidos
            case R.id.btnConfirmarPedido://cargarpedido
                Integer stockmi = Integer.valueOf(productoSelecccionado.getStockMinimo());
                Integer stockma = Integer.valueOf(productoSelecccionado.getStockActual());

                if (stockmi==0){
                    Toast.makeText(this,"Stock Cero,elija otro producto",Toast.LENGTH_LONG).show();
                    textPrecioUnitario.setText("0");
                    textMontoTotal.setText("0");
                    spinnerCantidadCompra.setSelection(DEFAULT_POSITION);

                }
                else if (stockmi >= stockma || stockmi <= stockma) {

                    if((cantidad>stockma) && (cantidad>stockmi)) {
                        Toast.makeText(this, "Cantidad no disponible de " + productoSelecccionado.getNombre(), Toast.LENGTH_LONG).show();
                        textPrecioUnitario.setText("0");
                        textMontoTotal.setText("0");
                        spinnerCantidadCompra.setSelection(DEFAULT_POSITION);
                        spinnerProducto.setSelection(DEFAULT_POSITION);


                    }else {
                        Toast.makeText(this, "Conectando...",Toast.LENGTH_LONG).show();
                        try {
                            idCliente = this.getIntent().getIntExtra(SeleccionCliente.EXTRA_CLIENTE,0);
                            idEmpleado = this.getIntent().getIntExtra(SeleccionCliente.EXTRA_VENDEDOR,0);
                            ContentValues values = new ContentValues();
                            /*BDVentas conn = new BDVentas(this);
                            db = conn.getReadableDatabase();*/
                           // Toast.makeText(this, "Accediendo a la BD..."+idCliente,Toast.LENGTH_LONG).show();
                            //consultar ultimo valor del id pedido
                            if (db != null) {
                                Cursor c = db.rawQuery("select * from pedido", null);
                                int cantidadFilas = 0;
                                cantidadFilas=c.getCount();
                              //  Toast.makeText(this, "Filas " + cantidadFilas +" encontradas",Toast.LENGTH_LONG).show();
                                // Pares clave-valor
                                values.put(EntradaPedido.ID, cantidadFilas + 1);
                                values.put(EntradaPedido.ID_CLIENTE, idCliente);
                                values.put(EntradaPedido.ID_EMPLEADO, idEmpleado);
                                values.put(EntradaPedido.ID_PRODUCTO, productoSelecccionado.getId());
                                values.put(EntradaPedido.CANTIDAD, cantidadSeleccionada);
                                values.put(EntradaPedido.PRECIO_VENTA, productoSelecccionado.getPrecioVenta());
                                values.put(EntradaPedido.MONTO_TOTAL, productoSelecccionado.getPrecioVenta() * cantidad);
                                // Inserta un Pedido
                                db.insert(EntradaPedido.TABLE_NAME, null, values);
                            }
                            Toast.makeText(this, "Se Agrego " + productoSelecccionado.getNombre()+" a la lista",Toast.LENGTH_LONG).show();
                            textMontoTotal.setText("Monto total: "+Integer.toString(productoSelecccionado.getPrecioVenta()*cantidad)+" Gs");

                        } catch (Exception e){
                            Toast.makeText(this, "Se perdio la conexion",Toast.LENGTH_LONG).show();
                            textPrecioUnitario.setText("0");
                            textMontoTotal.setText("0");
                            spinnerCantidadCompra.setSelection(DEFAULT_POSITION);
                        }

                        try {
                            Toast.makeText(this, "Actualizando stock productos...",Toast.LENGTH_LONG).show();
                            // Valores
                            ContentValues values1 = new ContentValues();

                            // Valores nuevos del nombre y teléfono
                            values1.put(ProductoContract.EntradaProducto.ID, productoSelecccionado.getId());
                            values1.put(ProductoContract.EntradaProducto.STOCK_ACTUAL, productoSelecccionado.getStockActual()-cantidad);
                            if (db != null) {
                                String[] args = new String[]{String.valueOf(stockma-cantidad), String.valueOf(productoSelecccionado.getId())};
                                //String strSQL = "UPDATE producto SET stock_actual = ? WHERE id = ?",args;
                                db.execSQL("UPDATE producto SET stock_actual = ? WHERE id = ?",args);
                                Toast.makeText(this, "Tabla productos actualizado...",Toast.LENGTH_LONG).show();
                                cargarProducto();

                            }
                        }catch (Exception e){
                            Toast.makeText(this, "No se pudo actualizar la tabla productos...",Toast.LENGTH_LONG).show();
                        }
                    }

             }
                else {
                    Toast.makeText(this, "Cantidad no disponible..",Toast.LENGTH_LONG).show();
                }

                break;
        }

    }


}
