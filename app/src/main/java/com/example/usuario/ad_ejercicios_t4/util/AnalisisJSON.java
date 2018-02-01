package com.example.usuario.ad_ejercicios_t4.util;

import android.os.Environment;
import android.util.Log;

import com.example.usuario.ad_ejercicios_t4.model.ContactJSON;
import com.example.usuario.ad_ejercicios_t4.model.Contacto;
import com.example.usuario.ad_ejercicios_t4.model.Noticia;
import com.example.usuario.ad_ejercicios_t4.model.Telefono;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by usuario on 22/01/18.
 */

public class AnalisisJSON {

    public static String analizarPrimitiva(JSONObject texto) throws JSONException {
        JSONArray jsonContenido;
        JSONObject jsonObject;
        String tipo;
        StringBuilder cadena = new StringBuilder();
        tipo = texto.getString("info");
        jsonContenido = new JSONArray(texto.getString("sorteo"));
        cadena.append("Sorteos de la Primitiva:" + "\n");
        for (int i = 0; i < jsonContenido.length(); i++) {
            jsonObject = jsonContenido.getJSONObject(i);
            cadena.append("tipo: ").append(jsonObject.get("fecha")).append('\n')
                    .append(jsonObject.getInt("numero1"))
                    .append(jsonObject.getInt("numero2"))
                    .append(jsonObject.getInt("numero3"))
                    .append(jsonObject.getInt("numero4"))
                    .append(jsonObject.getInt("numero5"))
                    .append(jsonObject.getInt("numero6"))
                    .append('\n')
                    .append("complementario: ").append(jsonObject.get("complementario"))
                    .append('\n')
                    .append("reintegro: ").append(jsonObject.get("reintegro"))
                    .append('\n')
                    .append("numeroSorteo: ").append(jsonObject.get("numeroSorteo"))
                    .append('\n');
        }
        return cadena.toString();
    }

    public static ArrayList<Contacto> analizarContactos(JSONObject respuesta) throws JSONException {
        JSONArray jAcontactos;
        JSONObject jOcontacto, jOtelefono;
        Contacto contacto;
        Telefono telefono;
        ArrayList<Contacto> personas = new ArrayList<>();

        // añadir contactos (en JSON) a personas
        jAcontactos = new JSONArray(respuesta.getString("contactos"));
        for (int i = 0; i < jAcontactos.length(); i++) {
            jOcontacto = jAcontactos.getJSONObject(i);
            contacto = new Contacto();
            contacto.setNombre(jOcontacto.getString("nombre"));
            contacto.setDireccion(jOcontacto.getString("direccion"));
            contacto.setEmail(jOcontacto.getString("email"));
            jOtelefono = jOcontacto.getJSONObject("telefono");
            telefono = new Telefono();
            telefono.setCasa(jOtelefono.getString("casa"));
            telefono.setMovil(jOtelefono.getString("movil"));
            telefono.setTrabajo(jOtelefono.getString("trabajo"));
            contacto.setTelefono(telefono);
            personas.add(contacto);
        }

        return personas;
    }

    public static ContactJSON analizarContactosGSON(JSONObject respuesta) throws JSONException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(String.valueOf(respuesta), ContactJSON.class);
    }

    public static void escribirJSON(ArrayList<Noticia> noticias, String fichero) throws IOException, JSONException {
        OutputStreamWriter out;
        File miFichero;
        JSONObject objeto, rss;
        JSONArray lista;

        miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero);
        out = new FileWriter(miFichero);

        //crear objeto JSON
        objeto = new JSONObject();
        objeto.put("web", "http://www.alejandrosuarez.es/");
        objeto.put("link", "http://www.alejandrosuarez.es/feed/");
        lista = new JSONArray();

        //...

        out.write(rss.toString(4)); //tabulación de 4 caracteres
        out.flush();
        out.close();
        Log.i("info", objeto.toString());
    }

}
