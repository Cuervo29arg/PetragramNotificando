package py.com.mcespedes.petagram.RestApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import py.com.mcespedes.petagram.RestApi.model.JsonKeys;
import py.com.mcespedes.petagram.RestApi.model.UserDataResponse;
import py.com.mcespedes.petagram.pojo.Contacto;

/**
 * Created by root on 20/12/16.
 */

public class UserDataDeserializador implements JsonDeserializer<UserDataResponse> {
    @Override
    public UserDataResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        UserDataResponse contactoResponse = gson.fromJson(json, UserDataResponse.class);

        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setContactos(deserializarContactoJson(contactoResponseData));

        return contactoResponse;

    }

    private ArrayList<Contacto> deserializarContactoJson(JsonArray contactoResponseData ){

        ArrayList<Contacto> contactos = new ArrayList<Contacto>();

        System.out.println("en el deserializar los contacots response data es : " + contactoResponseData.size());

        for (int i = 0 ; i < contactoResponseData.size(); i++ ) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            Contacto contactoActual = new Contacto();

            contactos.add(contactoActual);

        }

        System.out.println("los contactos son " + contactos.size());

        return contactos;

    }
}
