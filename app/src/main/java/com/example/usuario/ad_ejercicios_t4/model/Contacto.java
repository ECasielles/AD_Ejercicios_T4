package com.example.usuario.ad_ejercicios_t4.model;


public class Contacto {
    private String nombre;
    private String direccion;
    private String email;
    private Telefono telefono;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Telefono getTelefono() {
        return telefono;
    }
    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        return nombre;
    }

}
