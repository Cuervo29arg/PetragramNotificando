package com.christiancollado.petagram;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.christiancollado.petagram.db.ConstructorMascota;
import com.christiancollado.petagram.presenter.IRecyclerViewFragmentPresenter;
import com.christiancollado.petagram.presenter.IRecyclerViewFragmentView;
import com.christiancollado.petagram.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FavsActivity extends AppCompatActivity implements IRecyclerViewFragmentView {

    //ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private Toolbar toolbar;
    private IRecyclerViewFragmentPresenter presenter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        this.setTitle("Mascotas favoritas");

        toolbar = (Toolbar) findViewById(R.id.aabToolbar);
        setSupportActionBar(toolbar);

        //actionbar.setLogo(R.drawable.logo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ConstructorMascota constructorMascotas = new ConstructorMascota(this.getApplicationContext());

        //mascotas = constructorMascotas.obtenerMascotasFavs();

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
       // llm.setOrientation(LinearLayoutManager.VERTICAL);

        presenter = new RecyclerViewFragmentPresenter(this, this.getBaseContext());


        //listaMascotas.setLayoutManager(llm);

        //MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,this);
        //listaMascotas.setAdapter(adaptador);



        //inicializarListaMascotas();
        //inicializarAdaptador();
    }

    @Override
    public void generarLLV() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager glm = new GridLayoutManager(this,2);

        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public void generarGL() {
        GridLayoutManager glm = new GridLayoutManager(this,2);
        listaMascotas.setLayoutManager(glm);
    }

    @Override
    public MascotaAdaptador crearAdapter(ArrayList<Mascota> mascotas) {

        Collections.sort(mascotas, new Comparator<Mascota>(){
            @Override
            public int compare(Mascota m1, Mascota m2) {
                return (m1.getLikes() > m2.getLikes()) ? -1: (m1.getLikes() > m2.getLikes()) ? 1:0;
            }
        });

        ArrayList<Mascota> fin = new ArrayList<Mascota>();

        for (int i=0; i< 5; i++){
            fin.add(mascotas.get(i));
        }

        MascotaAdaptador adaptador = new MascotaAdaptador(fin, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favs, menu);
        return true;
    }
}

        /*@Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.favoritos:
                    //TODO item click
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }*/

        /*public void inicializarListaMascotas(){
            mascotas = new ArrayList<Mascota>();
            mascotas.add(new Mascota(R.drawable.mascota2, "Java",5 ,false));
            mascotas.add(new Mascota(R.drawable.mascota6, "Piolin", 5, false));
            mascotas.add(new Mascota(R.drawable.mascota8, "Nala", 5, false));
            mascotas.add(new Mascota(R.drawable.mascota3, "Pollo", 5, false));
            mascotas.add(new Mascota(R.drawable.mascota4, "Coco", 5, false));
        }

        public void inicializarAdaptador() {
            MascotaAdaptador adapter = new MascotaAdaptador(mascotas);
            listaMascotas.setAdapter(adapter);
        }}*/


