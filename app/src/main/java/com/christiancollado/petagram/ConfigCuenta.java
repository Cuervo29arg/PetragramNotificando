package com.christiancollado.petagram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by colla on 29/11/2016.
 */

public class ConfigCuenta  extends AppCompatActivity{
    private EditText tvCuenta;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_cuenta);

        tvCuenta = (EditText) findViewById(R.id.tvCuenta);

        toolbar = (Toolbar) findViewById(R.id.toolbarConfig);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.nombre_cuenta));
        }

        final Button guardar = (Button) findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                guardar();

            }
        });
    }
    public void guardar(){
        String usuario = tvCuenta.getText().toString();

        try{

            FileOutputStream fileOutput = openFileOutput("user.txt", MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(fileOutput);

            Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();

            tvCuenta.setText("");
        }

        catch (IOException except) {
            except.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
