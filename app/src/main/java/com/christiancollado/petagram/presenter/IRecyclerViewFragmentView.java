package com.christiancollado.petagram.presenter;

import com.christiancollado.petagram.Mascota;
import com.christiancollado.petagram.MascotaAdaptador;

import java.util.ArrayList;

/**
 * Created by colla on 25/11/2016.
 */

public interface IRecyclerViewFragmentView {

    public void generarLLV();

    public void generarGL();

    public MascotaAdaptador crearAdapter(ArrayList<Mascota> mascotas);

    public void inicializarAdaptador(MascotaAdaptador adaptador);
}

