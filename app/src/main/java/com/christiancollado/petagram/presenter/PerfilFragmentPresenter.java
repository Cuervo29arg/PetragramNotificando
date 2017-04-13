package com.christiancollado.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.christiancollado.petagram.IperfilFragment;
import com.christiancollado.petagram.User;
import com.christiancollado.petagram.db.ConstructorMascota;
import com.christiancollado.petagram.resAPI.ConstantesRA;
import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.IEndPointApi;
import com.christiancollado.petagram.resAPI.adapter.RestAPIAdapter;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by colla on 29/11/2016.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {
    private IperfilFragment iPerfilFragment;
    private Context context;
    private User user;
    private ConstructorMascota constructor;
    private String cuenta = "ccr.10";
    private ArrayList<User> misUsuarios = new ArrayList<>();

    public PerfilFragmentPresenter(IperfilFragment view, Context context) {
        this.iPerfilFragment = view;
        this.context = context;
        if (cargarUser() != "")
        {
            cuenta = cargarUser();
        }
        obtenerFotoUser();
    }

    @Override
    public void obtenerMascotasBD() {

    }

    @Override
    public void obtenerFotoUser() {
        RestAPIAdapter restApiAdapter = new RestAPIAdapter();
        Gson gsonFotoUser  = restApiAdapter.buildDesGsonFotoUser();
        IEndPointApi endpointApi = restApiAdapter.connectRestApiInsta(gsonFotoUser);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUser(cuenta, ConstantesRA.ACCESS_TOKEN);


        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                    Toast.makeText(context, "..ERROR", Toast.LENGTH_SHORT).show();
                }else {
                    user = fotoResponse.getUser();
                    mostrarFotos();
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                Log.e("-- Error de conexión --", t.toString());
            }
        });
    }

    @Override
    public void mostrarFotos() {
        for(int i=0 ; i < 12; i++){
            misUsuarios.add(user);
        }
        iPerfilFragment.inicializarAdaptador(iPerfilFragment.crearAdapter(misUsuarios));
        iPerfilFragment.generarGL();
        iPerfilFragment.completarPerfil(user);
    }

    @Override
    public String cargarUser() {

        String usuario = "";

        try{


            FileInputStream fis = context.openFileInput("usuario.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            char[] inputBuffer = new char[100];

            int charRead;
            while((charRead = isr.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                usuario += readString;

                inputBuffer = new char[100];
            }


            isr.close();
        }catch (IOException ex){
            usuario= "";
            ex.printStackTrace();
        }

        return usuario;
    }
}
