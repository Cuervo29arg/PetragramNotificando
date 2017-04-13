package com.christiancollado.petagram.resAPI;

import com.christiancollado.petagram.resAPI.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by colla on 09/12/2016.
 */

public interface IEndpoint {

    @FormUrlEncoded
    @POST(ConstantesRA.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarID(@Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_dispositivo") String id_dispositivo);
}

