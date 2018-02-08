package com.example.usuario.ad_ejercicios_t4.network;


import com.example.usuario.ad_ejercicios_t4.model.Repository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Es la ruta del Endpoint y también la llamada (api + consulta)
 */
public interface ApiService {
    @GET("/users/{username}/repos")
    Call<ArrayList<Repository>> listRepos(@Path("username") String username);
}
