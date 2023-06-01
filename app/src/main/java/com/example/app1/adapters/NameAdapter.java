package com.example.app1.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.ContactoActivity;
import com.example.app1.R;
import com.example.app1.entities.User;
import com.squareup.picasso.Picasso;

import java.util.List;



public class NameAdapter extends RecyclerView.Adapter {
    private List<User> items;
    public NameAdapter(List<User> items){
      this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_string, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User item = items.get(position);
        View view = holder.itemView;
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvUserName = view.findViewById(R.id.txtUserName);
        TextView tvPhone = view.findViewById(R.id.txtPhone);
        ImageView imgFoto = view.findViewById(R.id.imgFoto);
        ImageButton imgBtn = view.findViewById(R.id.imgElegir);

        String numero = item.numero; // Obtener el número del TextView
        tvName.setText(item.numero);
        Log.d("NUMEROAPP", "Número obtenido: " + numero);
        String imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numero + ".png";
        Log.d("URLAPP",imageUrl);
        Picasso.get().load(imageUrl).into(imgFoto);
        tvUserName.setText(item.nombre);
        tvPhone.setText(item.tipo);


        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    User item = items.get(adapterPosition);
                    String name = item.numero;
                    String username = item.nombre;
                    String phone = item.tipo;
                    int id = item.id;
                    // Obtener el contexto a través de la vista
                    Context context = v.getContext();

                    // Crear un Intent para iniciar la nueva actividad
                    Intent intent = new Intent(context, ContactoActivity.class);
                    intent.putExtra("id", id); // Agregar el ID a los extras
                    // Pasar los datos a través de los extras del Intent
                    intent.putExtra("name", name);
                    intent.putExtra("username", username);
                    intent.putExtra("phone", phone);
                    intent.putExtra("avatar", imageUrl);

                    // Iniciar la actividad
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class NameViewHolder extends RecyclerView.ViewHolder{

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
