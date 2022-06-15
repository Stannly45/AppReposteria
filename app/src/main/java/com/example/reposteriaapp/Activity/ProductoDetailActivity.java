package com.example.reposteriaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reposteriaapp.Ayuda.ManejoCarro;
import com.example.reposteriaapp.Domain.ProductoDomain;
import com.example.reposteriaapp.R;
import com.squareup.picasso.Picasso;

public class ProductoDetailActivity extends AppCompatActivity {

    private TextView btnAgregarCarro;
    private TextView txtNombreDetalle, txtPrecioDetalle, txtCantidad, txtDescripcion;
    private ImageView imgPhotoDetalle, imgMas, imgMenos;
    private ProductoDomain producto;
    private ManejoCarro manejoCarro;
    int cantidad = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detail);

        manejoCarro = new ManejoCarro(this);
        iniciar();
        obtenerDatos();
    }

    private void obtenerDatos() {
        producto = (ProductoDomain) getIntent().getSerializableExtra("producto");
        int photoId = this.getResources().getIdentifier(producto.getPhoto(),"drawable",this.getPackageName());

        Picasso.with(this).load(photoId).into(imgPhotoDetalle);
        txtNombreDetalle.setText(producto.getNombre());
        txtPrecioDetalle.setText("Bs. "+producto.getPrecio());
        txtDescripcion.setText(producto.getDescripcion());
        txtCantidad.setText(String.valueOf(cantidad));

        imgMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad < 10){
                    cantidad++;
                    txtCantidad.setText(String.valueOf(cantidad));
                }else{
                    Toast.makeText(ProductoDetailActivity.this, "No se Puede Aumentar mas Productos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad > 1){
                    cantidad--;
                    txtCantidad.setText(String.valueOf(cantidad));
                }else{
                    Toast.makeText(ProductoDetailActivity.this, "No se Puede Reducir mas Productos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAgregarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.setNumberinCart(cantidad);
                manejoCarro.insertarProducto(producto);
            }
        });
    }

    private void iniciar() {
        btnAgregarCarro = findViewById(R.id.btnAgregarCarro);
        txtNombreDetalle = findViewById(R.id.txtNombreDetalle);
        txtPrecioDetalle = findViewById(R.id.txtPrecioDetalle);
        txtDescripcion = findViewById(R.id.txtDescripcionDetalle);
        txtCantidad = findViewById(R.id.txtCantidad);
        imgMas = findViewById(R.id.imgMas);
        imgMenos = findViewById(R.id.imgMenos);
        imgPhotoDetalle = findViewById(R.id.imgPhotoDetalle);
    }
}