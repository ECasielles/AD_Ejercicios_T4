package com.example.usuario.ad_ejercicios_t4.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.ad_ejercicios_t4.R;
import com.example.usuario.ad_ejercicios_t4.model.Contacto;
import com.example.usuario.ad_ejercicios_t4.util.RestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.util.ArrayList;

public class ContactosGSONActivity extends AppCompatActivity {
    public static final String WEB = "http://192.168.1.20/acceso/contacts.json";
    //public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayList<Contacto> contactos;
    ArrayAdapter<Contacto> adapter;
    Contacto persona;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_gson);

        boton = findViewById(R.id.button);
        lista = findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ContactosGSONActivity.this,
                        "Móvil: " + contactos.get(position).getTelefono().getMovil(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        contactos = new ArrayList<>();
    }

    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(WEB, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }
        });
    }

    private void mostrar() {
        if (persona != null) {
            contactos.clear();
            contactos.addAll(contactos);
            if (adapter == null) {
                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contactos);
            }
        } else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

}
