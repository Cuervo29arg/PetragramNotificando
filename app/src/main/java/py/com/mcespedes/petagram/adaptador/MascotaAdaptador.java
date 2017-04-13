package py.com.mcespedes.petagram.adaptador;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import py.com.mcespedes.petagram.RestApi.ConstantesRestApi;
import py.com.mcespedes.petagram.RestApi.EndpointsApi;
import py.com.mcespedes.petagram.RestApi.adapter.RestApiAdapter;
import py.com.mcespedes.petagram.RestApi.model.UsuarioResponse;
import py.com.mcespedes.petagram.db.ConstructorMascotas;
import py.com.mcespedes.petagram.pojo.Mascota;
import py.com.mcespedes.petagram.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 22/11/16.
 */

public class MascotaAdaptador extends  RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    //se define el array de mascotas
    ArrayList<Mascota> mascotas;
    //se define la actividad
    Activity activity;

    // Especifica un constructor
    //que recibe la lista de mascotas y
    //y la actividad que la instancia
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    //inflar la vista y retornar el viewholder de la vista inflada
    //para obtener cada view
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    //asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        //mascotaViewHolder.imgFoto.setImageResource(mascota.getImagen());
        mascotaViewHolder.tvNomnreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvPunto.setText(String.valueOf(mascota.getLikes()));

        //mascota.getUrlProfilePicture()

        Picasso.with(activity)
                .load(mascota.getImagen())
                .placeholder(R.drawable.clipartrootwerler)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hola!, mi nombre es "+mascota.getNombre(), Snackbar.LENGTH_LONG).show();
            }
        });

       mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLike(mascota);
                String punto = mascotaViewHolder.tvPunto.getText().toString();
                int punto2 = Integer.valueOf(punto) + 1;
                mascotaViewHolder.tvPunto.setText(String.valueOf(punto2));

                Log.d("ENVIANDO", "enviando");

                RestApiAdapter restApiAdapter = new RestApiAdapter();

                EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiToken();

                String token = FirebaseInstanceId.getInstance().getToken();

                Log.d("token",token);

                Call<String> usuarioResponseCall = endpoints.setLikePhoto(token,mascota.getImagen(), ConstantesRestApi.KEY_JAFERR_91_ID);

                usuarioResponseCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String usuarioResponse = response.body();
                        Log.d("RESPUESTA  " , usuarioResponse);
                        Toast.makeText(activity, "Has dado like a la foto !", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("ERROR", t.getMessage());
                    }
                });

                /*Snackbar.make(v, "Has dado like a la foto !", Snackbar.LENGTH_LONG)
                        .setAction(v.getResources().getString(R.string.texto_accion), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Log.d("ENVIANDO", "enviando");

                                RestApiAdapter restApiAdapter = new RestApiAdapter();

                                EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiToken();

                                String token = FirebaseInstanceId.getInstance().getToken();

                                Call<String> usuarioResponseCall = endpoints.setLikePhoto(token,mascota.getImagen(), ConstantesRestApi.KEY_JAFERR_91_ID);

                                usuarioResponseCall.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        String usuarioResponse = response.body();
                                        Log.d("RESPUESTA  " , usuarioResponse);
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.d("ERROR", t.getMessage());
                                    }
                                });

                            }
                        })
                        .setActionTextColor(v.getResources().getColor(R.color.colorPrimary))
                        .show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNomnreCV;
        private ImageButton btnLike;
        private TextView tvPunto;
        private ImageButton btLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto         = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNomnreCV      = (TextView) itemView.findViewById(R.id.tvNombreCV);
            btnLike         = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvPunto         = (TextView) itemView.findViewById(R.id.tvPunto);
            btLike          = (ImageButton)itemView.findViewById(R.id.btnLike);
        }

    }

}