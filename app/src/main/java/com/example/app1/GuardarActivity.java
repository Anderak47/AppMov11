package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app1.entities.User;
import com.example.app1.services.UserService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GuardarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        Button btnGuar = this.findViewById(R.id.btnGuardar);
        btnGuar.setOnClickListener(view -> {
            EditText ediName = findViewById(R.id.editName);
            EditText ediUserName = findViewById(R.id.editUserName);
            EditText ediTipo = findViewById(R.id.editPhone);


            String name = ediName.getText().toString();
            String userName = ediUserName.getText().toString();
            String tipo = ediTipo.getText().toString();

            // Crear el objeto User con los valores ingresados
            User user = new User();
            user.numero = name;
            user.nombre = userName;
            user.tipo = tipo;

            // Llamar al servicio para guardar el nuevo usuario
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://647788dc9233e82dd53bd0e9.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService service = retrofit.create(UserService.class);
            Call<User> call = service.create(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        // Usuario guardado exitosamente
                        User newUser = response.body();
                    } else {
                        // Manejar el error en caso de que no se haya podido guardar el usuario
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Manejar el error de la llamada al servicio
                }
            });
            Toast.makeText(getApplicationContext(), "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
            //limpiar datos
            ediName.setText("");
            ediUserName.setText("");
            ediTipo.setText("");
        });
    }
}