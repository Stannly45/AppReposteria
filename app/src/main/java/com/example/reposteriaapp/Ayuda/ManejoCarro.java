package com.example.reposteriaapp.Ayuda;

import android.content.Context;
import android.widget.Toast;

import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.Interface.CalcularList;

import java.util.ArrayList;

public class ManejoCarro {
    private Context context;
    private TinyDB tinyDB;

    public ManejoCarro(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void BorrarCarro(ArrayList<ProductoDomain> list){
        list.clear();
        tinyDB.putListObject("CartList", list);
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

    public void aumentarCarro(ArrayList<ProductoDomain> listProductos, int position, CalcularList calcularList){
        int cantidad = listProductos.get(position).getNumberinCart();
        if (cantidad<11){
            listProductos.get(position).setNumberinCart(cantidad+1);
            tinyDB.putListObject("CartList",listProductos);
            calcularList.calcular();
        }else {
            Toast.makeText(context, "No se puede Aumentar mas Cantidad de " + listProductos.get(position).getNombre(), Toast.LENGTH_SHORT).show();
        }
    }

    public void reducirCarro(ArrayList<ProductoDomain> listProductos, int position, CalcularList calcularList){
        int cantidad = listProductos.get(position).getNumberinCart();
        if (listProductos.size() > 1){
            if (cantidad==1){
                Toast.makeText(context, listProductos.get(position).getNombre() + " Eliminada", Toast.LENGTH_SHORT).show();
                listProductos.remove(position);
            }else {
                listProductos.get(position).setNumberinCart(cantidad-1);
            }
        }else{
            if (cantidad==1){
                Toast.makeText(context, listProductos.get(position).getNombre() + " Eliminada", Toast.LENGTH_SHORT).show();
                listProductos.clear();

            }else {
                listProductos.get(position).setNumberinCart(cantidad-1);
            }
        }

        tinyDB.putListObject("CartList",listProductos);
        calcularList.calcular();

    }

    public double getTotal(){
        ArrayList<ProductoDomain> listProductos = getListCart();
        double total = 0;
        for (int i = 0; i < listProductos.size(); i++){
            total = total + (listProductos.get(i).getNumberinCart()*listProductos.get(i).getPrecio());
        }
        return  total;
    }
}
