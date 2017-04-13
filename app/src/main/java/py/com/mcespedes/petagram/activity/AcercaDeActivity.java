package py.com.mcespedes.petagram.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import py.com.mcespedes.petagram.R;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void irfavoritos(View v){

        Intent i = new Intent(this, FavoritosMascota.class);
        startActivity(i);

    }

}