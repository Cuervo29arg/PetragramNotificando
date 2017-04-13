package com.christiancollado.petagram;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.christiancollado.petagram.PgAdapter;
import com.christiancollado.petagram.HomeFragment;
import com.christiancollado.petagram.PerfilFragment;
import com.christiancollado.petagram.Mascota;
import com.christiancollado.petagram.MascotaAdaptador;
import com.christiancollado.petagram.R;
import com.christiancollado.petagram.presenter.IRecyclerViewFragmentPresenter;
import com.christiancollado.petagram.presenter.IRecyclerViewFragmentView;
import com.christiancollado.petagram.presenter.RecyclerViewFragmentPresenter;


import java.util.ArrayList;

/**
 * Created by christian on 19/11/16.
 */


public class HomeFragment extends Fragment implements IRecyclerViewFragmentView {


   // public static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;

   public HomeFragment() {

   }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listaMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);
        //listaMascotas.setHasFixedSize(true);

       /* LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);*/

        presenter = new RecyclerViewFragmentPresenter(this,getContext());


        /*inicializarListaMascotas();
        inicializarAdaptadorMascotas();*/

        return view;
    }


    @Override
    public void generarLLV() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public void generarGL() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        listaMascotas.setLayoutManager(glm);
    }


    @Override
    public MascotaAdaptador crearAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adapter = new MascotaAdaptador(mascotas,getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}




    /*public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 0, false));
        mascotas.add(new Mascota(R.drawable.mascota2, "Java", 0, false));
        mascotas.add(new Mascota(R.drawable.mascota3, "Pollo", 0, false));
        mascotas.add(new Mascota(R.drawable.mascota4, "Coco", 0, false));
        mascotas.add(new Mascota(R.drawable.mascota5, "Bunny",0, false));
        mascotas.add(new Mascota(R.drawable.mascota6, "Piolin", 0, false));
        mascotas.add(new Mascota(R.drawable.mascota8, "Nala", 0, false));

    }*/


