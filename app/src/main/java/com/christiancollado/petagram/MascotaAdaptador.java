package com.christiancollado.petagram;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.christiancollado.petagram.db.ConstructorMascota;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by colla on 11/11/2016.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;

    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.logo)
                .into(holder.imgImagenMascota);
        holder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        holder.imgImagenMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FotoDetalle.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());
                v.getContext().startActivity(intent);

            }
        });


        /*holder.imgPuntuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.setPuntuacion(mascota.getPuntuacion()+1);
                holder.tvPuntuacion.setText(String.valueOf(mascota.getPuntuacion()));

                ConstructorMascota constructorMascotas = new ConstructorMascota(activity);
                constructorMascotas.favMascota(mascota);

                Toast.makeText(activity,"HAS DADO FAV A  "+ mascota.getNombre(), Toast.LENGTH_SHORT).show();

            }
                                             }
        );*/

                /*int contador = Integer.parseInt(holder.tvPuntuacion.getText().toString());
                if(contador<5) {
                    mascota.setPuntuacion(contador + 1);
                }else{
                    mascota.setPuntuacion(0);
                }
                holder.tvPuntuacion.setText(String.valueOf(mascota.getPuntuacion()));



            }
        }
);*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImagenMascota;
        private TextView tvLikes;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgImagenMascota = (ImageView) itemView.findViewById(R.id.imgImagenMascota);
            tvLikes = (TextView) itemView.findViewById(R.id.tvPuntuacion);

    /*public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        ImageView imgImagenMascota;
        ImageView imgPuntuacion;
        ImageView imgPuntuar;
        TextView tvNombreMascota;
        TextView tvPuntuacion;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgImagenMascota = (ImageView) itemView.findViewById(R.id.imgImagenMascota);
            tvNombreMascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvPuntuacion = (TextView) itemView.findViewById(R.id.tvPuntuacion);
            imgPuntuar = (ImageView) itemView.findViewById(R.id.imgPuntuar);
            imgPuntuacion = (ImageView) itemView.findViewById(R.id.imgPuntuacion);
        }*/
        }
    }}
