package com.example.reposteriaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reposteriaapp.Adaptador.CarroAdaptador;
import com.example.reposteriaapp.Ayuda.ManejoCarro;
import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.Interface.CalcularList;
import com.example.reposteriaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter carroAdaptador;
    private RecyclerView rvCarro;
    private ManejoCarro manejoCarro;
    TextView txtTotal, txtVacio, btnPagar;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        manejoCarro = new ManejoCarro(this);

        iniciar();
        cargarLista();
        calcularTotal();
        Home();
    }

    private void Home(){
        FloatingActionButton btnHome = findViewById(R.id.fabHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manejoCarro.getListCart().isEmpty()){
                    Toast.makeText(CartActivity.this, "No Hay Productos en su Carro", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<ProductoDomain> list = manejoCarro.getListCart();
                    Toast.makeText(CartActivity.this, "Pago Realizado con Exito", Toast.LENGTH_SHORT).show();
                    manejoCarro.BorrarCarro(list);
                    startActivity(new Intent(CartActivity.this,MainActivity.class));
                }
            }
        });
    }

    private void iniciar() {
        rvCarro = findViewById(R.id.rvCarro);
        txtTotal = findViewById(R.id.txtTotal);
        txtVacio = findViewById(R.id.txtVacio);
        scrollView = findViewById(R.id.svCarro);
        btnPagar = findViewById(R.id.btnPagar);
    }

    private void cargarLista(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCarro.setLayoutManager(linearLayoutManager);
        carroAdaptador = new CarroAdaptador(manejoCarro.getListCart(), this, new CalcularList() {
            @Override
            public void calcular() {
                calcularTotal();
            }
        });

        rvCarro.setAdapter(carroAdaptador);
        if (manejoCarro.getListCart().isEmpty()){
            txtVacio.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            txtVacio.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calcularTotal(){
        double total = manejoCarro.getTotal();
        txtTotal.setText("Bs. " + String.valueOf(total));
    }
}