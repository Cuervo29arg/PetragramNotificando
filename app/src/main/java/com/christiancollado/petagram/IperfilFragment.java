package com.christiancollado.petagram;

import java.util.ArrayList;

/**
 * Created by colla on 29/11/2016.
 */

public interface IperfilFragment {

    public void generarLLV();

    public void generarGL();

    public UsuarioPerfil crearAdapter(ArrayList<User> users);

    public void inicializarAdaptador(UsuarioPerfil adaptador);

    public void completarPerfil(User user);
}
