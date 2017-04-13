package com.christiancollado.petagram.resAPI.model;

import java.util.ArrayList;
import com.christiancollado.petagram.Mascota;

/**
 * Created by colla on 29/11/2016.
 */

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
