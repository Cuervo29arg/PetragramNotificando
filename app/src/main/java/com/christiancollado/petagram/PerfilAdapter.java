package com.christiancollado.petagram;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by christian on 19/11/16.
 */

public class PerfilAdapter /*extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> */ {

   /* private ArrayList<Mascota> mascotas;

    public PerfilAdapter(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @Override
    public PerfilAdapter.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new PerfilAdapter.PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PerfilAdapter.PerfilViewHolder perfilViewHolder, int position) {
        final Mascota mascota=mascotas.get(position);
        perfilViewHolder.img.setImageResource(mascota.getImg());
        perfilViewHolder.puntuacion.setText(String.valueOf(mascota.getPuntuacion()));
    }

    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView puntuacion;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            img    = (ImageView)   itemView.findViewById(R.id.imgImagenMascota);
            puntuacion = (TextView)    itemView.findViewById(R.id.tvPuntuacion);
        }
    }



*/
}