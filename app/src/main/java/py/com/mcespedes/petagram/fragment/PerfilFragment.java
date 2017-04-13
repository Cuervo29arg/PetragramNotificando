package py.com.mcespedes.petagram.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import py.com.mcespedes.petagram.adaptador.PerfilMascotaAdapter;
import py.com.mcespedes.petagram.pojo.Mascota;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private RecyclerView listaMascotas;

    ArrayList<Mascota> mascotas= new ArrayList<Mascota>();

    CircularImageView imagCircular;
    TextView tvNombrePerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Imagen circukar
        CircularImageView circularImageView = (CircularImageView) v.findViewById(R.id.imagCircular);
        // Set Border
        circularImageView.setBorderColor(getResources().getColor(R.color.colorIcons));
        circularImageView.setBorderWidth(1);
        // Add Shadow with default param
        circularImageView.addShadow();
        // or with custom param
        circularImageView.setShadowRadius(3);

        //creando reciclerview
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvPerfilMascota);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        listaMascotas.setLayoutManager(glm);

        imagCircular = (CircularImageView) v.findViewById(R.id.imagCircular);

        tvNombrePerfil = (TextView) v.findViewById(R.id.tvNombrePerfil);

        obtenerMediosRecientesByID();

        return v;

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
                inicializarAdaptador();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Fallo la conexion, intente de nuevo", Toast.LENGTH_LONG).show();
                //Log.i("Fallo la conexion",t.toString());
                t.printStackTrace();
            }

        });

    }

    public void inicializarAdaptador(){
        setProfilePicture();
        PerfilMascotaAdapter adaptador = new PerfilMascotaAdapter(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);

    }

    private void setProfilePicture() {

        String img="";
        String nombrePerfil="";

        for (int i = 0 ; i < mascotas.size(); i++ ) {

            Mascota mascota = mascotas.get(i);

            img = mascota.getUrlProfilePicture();
            System.out.println("el url es: " + img);
            nombrePerfil = mascota.getNombre();

            break;

        }

        Picasso.with(getContext())
                .load(img)
                .placeholder(R.drawable.clipartrootwerler)
                .into(imagCircular);

        tvNombrePerfil.setText(nombrePerfil);

    }

}
