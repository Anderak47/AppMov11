package com.example.app1.Clases;

public class Persona {
    private String nombre;
    private String descripcion;
    private String foto;
    private boolean estrella;

    public Persona(String nombre, String descripcion, String foto, boolean estrella) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.estrella = estrella;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEstrella() {
        return estrella;
    }

    public void setEstrella(boolean estrella) {
        this.estrella = estrella;
    }
}
