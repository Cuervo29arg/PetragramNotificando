package com.christiancollado.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.christiancollado.petagram.Mascota;
import com.christiancollado.petagram.User;
import com.christiancollado.petagram.db.ConstructorMascota;
import com.christiancollado.petagram.resAPI.ConstantesRA;
import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.IEndPointApi;
import com.christiancollado.petagram.resAPI.model.MascotaResponse;
import com.christiancollado.petagram.resAPI.adapter.RestAPIAdapter;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by colla on 25/11/2016.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> mascotas;
    private String cuenta = "ccr.10";
    private User user;
    RestAPIAdapter restApiAdapter;
    Gson gsonConsult;
    IEndPointApi endpointApi;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;

        if (cargarUsuario() != "")
        {
            cuenta = cargarUsuario();
        }

        obtenerMediosRecientes();
    }




    public void obtenerMascotasBD() {
        constructorMascota = new ConstructorMascota(context);
        mostrarMascotas();

        /*this.constructorMascota = new ConstructorMascota(this.context);
        this.mascotas = this.constructorMascota.obtenerMascotas();
        this.mostrarMascotas();*/
    }

    public String cargarUsuario() {
        String user = "";

        try {
            FileInputStream inputStream = context.openFileInput("user.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            char[] inputBuffer = new char[100];

            int charRead;
            while((charRead = streamReader.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0 ,charRead);
                user += readString;

                inputBuffer = new char[100];
            }

            streamReader.close();

        }catch (IOException except){
            user = "";
            except.printStackTrace();
        }
        return user;
    }

    public void obtenerMediosRecientes() {
        restApiAdapter = new RestAPIAdapter();
        gsonConsult = restApiAdapter.buildDesGsonFotoUser();
        endpointApi = restApiAdapter.connectRestApiInsta(gsonConsult);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUser(cuenta, ConstantesRA.ACCESS_TOKEN);

        fotoResponseCall.enqueue(new Callback<FotoResponse>() {

            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                    Toast.makeText(context, "... ERROR", Toast.LENGTH_SHORT).show();
                } else {
                    user = fotoResponse.getUser();
                    buscarMediosRecientes(user);
                }

            }

            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                Log.e("-- ERROR DE CONEX --", t.toString());
            }
        });

    }

    public void buscarMediosRecientes(User user){
        gsonConsult = restApiAdapter.buildDesGsonMediaRecent();
        endpointApi = restApiAdapter.connectRestApiInsta(gsonConsult);

        Call<MascotaResponse> mascotaResponseCall = endpointApi.getRecentMediaAmigos(user.getId());

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotas();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                Log.e("-- Error de conexión --", t.toString());
            }
        });
    }


    public void mostrarMascotas() {
        iRecyclerViewFragmentView.inicializarAdaptador(iRecyclerViewFragmentView.crearAdapter(mascotas));
        iRecyclerViewFragmentView.generarGL();
    }
}

