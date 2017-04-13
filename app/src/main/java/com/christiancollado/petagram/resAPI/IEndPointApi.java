package com.christiancollado.petagram.resAPI;

import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by colla on 29/11/2016.
 */

public interface IEndPointApi {


    @GET(ConstantesRA.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRA.URL_GET_RECENT_MEDIA_AMIGOS)
    Call<MascotaResponse> getRecentMediaAmigos(@Path("usuario") String user);


    @GET(ConstantesRA.KEY_SEARCH_USER)
    Call<FotoResponse> getFotoUser(@Query("q") String cuenta, @Query("access_token") String access_token);

}
