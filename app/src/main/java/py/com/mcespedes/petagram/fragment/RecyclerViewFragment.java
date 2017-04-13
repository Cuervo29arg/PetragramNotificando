package py.com.mcespedes.petagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.adaptador.MascotaAdaptador;
import py.com.mcespedes.petagram.pojo.Mascota;
import py.com.mcespedes.petagram.presentador.IRecyclerViewFragmentPresenter;
import py.com.mcespedes.petagram.presentador.RecyclerViewFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements  IReciclerViewfragmentView {

    private RecyclerView listaMascotas;

    private IRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        //creando reciclerview
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generarLineaLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adapatador = new MascotaAdaptador(mascotas, getActivity());
        return adapatador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adapatador) {
        listaMascotas.setAdapter(adapatador);
    }

}