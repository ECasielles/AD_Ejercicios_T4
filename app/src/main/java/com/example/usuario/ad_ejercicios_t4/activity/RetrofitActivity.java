package com.example.usuario.ad_ejercicios_t4.activity;

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
import com.example.usuario.ad_ejercicios_t4.model.Git;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    EditText nombreUsuario;
    Button botonDescarga;
    RecyclerView rvRepos;
    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        adapter = new RepositoryAdapter();
        nombreUsuario = findViewById(R.id.edittext);
        botonDescarga = findViewById(R.id.button);
        rvRepos = findViewById(android.R.id.list);
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        rvRepos.addOnItemTouchListener(new RecyclerTouchListener(this, rvRepos,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Toast.makeText(getApplicationContext(), "position " + position + " was clicked!",
                                Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse(adapter.get(position).getUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        if (intent.resolveActivity(getPackageManager()) != null)
                            startActivity(intent);
                        else
                            Toast.makeText(getApplicationContext(), "No hay un navegador",
                                    Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(RetrofitActivity.this,
                                "Long press on position: " + position,
                                Toast.LENGTH_LONG).show();
                    }
                }));
    }

    public void onClick(View view) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        Git client = retrofit.create(Git.class);

        // Fetch a list of the Github repositories.
        Call<List<Git>> call = client.reposForUser("fs-opensource");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Git>>() {
            @Override
            public void onResponse(Call<List<Git>> call, Response<List<Git>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
            }

            @Override
            public void onFailure(Call<List<Git>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
            }
        });

    }
}
