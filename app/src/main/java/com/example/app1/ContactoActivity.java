package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1.adapters.NameAdapter;
import com.example.app1.entities.User;
import com.example.app1.services.UserService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactoActivity extends AppCompatActivity {
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String phone = intent.getStringExtra("phone");
        String avatar = intent.getStringExtra("avatar");

        EditText tvName = findViewById(R.id.textName);
        EditText tvUsername = findViewById(R.id.textUserName);
        EditText tvPhone = findViewById(R.id.textTelefono);
        ImageView imgAvatar = findViewById(R.id.imgAvatar);


        tvName.setText(name);
        tvUsername.setText(username);
        tvPhone.setText(phone);

        Picasso.get().load(avatar).into(imgAvatar);

        Button btnLlamar = findViewById(R.id.btnLlamar);
        btnLlamar.setOnClickListener(view -> {
            String phoneNumber = phone; // Reemplaza con el número de teléfono al que deseas llamar
            Intent intent2 = new Intent(Intent.ACTION_CALL);
            intent2.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent2);
        });

        Button btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = tvName.getText().toString();
                String newUsername = tvUsername.getText().toString();
                String newTelefono = tvPhone.getText().toString();
                User updatedUser = new User(newName, newUsername, newTelefono);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://647788dc9233e82dd53bd0e9.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = retrofit.create(UserService.class);
                Call<User> call = userService.update(id, updatedUser);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // Actualización exitosa
                            User updatedUser = response.body();

                            // Realiza cualquier acción adicional o actualización de la interfaz aquí

                            // Muestra un mensaje o realiza alguna acción adicional
                            Toast.makeText(ContactoActivity.this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error en la actualización
                            // Maneja el error de acuerdo a tus necesidades
                            Toast.makeText(ContactoActivity.this, "Error al actualizar el contacto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Error en la solicitud
                        // Maneja el error de acuerdo a tus necesidades
                        Toast.makeText(ContactoActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }

                });
                finish();
            }
        });


        Button btnEliminar = findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://647788dc9233e82dd53bd0e9.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = retrofit.create(UserService.class);

                Call<Void> call = userService.delete(id);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Eliminación exitosa
                            // Realiza cualquier acción adicional o actualización de la interfaz aquí

                            // Actualiza la lista de usuarios llamando a getAllUser() nuevamente
                            Call<List<User>> userListCall = userService.getAllUser();
                            userListCall.enqueue(new Callback<List<User>>() {
                                @Override
                                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                    if (response.isSuccessful()) {
                                        List<User> userList = response.body();
                                        // Actualiza tu RecyclerView o cualquier otra vista con la lista actualizada

                                    } else {
                                        // Error al obtener la lista actualizada
                                        // Maneja el error de acuerdo a tus necesidades
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<User>> call, Throwable t) {
                                    // Error en la solicitud
                                    // Maneja el error de acuerdo a tus necesidades
                                }
                            });

                            // Finaliza la actividad de ContactoActivity
                            finish();
                        } else {
                            // Ocurrió un error en la eliminación
                            // Maneja el error de acuerdo a tus necesidades
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Error en la solicitud
                        // Maneja el error de acuerdo a tus necesidades
                    }
                });
            }
        });


    }

}