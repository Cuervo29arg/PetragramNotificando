package py.com.mcespedes.petagram.presentador;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.RestApi.EndpointsApi;
import py.com.mcespedes.petagram.RestApi.adapter.RestApiAdapter;
import py.com.mcespedes.petagram.RestApi.model.MascotaResponse;
import py.com.mcespedes.petagram.db.ConstructorMascotas;
import py.com.mcespedes.petagram.fragment.IReciclerViewfragmentView;
import py.com.mcespedes.petagram.pojo.Mascota;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 11/12/16.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IReciclerViewfragmentView iReciclerViewfragmentView;
    private Context context;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IReciclerViewfragmentView iReciclerViewfragmentView, Context context) {
        this.iReciclerViewfragmentView = iReciclerViewfragmentView;
        this.context = context;
        obtenerMediosRecientesByID();
    }

    public void obtenerMediosRecientesByID() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentMediaUserByID();
        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getContactos();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context,"Fallo la conexion, intente de nuevo", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iReciclerViewfragmentView.inicializarAdaptadorRV(iReciclerViewfragmentView.crearAdaptador(mascotas));
        iReciclerViewfragmentView.generarLineaLayoutVertical();

    }


}
