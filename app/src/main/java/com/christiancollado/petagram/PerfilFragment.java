package com.christiancollado.petagram;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christiancollado.petagram.presenter.PerfilFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by christian on 19/11/16.
 */

public class PerfilFragment extends Fragment implements IperfilFragment {
    ImageView foto;
    TextView nombre;
    PerfilFragmentPresenter perfilFragmentPresenter;
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    Bundle bundle;
    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
       bundle = this.getArguments();

        // View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        /*listaMascotas = (RecyclerView) view.findViewById(R.id.rvPerfil);
        listaMascotas.setHasFixedSize(true);

        */

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        foto = (ImageView) v.findViewById(R.id.imgDetalleMascotaFoto);
        nombre = (TextView) v.findViewById(R.id.fgNombreMascota);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvPerfil);

       /* GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(glm);

        inicializarListaMascotas();
        inicializarAdaptador();*/

        return v;
    }

    public void inicializarAdaptador() {
       /* PerfilAdapter adapter = new PerfilAdapter(mascotas);
        listaMascotas.setAdapter(adapter);*/
    }

    @Override
    public void generarLLV() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public void generarGL() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);

        listaMascotas.setLayoutManager(glm);

    }

    @Override
    public UsuarioPerfil crearAdapter(ArrayList<User> usuarios) {
        UsuarioPerfil adaptador = new UsuarioPerfil(usuarios, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(UsuarioPerfil adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public void completarPerfil(User usuario) {
        nombre.setText(usuario.getNombre());
        Picasso.with(getContext())
                .load(usuario.getUrlFotoPerfil())
                .placeholder(R.drawable.logo)
                .into(foto);
    }

   /* public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 3));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 1));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 5));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 3));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 1));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 1));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 2));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 1));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 3));
        mascotas.add(new Mascota(R.drawable.mascota, "Gato", 5));


    }*/

}


