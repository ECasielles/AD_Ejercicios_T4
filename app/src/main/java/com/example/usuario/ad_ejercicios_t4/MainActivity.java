package com.example.usuario.ad_ejercicios_t4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usuario.ad_ejercicios_t4.activity.ContactosActivity;
import com.example.usuario.ad_ejercicios_t4.activity.ContactosGSONActivity;
import com.example.usuario.ad_ejercicios_t4.activity.CreacionJSONActivity;
import com.example.usuario.ad_ejercicios_t4.activity.PrimitivaActivity;
import com.example.usuario.ad_ejercicios_t4.activity.RetrofitActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPrimitiva:
                startActivity(new Intent(this, PrimitivaActivity.class));
                break;
            case R.id.btnContactos:
                startActivity(new Intent(this, ContactosActivity.class));
                break;
            case R.id.btnContactosGson:
                startActivity(new Intent(this, ContactosGSONActivity.class));
                break;
            case R.id.btnCreacionJson:
                startActivity(new Intent(this, CreacionJSONActivity.class));
                break;
            case R.id.btnRetrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;

        }
    }
}
