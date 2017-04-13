package py.com.mcespedes.petagram.RestApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import py.com.mcespedes.petagram.RestApi.ConstantesRestApi;
import py.com.mcespedes.petagram.RestApi.EndpointsApi;
import py.com.mcespedes.petagram.RestApi.deserializador.ContactoDeserializador;
import py.com.mcespedes.petagram.RestApi.model.MascotaResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 19/12/16.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiToken(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_HEROKU_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);

    }

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);

    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new ContactoDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUserData(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new ContactoDeserializador());
        return gsonBuilder.create();
    }

}
