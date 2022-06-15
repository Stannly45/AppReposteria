package com.example.reposteriaapp.Domain;

public class CategoriaDomain {
    private String title;
    private String photo;

    public CategoriaDomain(String title, String photo) {
        this.title = title;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
