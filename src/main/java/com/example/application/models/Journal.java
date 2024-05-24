package com.example.application.models;

public class Journal {
    private String name;

    public Journal() {
        // Prazan konstruktor potreban za Vaadin
    }

    public Journal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
