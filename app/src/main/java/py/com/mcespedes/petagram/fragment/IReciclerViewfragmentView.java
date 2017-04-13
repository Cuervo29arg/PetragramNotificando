package py.com.mcespedes.petagram.fragment;

import java.util.ArrayList;

import py.com.mcespedes.petagram.adaptador.MascotaAdaptador;
import py.com.mcespedes.petagram.pojo.Mascota;

/**
 * Created by root on 11/12/16.
 */

public interface IReciclerViewfragmentView {

    public void generarLineaLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adapatador);


}
