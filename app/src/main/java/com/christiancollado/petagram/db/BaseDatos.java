package com.christiancollado.petagram.db;

/**
 * Created by colla on 25/11/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.christiancollado.petagram.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context) {

        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota = "CREATE TABLE " +ConstantesBD.TABLA_MASCOTA +"(" +
                ConstantesBD.TABLE_LIKE_ID_MASCOTA     + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBD.MASCOTA_NOMBRE + " TEXT," +
                ConstantesBD.MASCOTA_FOTO   + " TEXT " +
                ")";

        String queryCrearTablaLike = "CREATE TABLE " + ConstantesBD.TABLE_LIKE +"(" +
                ConstantesBD.TABLE_LIKE_ID            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBD.TABLE_LIKE_ID_MASCOTA    + " INTEGER," +
                ConstantesBD.TABLE_LIKE_NLIKES         + " INTEGER, " +
                "FOREIGN KEY (" +  ConstantesBD.TABLE_LIKE_ID_MASCOTA + ")" +
                " REFERENCES " + ConstantesBD.TABLA_MASCOTA + "(" +ConstantesBD.TABLE_LIKE_ID_MASCOTA + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLike);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_LIKE);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLA_MASCOTA);

        onCreate(db);
    }

    }

    /*public BaseDatos(Context context) {
        super(context, "Mascotas", (CursorFactory)null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE MASCOTA(id INTEGER PRIMARY KEY, nombre TEXT, imagen INTEGER, favs INTEGER)";
        db.execSQL(queryCrearTablaMascota);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS MASCOTA");
        this.onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasMascotas() {
        ArrayList mascotas = new ArrayList();
        String query = "SELECT * FROM MASCOTA";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, (String[]) null);
        byte counter = 0;

        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setImg(registros.getInt(2));
            mascotaActual.setPuntuacion(registros.getInt(3));
            mascotas.add(mascotaActual);
            ++counter;
            if (counter == 5) {
                break;
            }
        }

        db.close();
        return mascotas;
    }


    public void insertarMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("MASCOTA", (String)null, contentValues);
        db.close();
    }

    public void cleanTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MASCOTA", (String)null, (String[])null);
        db.close();
    }

    public void actualizarFavsMascota(int idMascota, int favs) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE MASCOTA SET favs = " + String.valueOf(favs) + " WHERE " + "id" + " = " + idMascota;
        db.execSQL(query);
        db.close();
    }

    public ArrayList<Mascota> obtenerMascotasFavs(int topRanking) {
        ArrayList mascotas = new ArrayList();
        String query = "SELECT * FROM MASCOTA ORDER BY favs DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, (String[])null);
        byte counter = 0;

        while(registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setImg(registros.getInt(2));
            mascotaActual.setPuntuacion(registros.getInt(3));
            mascotas.add(mascotaActual);
            ++counter;
            if(counter == topRanking) {
                break;
            }
        }

        db.close();
        return mascotas;
    }*/

