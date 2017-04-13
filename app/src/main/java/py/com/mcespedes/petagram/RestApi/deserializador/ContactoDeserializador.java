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

import py.com.mcespedes.petagram.RestApi.model.MascotaResponse;
import py.com.mcespedes.petagram.RestApi.model.JsonKeys;
import py.com.mcespedes.petagram.pojo.Mascota;

/**
 * Created by root on 19/12/16.
 */

public class ContactoDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        MascotaResponse contactoResponse = gson.fromJson(json, MascotaResponse.class);

        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setContactos(deserializarContactoJson(contactoResponseData));

        return contactoResponse;

    }

    private ArrayList<Mascota> deserializarContactoJson(JsonArray contactoResponseData ){

        ArrayList<Mascota> contactos = new ArrayList<Mascota>();

        for (int i = 0 ; i < contactoResponseData.size(); i++ ) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            //datos del usuario
            JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String urlProfilePicture   = userJson.get(JsonKeys.USER_URL_PROFILE_PICTURE).getAsString();

            //datos de la imagen
            JsonObject imageJson    = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            //datos de los likes
            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota contactoActual = new Mascota();
            contactoActual.setId(id);
            contactoActual.setNombre(nombreCompleto);
            contactoActual.setImagen(urlFoto);
            contactoActual.setLikes(likes);
            contactoActual.setUrlProfilePicture(urlProfilePicture);

            contactos.add(contactoActual);

        }

        return contactos;

    }
}
