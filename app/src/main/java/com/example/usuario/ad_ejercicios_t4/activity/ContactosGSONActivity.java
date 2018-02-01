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
import com.example.usuario.ad_ejercicios_t4.model.ContactJSON;
import com.example.usuario.ad_ejercicios_t4.util.AnalisisJSON;
import com.example.usuario.ad_ejercicios_t4.util.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ContactosGSONActivity extends AppCompatActivity {
    //public static final String WEB = "http://192.168.3.57/acceso/contacts.json";
    public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayAdapter<ContactJSON.Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_gson);

        boton = findViewById(R.id.button);
        lista = findViewById(R.id.listView);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(
                        ContactosGSONActivity.this,
                        "Móvil: " + adapter.getItem(position).getPhone(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<ContactJSON.Contact>());
        lista.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    progreso.dismiss();
                    adapter.clear();
                    ContactJSON contactJSON = AnalisisJSON.analizarContactosGSON(response);
                    ArrayList<ContactJSON.Contact> contacts = contactJSON.getContacts();
                    adapter.addAll(contacts);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
            }
        });
    }

}
