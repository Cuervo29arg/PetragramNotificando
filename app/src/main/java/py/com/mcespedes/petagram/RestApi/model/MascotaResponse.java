package py.com.mcespedes.petagram.RestApi.model;

import java.util.ArrayList;

import py.com.mcespedes.petagram.pojo.Mascota;


/**
 * Created by root on 19/12/16.
 */

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getContactos() {
        return mascotas;
    }

    public void setContactos(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}
