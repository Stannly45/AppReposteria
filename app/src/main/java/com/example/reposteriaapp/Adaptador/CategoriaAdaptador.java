package com.example.reposteriaapp.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reposteriaapp.Domain.CategoriaDomain;
import com.example.reposteriaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriaAdaptador extends RecyclerView.Adapter<CategoriaAdaptador.CategoriaHolder> {
    ArrayList<CategoriaDomain> categoriaList;

    public CategoriaAdaptador(ArrayList<CategoriaDomain> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @NonNull
    @Override
    public CategoriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria,parent,false);
        return new CategoriaHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaHolder holder, int position) {
        holder.categoriaNombre.setText(categoriaList.get(position).getTitle());
        String photoUrl = categoriaList.get(position).getPhoto();
        int photoId = holder.itemView.getContext().getResources().getIdentifier(photoUrl,"drawable",holder.itemView.getContext().getPackageName());

        Picasso.with(holder.itemView.getContext()).load(photoId).into(holder.categoriaPhoto);
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public class CategoriaHolder extends RecyclerView.ViewHolder {
        TextView categoriaNombre;
        ImageView categoriaPhoto;
        ConstraintLayout categoriaLayout;
        public CategoriaHolder(@NonNull View itemView) {
            super(itemView);
            categoriaNombre = itemView.findViewById(R.id.txtCategoria);
            categoriaPhoto = itemView.findViewById(R.id.imgCategoria);
            categoriaLayout = itemView.findViewById(R.id.CategoriaLayout);
        }
    }
}
