package com.example.usuario.ad_ejercicios_t4.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.usuario.ad_ejercicios_t4.R;
import com.example.usuario.ad_ejercicios_t4.util.MySingleton;

public class PrimitivaActivity extends AppCompatActivity {

    public static final String TAG = "MyTag";
    public static final String WEB = "http://192.168.1.20/acceso/primitiva.json";
    Button mButton;
    TextView mTextView;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitiva);

        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
    }

    public void onClick(View view) {
        descarga();
    }

    private void descarga() {


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
