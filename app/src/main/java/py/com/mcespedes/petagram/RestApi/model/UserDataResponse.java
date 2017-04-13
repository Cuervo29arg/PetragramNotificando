package py.com.mcespedes.petagram.RestApi.model;

import java.util.ArrayList;

import py.com.mcespedes.petagram.pojo.Contacto;

/**
 * Created by root on 20/12/16.
 */

public class UserDataResponse {

    ArrayList<Contacto> contactos;

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

}
