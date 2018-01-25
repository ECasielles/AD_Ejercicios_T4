package com.example.usuario.ad_ejercicios_t4.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.usuario.ad_ejercicios_t4.R;
import com.example.usuario.ad_ejercicios_t4.util.AnalisisJSON;
import com.example.usuario.ad_ejercicios_t4.util.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class PrimitivaActivity extends AppCompatActivity {

    public static final String TAG = "MyTag";
    //public static final String WEB = "192.168.3.57/acceso/primitiva.json";
    //public static final String WEB = "http://portadaalta.mobi/acceso/primitiva.json";
    //public static final String WEB = "https://alumno.mobi/~alumno/superior/casielles/primitiva.json";
    public static final String WEB = "http://192.168.0.139/acceso/primitiva.json";

    Button mButton;
    TextView mTextView;
    RequestQueue mRequestQueue;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitiva);

        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        progressDialog = new ProgressDialog(this);
    }

    public void onClick(View view) {
        progressDialog.show();
        descarga();
    }

    private void descarga() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, WEB, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            mTextView.setText(AnalisisJSON.analizarPrimitiva(response));
                        } catch (JSONException e) {
                            Toast.makeText(PrimitivaActivity.this,
                                    "Error en el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(PrimitivaActivity.this,
                                "Error en la descarga: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        // Set the tag on the request.
        jsObjRequest.setTag( TAG );
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll( TAG );
        }
    }

}
