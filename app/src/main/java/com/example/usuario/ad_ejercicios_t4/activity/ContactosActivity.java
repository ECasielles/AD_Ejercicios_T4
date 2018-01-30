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
import com.example.usuario.ad_ejercicios_t4.util.AnalisisJSON;
import com.example.usuario.ad_ejercicios_t4.util.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ContactosActivity extends AppCompatActivity {

    //Podemos comprobar el contenido del JSON usando curl

    //public static final String WEB = "http://192.168.3.57/acceso/contactos.json";
    public static final String WEB = "https://portadaalta.mobi/acceso/contactos.json";
    //public static final String WEB = "https://alumno.mobi/~alumno/superior/casielles/contactos.json";
    //public static final String WEB = "http://192.168.0.139/acceso/contactos.json";
    Button boton;
    ListView lista;
    ArrayList<Contacto> contactos;
    ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        boton = findViewById(R.id.button);
        lista = findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ContactosActivity.this,
                        "Móvil: " + contactos.get(position).getTelefono().getMovil(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descarga(WEB);
            }
        });
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog. STYLE_SPINNER );
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progreso.dismiss();
                try {
                    contactos = AnalisisJSON.analizarContactos(response);
                    Toast.makeText(ContactosActivity.this, "Descarga con éxito", Toast.LENGTH_SHORT).show();
                    mostrar();
                } catch (JSONException e) {
                    Toast.makeText(ContactosActivity.this,
                            "Error en el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progreso.dismiss();
                Toast.makeText(ContactosActivity.this,
                        "Error de conexión: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        if (contactos != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactos);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contactos);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast. LENGTH_SHORT ).show();
    }

}
