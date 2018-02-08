package com.example.usuario.ad_ejercicios_t4.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.ad_ejercicios_t4.R;
import com.example.usuario.ad_ejercicios_t4.RecyclerTouchListener;
import com.example.usuario.ad_ejercicios_t4.RepositoryAdapter;
import com.example.usuario.ad_ejercicios_t4.model.Repository;
import com.example.usuario.ad_ejercicios_t4.network.ApiAdapter;
import com.example.usuario.ad_ejercicios_t4.network.OnApiListener;

import java.util.ArrayList;

public class RetrofitActivity extends AppCompatActivity implements OnApiListener {
    ArrayList<Repository> repositories;
    EditText nombreUsuario;
    Button botonDescarga;
    RecyclerView rvRepos;
    RepositoryAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        adapter = new RepositoryAdapter(repositories);
        nombreUsuario = findViewById(R.id.editText);
        botonDescarga = findViewById(R.id.button);
        rvRepos = findViewById(android.R.id.list);
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        rvRepos.addOnItemTouchListener(new RecyclerTouchListener(this, rvRepos,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        showMessage("Single Click on position:" + position);
                        //Uri uri = Uri.parse(adapter.get(position).getUrl());
                        Uri uri = Uri.parse(adapter.get(position).getHtmlUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        if (intent.resolveActivity(getPackageManager()) != null)
                            startActivity(intent);
                        else
                            showMessage("No hay un navegador");
                    }
                    @Override
                    public void onLongClick(View view, int position) {
                        showMessage("Long press on position :" + position);
                    }
                }));
    }

    public void onClick(View view) {
        String username = nombreUsuario.getText().toString();
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        //Llamo al ApiAdapter
        ApiAdapter.load(username, this);
    }

    @Override
    public void setRepository(ArrayList<Repository> repositories) {
        adapter.setRepository(repositories);
    }

    @Override
    public void dismiss() {
        progressDialog.dismiss();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
