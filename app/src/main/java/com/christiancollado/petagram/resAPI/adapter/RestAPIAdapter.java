package com.christiancollado.petagram.resAPI.adapter;

import com.christiancollado.petagram.resAPI.ConstantesRA;
import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.IEndPointApi;
import com.christiancollado.petagram.resAPI.IEndpoint;
import com.christiancollado.petagram.resAPI.model.MascotaResponse;
import com.christiancollado.petagram.resAPI.desserializador.FotoDes;
import com.christiancollado.petagram.resAPI.desserializador.MascotaDes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by colla on 29/11/2016.
 */

public class RestAPIAdapter {
    public IEndPointApi connectRestApiInsta(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRA.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndPointApi.class);
    }

    public Gson buildDesGsonMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDes());
        return gsonBuilder.create();
    }

    public Gson buildDesGsonFotoUser(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FotoResponse.class, new FotoDes());
        return gsonBuilder.create();
    }

    public IEndpoint connectRestAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRA.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(IEndpoint.class);
    }
}

