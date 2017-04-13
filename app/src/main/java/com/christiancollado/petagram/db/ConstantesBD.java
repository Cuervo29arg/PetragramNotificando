package com.christiancollado.petagram.db;

/**
 * Created by colla on 25/11/2016.
 */

public final class ConstantesBD {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MASCOTA";
    public static final String TABLA_MASCOTA = "mascota";
    public static final String MASCOTA_ID = "id";
    public static final String MASCOTA_NOMBRE = "nombre";
    public static final String MASCOTA_FOTO = "imagen";
    public static final String MASCOTA_FAVS = "favs";

    public static final String TABLE_LIKE = "like";
    public static final String TABLE_LIKE_ID = "id";
    public static final String TABLE_LIKE_ID_MASCOTA = "id_mascota";
    public static final String TABLE_LIKE_NLIKES = "likes";



    public ConstantesBD() {
    }
}
