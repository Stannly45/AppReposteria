package com.example.reposteriaapp.Adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reposteriaapp.Activity.ProductoDetailActivity;
import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductoAdaptador extends RecyclerView.Adapter<ProductoAdaptador.ProductoHolder> {
    ArrayList<ProductoDomain> productoList;

    public ProductoAdaptador(ArrayList<ProductoDomain> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ProductoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ProductoHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoHolder holder, int position) {
        holder.Nombre.setText(productoList.get(position).getNombre());
        holder.precio.setText(String.valueOf(productoList.get(position).getPrecio()));
        String photoUrl = productoList.get(position).getPhoto();
        int photoId = holder.itemView.getContext().getResources().getIdentifier(photoUrl,"drawable",holder.itemView.getContext().getPackageName());

        Picasso.with(holder.itemView.getContext()).load(photoId).into(holder.photo);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(holder.itemView.getContext(), ProductoDetailActivity.class);
                intentDetail.putExtra("producto", productoList.get(position));
                holder.itemView.getContext().startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ProductoHolder extends RecyclerView.ViewHolder {
        TextView Nombre, precio;
        ImageView photo;
        TextView btnAdd;
        public ProductoHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.txtNombre);
            precio = itemView.findViewById(R.id.txtPrecio);
            photo = itemView.findViewById(R.id.imgProducto);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}
