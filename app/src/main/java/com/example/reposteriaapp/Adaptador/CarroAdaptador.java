package com.example.reposteriaapp.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reposteriaapp.Ayuda.ManejoCarro;
import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.Interface.CalcularList;
import com.example.reposteriaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarroAdaptador extends RecyclerView.Adapter<CarroAdaptador.CarroHolder> {
    private ArrayList<ProductoDomain> productoList;
    private ManejoCarro manejoCarro;
    private CalcularList calcularList;

    public CarroAdaptador(ArrayList<ProductoDomain> productoList, Context context, CalcularList calcularList) {
        this.productoList = productoList;
        this.manejoCarro = new ManejoCarro(context);
        this.calcularList = calcularList;
    }

    @NonNull
    @Override
    public CarroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carro, parent, false);
        return new CarroHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CarroHolder holder, int position) {
        holder.txtNombreItem.setText(productoList.get(position).getNombre());
        holder.txtPrecioItem.setText(String.valueOf(productoList.get(position).getPrecio()));
        holder.txtTotalItem.setText(String.valueOf(productoList.get(position).getNumberinCart()*productoList.get(position).getPrecio()));
        holder.txtCantidadItem.setText(String.valueOf(productoList.get(position).getNumberinCart()));

        int photoId = holder.itemView.getContext().getResources().getIdentifier(productoList.get(position).getPhoto(),"drawable",holder.itemView.getContext().getPackageName());

        Picasso.with(holder.itemView.getContext()).load(photoId).into(holder.imgPhotoItem);

        holder.imgMasItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manejoCarro.aumentarCarro(productoList, position, new CalcularList() {
                    @Override
                    public void calcular() {
                        notifyDataSetChanged();
                        calcularList.calcular();
                    }
                });
            }
        });

        holder.imgMenosItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manejoCarro.reducirCarro(productoList, position, new CalcularList() {
                    @Override
                    public void calcular() {
                        notifyDataSetChanged();
                        calcularList.calcular();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class CarroHolder extends RecyclerView.ViewHolder{
        TextView txtNombreItem, txtPrecioItem;
        ImageView imgPhotoItem, imgMenosItem, imgMasItem;
        TextView txtTotalItem, txtCantidadItem;
        public CarroHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreItem = itemView.findViewById(R.id.txtNombreItem);
            txtPrecioItem = itemView.findViewById(R.id.txtPrecioItemCart);
            txtTotalItem = itemView.findViewById(R.id.txtTotalItemCart);
            txtCantidadItem = itemView.findViewById(R.id.txtNumberItem);
            imgMasItem = itemView.findViewById(R.id.imgMasCart);
            imgMenosItem = itemView.findViewById(R.id.imgMenosCart);
            imgPhotoItem = itemView.findViewById(R.id.imgPhotoItem);
        }
    }
}
