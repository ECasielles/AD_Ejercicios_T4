package com.example.usuario.ad_ejercicios_t4.network;


import com.example.usuario.ad_ejercicios_t4.model.Git;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/users/{user}/repos")
    Call<List<Git>> reposForUser(@Path("user") String user);
}
