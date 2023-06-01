package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

import com.example.app1.entities.User;
import com.example.app1.services.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCam = this.findViewById(R.id.btnCambiar);
        btnCam.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GuardarActivity.class);
            startActivity(intent);

        });

        Button btnList = findViewById(R.id.btnLista);
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
            startActivity(intent);
        });
    }
}