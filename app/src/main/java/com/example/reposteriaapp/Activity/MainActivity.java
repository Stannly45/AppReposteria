package com.example.reposteriaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reposteriaapp.Adaptador.CategoriaAdaptador;
import com.example.reposteriaapp.Adaptador.ProductoAdaptador;
import com.example.reposteriaapp.Domain.CategoriaDomain;
import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterCategoria, adapterProducto;
    private RecyclerView rvCategoria, rvProducto;
    private EditText txtSearch;
    private ArrayList<ProductoDomain> listSearch = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ProductoDomain> productoList = new ArrayList<>();
        productoList.add(new ProductoDomain("Dona Bañada \nen Chocolate", "prod_1", "Donas Rellenas con dulce de Leche, bañada en cocholate", 10.0));
        productoList.add(new ProductoDomain("Torta de Frutilla", "prod_2", "Torta de Frutilla, remojada en jugo de Naranja, con una cubierta de crema de Vainilla, adornada con chocolate y Frutilla", 95.5));
        productoList.add(new ProductoDomain("Galleta de Chocolate", "prod_3", "Galetas de Mantequilla con chispas de Cocholate", 12.5));
        productoList.add(new ProductoDomain("Flan", "prod_4", "Flan de Vainilla, con cubierta de Caramelo", 7.5));


        recyclerViewCategoria();
        recyclerViewProducto(productoList);
        Carro();
        Search(productoList);


    }
    private void Search(ArrayList<ProductoDomain> productoList){
        txtSearch = findViewById(R.id.txtSearch);

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listSearch.clear();
                for (int j = 0; j < productoList.size(); j++){
                    if (productoList.get(j).getNombre().toUpperCase(Locale.ROOT).contains(charSequence.toString().toUpperCase(Locale.ROOT))){
                        listSearch.add(productoList.get(j));
                    }
                }
                recyclerViewProducto(listSearch);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void Carro(){
        FloatingActionButton btnCarro = findViewById(R.id.fabCarro);
        btnCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewCategoria() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvCategoria = findViewById(R.id.rvCategorias);
        rvCategoria.setLayoutManager(linearLayoutManager);

        ArrayList<CategoriaDomain> categorias = new ArrayList<>();
        categorias.add(new CategoriaDomain("Dona","cat_r_1"));
        categorias.add(new CategoriaDomain("Torta","cat_r_2"));
        categorias.add(new CategoriaDomain("Pie","cat_r_3"));
        categorias.add(new CategoriaDomain("Galleta","cat_r_4"));
        categorias.add(new CategoriaDomain("Flan","cat_r_5"));

        adapterCategoria = new CategoriaAdaptador(categorias);
        rvCategoria.setAdapter(adapterCategoria);
    }

    private void recyclerViewProducto(ArrayList<ProductoDomain> productoList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView.LayoutManager gridLayout  = new GridLayoutManager(this,2);
        rvProducto = findViewById(R.id.rvProductos);
        rvProducto.setLayoutManager(gridLayout);


        adapterProducto = new ProductoAdaptador(productoList);
        rvProducto.setAdapter(adapterProducto);
    }

}