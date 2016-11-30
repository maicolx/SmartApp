package com.venta.gestion.smartapp.gestionventa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.venta.gestion.smartapp.R;
import com.venta.gestion.smartapp.conectivity.BDVentas;
import com.venta.gestion.smartapp.contract.EmpleadoContract;
import com.venta.gestion.smartapp.entities.Cliente;
import com.venta.gestion.smartapp.entities.Empleado;

import java.util.ArrayList;


public class SeleccionCliente extends AppCompatActivity implements View.OnClickListener{
    Spinner spinnerCliente,spinnerEmpleado;
    Button btnSeleccionCliente, btnLevantarPedido, btnSeleccionarEmpleado;
    TextView textoTelefono,textoDireccion,tvdireccionvendedor,tvtelefonovendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_cliente);
        cargarCliente();
        cargarEmpleado();
        selecionarclientes();
        seleccionarvendedor();
        //Obteniendo una instancia de los textview y button
        //btnSeleccionCliente = (Button) findViewById(R.id.btnSeleccionarCliente);
        //btnSeleccionarEmpleado = (Button) findViewById(R.id.btnSeleccionarEmpleado);
        btnLevantarPedido = (Button) findViewById(R.id.btnLevantarPedido);
        textoDireccion = (TextView) findViewById(R.id.textDireccion);
        textoTelefono = (TextView) findViewById(R.id.textTelefono);
        tvdireccionvendedor = (TextView) findViewById(R.id.direccionvendedor);
        tvtelefonovendedor = (TextView) findViewById(R.id.telefonovendedor);

        //Registrando la escucha sobre la actividad Main
        //btnSeleccionCliente.setOnClickListener(this);
        //btnSeleccionarEmpleado.setOnClickListener(this);
        btnLevantarPedido.setOnClickListener(this);

    }

    public void cargarCliente(){
        ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
        try {
            //El objeto conn establece una conexion
            BDVentas conn = new BDVentas(this);
            //db es un objeto que provee metodos para manipular y acceder a la base de datos
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from cliente", null);
            Cliente cliente;
            while (cursor.moveToNext()){
                cliente= new Cliente();
                cliente.setCedula(cursor.getInt(0));
                cliente.setId(cursor.getInt(1));
                cliente.setNombre(cursor.getString(2));
                cliente.setApellido(cursor.getString(3));
                cliente.setTelefono(cursor.getString(4));
                cliente.setDireccion(cursor.getString(5));
                cliente.setRuc(cursor.getString(6));
                clientesList.add(cliente);
            }
        } catch (Exception e){

        }
        //obtener instancia de spinnercliente
        spinnerCliente = (Spinner) findViewById(R.id.spinnerCliente);
        ArrayAdapter<Cliente> adaptadorCliente = new ArrayAdapter<Cliente>(this,android.R.layout.simple_expandable_list_item_1,clientesList);

        //Seteas el adptador,carga el Spinner con el adaptador
        spinnerCliente.setAdapter(adaptadorCliente);
    }

    public void cargarEmpleado(){
        ArrayList<Empleado> empleadosList = new ArrayList<Empleado>();
        try {
            //El objeto conn establece una conexion
            BDVentas conn = new BDVentas(this);
            //db es un objeto que provee metodos para manipular y acceder a la base de datos
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from empleado", null);
            Empleado empleado;
            while (cursor.moveToNext()){
                empleado= new Empleado();
                empleado.setCedula(cursor.getInt(0));
                empleado.setId(cursor.getInt(1));
                empleado.setNombre(cursor.getString(2));
                empleado.setApellido(cursor.getString(3));
                empleado.setTelefono(cursor.getString(4));
                empleado.setDireccion(cursor.getString(5));
                empleado.setCargo(cursor.getString(6));
                empleadosList.add(empleado);
            }
        } catch (Exception e){

        }
        spinnerEmpleado = (Spinner) findViewById(R.id.spinnerEmpleado);
        ArrayAdapter<Empleado> adaptadorEmpleado = new ArrayAdapter<Empleado>(this,android.R.layout.simple_expandable_list_item_1,empleadosList);

        //Se carga el Spinner con el adaptador
        spinnerEmpleado.setAdapter(adaptadorEmpleado);
    }
    public void seleccionarvendedor(){
        spinnerEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> par, View view, int pos, long id) {
                Empleado datos= (Empleado) par.getItemAtPosition(pos);
                tvdireccionvendedor.setText(datos.getDireccion());

                tvtelefonovendedor.setText(datos.getTelefono());
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Elija un Vendedor",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void selecionarclientes(){
        spinnerCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.spinnerCliente) {
                    Cliente midireccion = ((Cliente) parent.getItemAtPosition(position));
                    textoDireccion.setText(midireccion.getDireccion());
                    textoTelefono.setText(midireccion.getTelefono());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(),"Elija un Cliente",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Cliente clienteSelecccionado = (Cliente) spinnerCliente.getSelectedItem();
        Empleado empleadoSeleccionado = (Empleado) spinnerEmpleado.getSelectedItem();
        switch (view.getId()){

            case R.id.btnLevantarPedido:
                int idCLiente = clienteSelecccionado.getId();
                int idEmpleado = empleadoSeleccionado.getCedula();
                Intent intent = new Intent(this, LevantarPedido.class);
                intent.putExtra("clienteEnviado",idCLiente);
                intent.putExtra("empleadoEnviado",idEmpleado);
                startActivity(intent);
                break;
            /* case R.id.btnSeleccionarCliente:

                Toast.makeText(this, "Cliente " + clienteSelecccionado.getNombre()+" "+clienteSelecccionado.getApellido()+" seleccionado",Toast.LENGTH_LONG).show();
                textoDireccion.setText(clienteSelecccionado.getDireccion());
                textoTelefono.setText(clienteSelecccionado.getTelefono());
                break;*/
           /* case R.id.btnSeleccionarEmpleado:
                Toast.makeText(this,"Empleado " + empleadoSeleccionado.getNombre()+" "+empleadoSeleccionado.getApellido()+" seleccionado",Toast.LENGTH_LONG).show();
                break;*/
        }

    }
}