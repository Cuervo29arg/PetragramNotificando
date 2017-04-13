package com.christiancollado.petagram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by colla on 29/11/2016.
 */

public class FotoDetalle extends AppCompatActivity{
    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKES = "like";

    private ImageView imgFoto;
    private TextView tvLikesDetalleFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_detalle);

        Bundle extras = getIntent().getExtras();
        String url = extras.getString(KEY_EXTRA_URL);
        int likes = extras.getInt(KEY_EXTRA_LIKES);

        tvLikesDetalleFoto = (TextView) findViewById(R.id.tvLikesDetalleFoto);
        tvLikesDetalleFoto.setText(String.valueOf(likes));

        imgFoto = (ImageView) findViewById(R.id.imgDetalleMascotaFoto);

        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.logo)
                .into(imgFoto);

    }
}
