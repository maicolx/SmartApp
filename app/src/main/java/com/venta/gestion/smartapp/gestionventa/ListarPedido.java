package com.venta.gestion.smartapp.gestionventa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.venta.gestion.smartapp.R;
import com.venta.gestion.smartapp.conectivity.BDVentas;

public class ListarPedido extends AppCompatActivity implements View.OnClickListener {
    Button btnAtras,btnFinalizar;
    ListView listaPedidos;
    int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedido);
        listarPedidos();
        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnAtras.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);

        listaPedidos = (ListView) findViewById(R.id.listaPedidos);
    }

    public void listarPedidos (){
        BDVentas conn = new BDVentas(this);
        SQLiteDatabase db = conn.getReadableDatabase();

        if (db != null){
            //idCliente = this.getIntent().getExtras().getInt("clienteEnviado");
            //String[] param  = new String[] {Integer.toString(idCliente)};

            // Cursor cursor = db.rawQuery("select p.cantidad,a.nombre,p.monto_total from pedido p INNER JOIN producto a on p.id_producto=a.id where p.id_cliente=? ", param);
            Cursor cursor = db.rawQuery("select p.cantidad,a.nombre,p.monto_total from pedido p INNER JOIN producto a on p.id_producto=a.id", null);
            //cuenta la cantidad de filas de la tabla
            int cantidadFilas = cursor.getCount();
            int i = 0;
            // Instancia el array donde se guardaran los pedidos.
            String [] arrayPedido = new String[cantidadFilas];
            Toast.makeText(this, "Filas " + cantidadFilas +" encontradas",Toast.LENGTH_LONG).show();
            // Hace el recorrido desde el primer registro
            if (cursor.moveToFirst()){

                do {

                    String linea = cursor.getInt(0) +"     "+ cursor.getString(1)+"     "+cursor.getInt(2);
                    arrayPedido[i] = linea;
                    i++;
                    //verifica mientras exista un siguiente registro
                }while(cursor.moveToNext());


            }
            // el adapatador sirve para comunicar el origen de los datos con un elemento de la vista
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayPedido);
            listaPedidos = (ListView) findViewById(R.id.listaPedidos);
            listaPedidos.setAdapter(adapter);
            if (db.isOpen()){
                cursor.close();
                db.close();}

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnAtras:
                Intent intent = new Intent(this, LevantarPedido.class);
                startActivity(intent);
                break;

            case R.id.btnFinalizar:
                Intent i = new Intent(this, SeleccionCliente.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

        }
    }
}

