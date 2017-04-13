package com.christiancollado.petagram.resAPI.desserializador;

import com.christiancollado.petagram.User;
import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.JsonKEY;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by colla on 29/11/2016.
 */

public class FotoDes implements JsonDeserializer<FotoResponse> {

    @Override
    public FotoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FotoResponse fotoResponse = gson.fromJson(json, FotoResponse.class);
        JsonArray fotoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKEY.MEDIA_RESPONSE_ARRAY);

        fotoResponse.setUser(desFotoJson(fotoResponseData));
        return fotoResponse;
    }

    private User desFotoJson(JsonArray fotoResponseData){
        JsonObject fotoResponseDataObject = fotoResponseData.get(0).getAsJsonObject();

        String nombre = fotoResponseDataObject.get(JsonKEY.USER_NAME).getAsString();
        String nombreCompleto = fotoResponseDataObject.get(JsonKEY.USER_FULLNAME).getAsString();

        String urlFotoPerfil = fotoResponseDataObject.get(JsonKEY.USER_FOTO_PERFIL).getAsString();

        String id = fotoResponseDataObject.get(JsonKEY.USER_ID).getAsString();

        User actualUser = new User(nombre, nombreCompleto, urlFotoPerfil, id);

        return actualUser;
    }

    }
