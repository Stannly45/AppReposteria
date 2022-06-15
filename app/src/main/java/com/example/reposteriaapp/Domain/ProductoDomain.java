package com.example.reposteriaapp.Domain;

import java.io.Serializable;

public class ProductoDomain implements Serializable {
    private String nombre, photo, descripcion;
    private Double precio;
    private int numberinCart;

    public ProductoDomain(String nombre, String photo, String descripcion, Double precio) {
        this.nombre = nombre;
        this.photo = photo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public ProductoDomain(String nombre, String photo, String descripcion, Double precio, int numberinCart) {
        this.nombre = nombre;
        this.photo = photo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.numberinCart = numberinCart;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}
