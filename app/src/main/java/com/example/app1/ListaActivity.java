package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.app1.Clases.Anime;
import com.example.app1.adapters.NameAdapter;
import com.example.app1.entities.User;
import com.example.app1.services.UserService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaActivity extends AppCompatActivity {
    RecyclerView rvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        rvLista = findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));

        actualizarLista();
    }
    @Override
    protected void onResume(){
        super.onResume();
        actualizarLista();
    }
    private void actualizarLista() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://647788dc9233e82dd53bd0e9.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        Call<List<User>> call = service.getAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("MAIN_APP", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<User> data = response.body();
                    Log.i("MAIN_APP", new Gson().toJson(data));
                    NameAdapter adapter = new NameAdapter(data);
                    rvLista.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Manejar el error en caso de fallo en la solicitud
            }
        });
    }
}