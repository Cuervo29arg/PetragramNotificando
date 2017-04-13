package py.com.mcespedes.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import py.com.mcespedes.petagram.pojo.Mascota;

import static py.com.mcespedes.petagram.db.ConstantesBaseDatos.DATABASE_NAME;
import static py.com.mcespedes.petagram.db.ConstantesBaseDatos.DATABASE_VERSION;
import static py.com.mcespedes.petagram.db.ConstantesBaseDatos.TABLE_MASCOTA;

/**
 * Created by root on 11/12/16.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE CONTACTO
        String queryCrearTablaMascota = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTA +"("+
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, "+
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER ,"+
                ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES + " INTEGER "+
                ")";
        db.execSQL(queryCrearTablaMascota);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerAllMascotas(){
        ArrayList<Mascota> mascotas =  new ArrayList<>();

        String sql = "SELECT * FROM " +ConstantesBaseDatos.TABLE_MASCOTA;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(sql, null);

        while (registros.moveToNext()){
            Mascota c = new Mascota();
            //c.setId(registros.getInt(0));
            c.setNombre(registros.getString(1));
            //c.setImagen(registros.getInt(2));
            c.setLikes(registros.getInt(3));
            mascotas.add(c);

        }
        registros.close();
        db.close();

        return  mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public  void insertarLikes( Mascota mascota){
        int likes = obtenerLikes(mascota) + 1 ;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES, likes);
        db.update(TABLE_MASCOTA, values, ConstantesBaseDatos.TABLE_MASCOTA_ID + " = ?",
                new String[]{String.valueOf(mascota.getId())});
    }

    public int obtenerLikes(Mascota mascota){
        int likes = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "select "+ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES
                +" from "+ ConstantesBaseDatos.TABLE_MASCOTA+
                " where "+ConstantesBaseDatos.TABLE_MASCOTA_ID +" = "+ mascota.getId();
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToNext()){
            likes = c.getInt(0);
        }
        c.close();
        db.close();

        return likes;
    }

    public ArrayList<Mascota> obtenerfavoritos(){

        ArrayList<Mascota> mascotas =  new ArrayList<>();

        String sql = "SELECT * FROM " +
                ConstantesBaseDatos.TABLE_MASCOTA +
                " order by " +ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES +
                " desc limit 5";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(sql, null);

        while (registros.moveToNext()){
            Mascota c = new Mascota();
            //c.setId(registros.getInt(0));
            c.setNombre(registros.getString(1));
            //c.setImagen(registros.getInt(2));
            c.setLikes(registros.getInt(3));
            mascotas.add(c);

        }
        registros.close();
        db.close();

        return  mascotas;
    }
}

