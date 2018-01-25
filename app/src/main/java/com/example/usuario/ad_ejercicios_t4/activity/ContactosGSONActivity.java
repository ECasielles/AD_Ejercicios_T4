package com.example.usuario.ad_ejercicios_t4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.usuario.ad_ejercicios_t4.R;
import com.example.usuario.ad_ejercicios_t4.model.Contacto;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ContactosGSONActivity extends AppCompatActivity {
    public static final String WEB = "http://192.168.1.20/acceso/contacts.json";
    //public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayList<Contacto> contacts;
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        contacts = new ArrayList<Contacto>();
    }

    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {

    }
}
