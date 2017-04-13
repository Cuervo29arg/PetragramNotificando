package com.christiancollado.petagram.resAPI.desserializador;

import com.christiancollado.petagram.Mascota;
import com.christiancollado.petagram.resAPI.JsonKEY;
import com.christiancollado.petagram.resAPI.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by colla on 29/11/2016.
 */

public class MascotaDes implements JsonDeserializer<MascotaResponse> {



    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKEY.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(desMascJson(mascotaResponseData));
        return mascotaResponse;

    }
    private ArrayList<Mascota> desMascJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++){
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKEY.USER);
            String id = userJson.get(JsonKEY.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKEY.USER_FULLNAME).getAsString();

            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKEY.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKEY.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKEY.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKEY.MEDIA_LIKES);
            int likes = likesJson.get(JsonKEY.MEDIA_LIKES_COUNT).getAsInt();

            Mascota actualMascota = new Mascota();
            actualMascota.setId(id);
            actualMascota.setNombre(nombreCompleto);
            actualMascota.setUrlFoto(urlFoto);
            actualMascota.setLikes(likes);

            mascotas.add(actualMascota);


        }
        return mascotas;

    }
}
