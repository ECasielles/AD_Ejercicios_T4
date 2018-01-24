package com.example.usuario.ad_ejercicios_t4.util;

import com.example.usuario.ad_ejercicios_t4.model.Contacto;
import com.example.usuario.ad_ejercicios_t4.model.Telefono;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by usuario on 22/01/18.
 */

public class AnalisisJSON {

    public static String analizarPrimitiva(JSONObject texto) throws JSONException {
        JSONArray jsonContenido;
        String tipo;
        StringBuilder cadena = new StringBuilder();
        tipo = texto.getString("info");
        jsonContenido = new JSONArray(texto.getString("sorteo"));
        cadena.append("Sorteos de la Primitiva:" + "\n");
        for (int i = 0; i < jsonContenido.length(); i++) {

        }
        return cadena.toString();
    }

    public static ArrayList<Contacto> analizarContactos(JSONObject respuesta) throws JSONException {
        JSONArray jAcontactos;
        JSONObject jOcontacto, jOtelefono;
        Contacto contacto;
        Telefono telefono;
        ArrayList<Contacto> personas;
        // añadir contactos (en JSON) a personas



        return personas;
    }

}
