package com.christiancollado.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactoActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText email;
    private EditText mensaje;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        enviar = (Button) findViewById(R.id.btnEnviar);
        nombre = (EditText) findViewById(R.id.etNombre);
        email = (EditText) findViewById(R.id.etEmail);
        mensaje = (EditText) findViewById(R.id.etMensaje);

        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boton();
            }
        });}

        public void boton(){

            final String nombreuser = nombre.getText().toString().trim();
            final String emailuser = email.getText().toString().trim();
            final String mensajeuser = mensaje.getText().toString().trim();

        SendMail sm = new SendMail(this, nombreuser, emailuser, mensajeuser);

        sm.execute();
    }


    }

