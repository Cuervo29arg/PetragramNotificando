package com.christiancollado.petagram.resAPI.model;

/**
 * Created by colla on 09/12/2016.
 */

public class UsuarioResponse {

    private String id_usuario_instagram;

    private String id_dispositivo;

    public UsuarioResponse(String id_usuario_instagram, String id_dispositivo) {
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_dispositivo = id_dispositivo;
    }

    public UsuarioResponse() {

    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
}
