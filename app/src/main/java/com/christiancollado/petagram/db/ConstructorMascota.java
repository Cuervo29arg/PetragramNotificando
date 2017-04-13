package com.christiancollado.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.christiancollado.petagram.Mascota;

import java.util.ArrayList;

/**
 * Created by colla on 25/11/2016.
 */

public class ConstructorMascota {

    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascota(Context context) {
        this.context = context;
    }
}

    /*private Context context;

    public ConstructorMascota(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerMascotas() {
        BaseDatos db = new BaseDatos(this.context);
        this.cleanDB(db);
        this.insertarCincoMascotas(db);
        return db.obtenerTodasMascotas();
    }

    public void insertarCincoMascotas(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", "0");
        contentValues.put("nombre", "Nala");
        contentValues.put("imagen", Integer.valueOf(2130837599));
        contentValues.put("favs", "3");
        db.insertarMascota(contentValues);



        contentValues = new ContentValues();
        contentValues.put("id", "1");
        contentValues.put("nombre", "Bunny");
        contentValues.put("imagen", Integer.valueOf(2130837597));
        contentValues.put("favs", "1");
        db.insertarMascota(contentValues);

        contentValues.put("id", "2");
        contentValues.put("nombre", "Chop");
        contentValues.put("imagen", Integer.valueOf(2130837595));
        contentValues.put("favs", "2");
        db.insertarMascota(contentValues);


        contentValues.put("id", "3");
        contentValues.put("nombre", "Coco");
        contentValues.put("imagen", Integer.valueOf(2130837596));
        contentValues.put("favs", "1");
        db.insertarMascota(contentValues);


        contentValues.put("id", "4");
        contentValues.put("nombre", "Java");
        contentValues.put("imagen",  Integer.valueOf(2130837594));
        contentValues.put("favs", "5");
        db.insertarMascota(contentValues);

    }

    private void cleanDB(BaseDatos db) {
        db.cleanTable();
    }

    public void favMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(this.context);
        db.actualizarFavsMascota(mascota.getId(), mascota.getPuntuacion());
    }

    public ArrayList<Mascota> obtenerMascotasFavs(){
        BaseDatos db = new BaseDatos(this.context);
        return db.obtenerMascotasFavs(5);
    }
}*/
