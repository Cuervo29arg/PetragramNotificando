package com.christiancollado.petagram;

/**
 * Created by colla on 29/11/2016.
 */

public class User {

    private String user;
    private String nombre;
    private String urlFotoPerfil;
    private String id;

    public User () {

    }

    public User(String user, String nombre, String urlFotoPerfil, String id) {
        this.user = user;
        this.nombre = nombre;
        this.urlFotoPerfil = urlFotoPerfil;
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }






    }



