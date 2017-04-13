package py.com.mcespedes.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.pojo.Mascota;

/**
 * Created by root on 11/12/16.
 */

public class ConstructorMascotas {

    private static final int LIKE = 0;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> ObtenerDatos(){
        ArrayList<Mascota> mascotas =  new ArrayList<>();
        BaseDatos db = new BaseDatos(context);
        insertarContactos(db);
        mascotas = db.obtenerAllMascotas();
        return mascotas;
    }

    public void insertarContactos(BaseDatos db){

        ContentValues contentValues =  new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE , "Animal instagram_api_1");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES , 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO , R.drawable.animal_1);

        db.insertarMascota(contentValues);

        contentValues =  new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE , "Animal 2");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES , 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO , R.drawable.animal_2);

        db.insertarMascota(contentValues);

        contentValues =  new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE , "Animal 3");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NUMERO_LIKES , 0);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO , R.drawable.animal_3);

        db.insertarMascota(contentValues);

    }

    public void darLike(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        bd.insertarLikes(mascota);
    }

    public int obtenerLikes(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        return bd.obtenerLikes(mascota);
    }

    public ArrayList<Mascota> ObtenerMascotasFavoritas(){
        ArrayList<Mascota> mascotas =  new ArrayList<>();
        BaseDatos db = new BaseDatos(context);
        insertarContactos(db);
        mascotas = db.obtenerfavoritos();
        return mascotas;
    }

}
