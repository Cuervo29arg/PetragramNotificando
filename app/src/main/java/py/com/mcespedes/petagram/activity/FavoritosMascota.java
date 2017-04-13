package py.com.mcespedes.petagram.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.adaptador.MascotaAdaptador;
import py.com.mcespedes.petagram.db.ConstructorMascotas;
import py.com.mcespedes.petagram.pojo.Mascota;

public class FavoritosMascota extends AppCompatActivity {

    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_mascota);

        //ocular el boton de favoritos del actionbar
        setContentView(R.layout.activity_favoritos_mascota);
        ImageView fav = (ImageView) findViewById(R.id.imgFav);
        fav.setVisibility(View.INVISIBLE);

        //agrega el action bar
        Toolbar miactionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miactionBar);

        //ocultar el nombre de la app que muestra por defecto
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //creando reciclerview
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFav);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();

    }

    //carga 5 elementos en el recyclerview
    public void inicializarListaMascotas(){
        /*mascotas.add(new Mascota("Animal 2", R.drawable.animal_2, 3));
        mascotas.add(new Mascota("Animal 3", R.drawable.animal_3,2));
        mascotas.add(new Mascota("Animal 4", R.drawable.animal_4,6));
        mascotas.add(new Mascota("Animal 5", R.drawable.animal_5,instagram_api_1));
        mascotas.add(new Mascota("Animal instagram_api_1", R.drawable.animal_1,3));*/

        ConstructorMascotas constructorMascotas = new ConstructorMascotas(this);
        mascotas = constructorMascotas.ObtenerMascotasFavoritas();

    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }
}