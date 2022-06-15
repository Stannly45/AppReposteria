package com.example.reposteriaapp.Ayuda;

import android.content.Context;
import android.widget.Toast;

import com.example.reposteriaapp.Domain.ProductoDomain;

import java.util.ArrayList;

public class ManejoCarro {
    private Context context;
    private TinyDB tinyDB;

    public ManejoCarro(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertarProducto(ProductoDomain item){
        ArrayList<ProductoDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getNombre().equals(item.getNombre())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberinCart(item.getNumberinCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Agregado a tu Carro de Compras", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ProductoDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
}
