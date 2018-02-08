package com.example.usuario.ad_ejercicios_t4.network;

import com.example.usuario.ad_ejercicios_t4.model.Repository;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public interface OnApiListener {
    void setRepository(ArrayList<Repository> body);

    void showMessage(String s);

    void dismiss();
}
