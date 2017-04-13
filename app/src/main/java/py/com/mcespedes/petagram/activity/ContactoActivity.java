package py.com.mcespedes.petagram.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import py.com.mcespedes.petagram.R;

public class ContactoActivity extends AppCompatActivity {

    private EditText mensaje;
    private EditText nombre;
    private EditText remitente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void enviarCorreo(View v){

        nombre = (EditText) findViewById(R.id.etNombreContacto);
        remitente = (EditText) findViewById(R.id.etCorreoContacto);
        mensaje = (EditText) findViewById(R.id.etMensaje);

        if (remitente.getText().toString().equals("") || remitente.getText().toString().equals(null) ){
            Toast.makeText(this, "Atención, Debe cargar el correo", Toast.LENGTH_LONG).show();
        }else if(nombre.getText().toString().equals("") || nombre.getText().toString().equals(null) ) {
            Toast.makeText(this, "Atención, Debe cargar el nombre", Toast.LENGTH_LONG).show();
        }else if (mensaje.getText().toString().equals("") || mensaje.getText().toString().equals(null)) {
            Toast.makeText(this, "Atención, Debe cargar el mensaje", Toast.LENGTH_LONG).show();
        }

        if(isEmailValid(remitente.getText().toString())){

            new Thread(new Runnable() {
                public void run(){
                    sendMail(nombre.getText().toString(), remitente.getText().toString(),mensaje.getText().toString());
                }
            }).start();

            Toast.makeText(this, "Su mensaje ha sido enviado correctamente !!!", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Error, Correo inválido !", Toast.LENGTH_LONG).show();
        }

        inicializar();

    }

    public void sendMail(String pnombre, String premitente, String pmensaje){

        try {


            Properties properties2 = new Properties();
            properties2.put("mail.smtps.host", "mail.fundacionparaguaya.org.py");
            properties2.put("mail.smtps.port", 465);
            properties2.put("mail.smtps.mail.sender", "appfupa@fundacionparaguaya.org.py");
            properties2.put("mail.smtps.user", "appfupa@fundacionparaguaya.org.py");
            properties2.put("mail.smtps.password", "appfupa123");
            Session session2 = Session.getDefaultInstance(properties2);
            session2.setDebug(true);

            String sendto2 = premitente;

            MimeMessage message2 = new MimeMessage(session2);
            message2.setFrom(new InternetAddress((String) properties2.get("mail.smtps.mail.sender")));
            message2.addRecipient(Message.RecipientType.TO, new InternetAddress(sendto2));
            message2.setSubject("Formulario de Contacto");
            message2.setText(pnombre + ", " + pmensaje);

            Transport t2 = session2.getTransport("smtps");
            t2.connect((String) properties2.get("mail.smtps.user"), (String) properties2.get("mail.smtps.password"));
            t2.sendMessage(message2, message2.getAllRecipients());
            t2.close();

        }catch (Exception e){
            Toast.makeText(this, "Error " + e.getClass().getCanonicalName(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {
            //nothing to do
        }

    }

    private void inicializar() {
        nombre.setText(null);
        remitente.setText(null);
        mensaje.setText(null);
        nombre.requestFocus();
    }

    public void irfavoritos(View v){

        Intent i = new Intent(this, FavoritosMascota.class);
        startActivity(i);

    }

}
