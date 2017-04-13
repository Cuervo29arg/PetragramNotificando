package com.christiancollado.petagram;

/**
 * Created by colla on 11/11/2016.
 */

public class Mascota {

    private String id;
    private String nombre;
    private int likes = 0;
    private String urlFoto;


    public Mascota(String nombre, String urlFoto, int likes) {
        this.nombre = nombre;
        this.urlFoto = urlFoto;
        this.likes = likes;
    }

    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}



/*    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    public int getId() { return idMascota;}
    public void setId(int id) {this.idMascota = id;}*/

