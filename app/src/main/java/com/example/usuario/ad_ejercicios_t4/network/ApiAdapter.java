package com.example.usuario.ad_ejercicios_t4.network;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.usuario.ad_ejercicios_t4.model.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    //Trailing slash is needed
    private static final String BASE_URL = "https://api.github.com/html_url/";
    private static ApiService API_SERVICE;
    private static ApiAdapter apiAdapter;

    static {
        apiAdapter = new ApiAdapter();
    }

    private ApiAdapter() {
    }

    //Hay que sincronizar el método para admitir concurrencia
    public static synchronized ApiAdapter getInstance() {
        if (apiAdapter == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();

            //Crea la llamada al servidor
            API_SERVICE = retrofit.create(ApiService.class);
        }
        return apiAdapter;
    }

    public static void load(final String username, final OnApiListener listener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                getInstance();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                onLoaded(username, listener);
            }
        }.execute();

    }

    private static void onLoaded(final String username, final OnApiListener listener) {
        if (!username.equals("") && API_SERVICE != null)
            API_SERVICE.listRepos(username).enqueue(new Callback<ArrayList<Repository>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Repository>> call, @NonNull Response<ArrayList<Repository>> response) {
                    if (response.isSuccessful()) {
                        listener.setRepository(response.body());
                        listener.showMessage("Repositorios de " + username + " cargados con éxito");
                    } else {
                        StringBuilder message = new StringBuilder();
                        message.append("Error en la descarga: ").append(response.code());
                        if (response.body() != null)
                            message.append(response.body());
                        else
                            message.append(response.errorBody());
                        listener.showMessage(message.toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Repository>> call, @NonNull Throwable t) {
                    listener.dismiss();
                    // Log error here since request failed
                    listener.showMessage("Fallo en la comunicación" + t.getMessage());
                }
            });
        else {
            listener.dismiss();
            if (API_SERVICE != null)
                listener.showMessage("Introduce nombre de usuario");
            else
                listener.showMessage("Error de API");
        }
    }

}



