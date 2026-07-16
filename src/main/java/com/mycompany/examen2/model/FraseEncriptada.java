package com.mycompany.examen2.model;

public class FraseEncriptada {

    private int id;
    private String frase;
    private byte[] config; // Usamos byte[] para representar el formato BLOB

    public FraseEncriptada() {
    }

    public FraseEncriptada(int id, String frase, byte[] config) {
        this.id = id;
        this.frase = frase;
        this.config = config;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public byte[] getConfig() {
        return config;
    }

    public void setConfig(byte[] config) {
        this.config = config;
    }
}
