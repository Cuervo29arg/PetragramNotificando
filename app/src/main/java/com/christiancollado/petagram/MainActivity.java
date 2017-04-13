package com.christiancollado.petagram;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.christiancollado.petagram.PgAdapter;
import com.christiancollado.petagram.HomeFragment;
import com.christiancollado.petagram.PerfilFragment;
import com.christiancollado.petagram.Mascota;
import com.christiancollado.petagram.resAPI.ConstantesRA;
import com.christiancollado.petagram.resAPI.IEndPointApi;
import com.christiancollado.petagram.resAPI.IEndpoint;
import com.christiancollado.petagram.resAPI.adapter.RestAPIAdapter;
import com.christiancollado.petagram.resAPI.model.FotoResponse;
import com.christiancollado.petagram.resAPI.model.UsuarioResponse;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar  toolbar;
    private TabLayout tab;
    private ViewPager view;
    private ArrayList<Fragment> fragments;
    private String cuenta = "ccr.10";
    String [] nombreMascota;
    int    [] imgMascota;
    int    [] pntMascota;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.aabToolbar);
        setSupportActionBar(toolbar);


        tab  = (TabLayout) findViewById(R.id.tab);
        view = (ViewPager) findViewById(R.id.view);
        setUpViewPager();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private ArrayList<Fragment> addFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        view.setAdapter(new PgAdapter(getSupportFragmentManager(),addFragment()));
        tab.setupWithViewPager(view);
        tab.getTabAt(0).setIcon(R.drawable.whitebone);
        tab.getTabAt(1).setIcon(R.drawable.yellowbone);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favoritos:
                Intent intent = new Intent(this,FavsActivity.class);
                //mascotasFavs(HomeFragment.mascotas);
                intent.putExtra("Nombre",nombreMascota);
                intent.putExtra("Imagen",imgMascota);
                intent.putExtra("Puntuación",pntMascota);
                startActivity(intent);
                break;
            case R.id.contacto:
                Intent intent1 = new Intent(this, ContactoActivity.class);
                startActivity(intent1);
                break;
            case R.id.about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.configurar:
                intent = new Intent(this, ConfigCuenta.class);
                startActivity(intent);
                break;
            case R.id.notif:
                enviarToken();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enviarToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token) {
        Log.d("TOKEN", token);
        if (cargarUsuario() != "") {
            cuenta = cargarUsuario();
        }
        obtenerIdInstagram(cuenta, token);
    }
    public String cargarUsuario() {

        String usuario = "";
        try {
            FileInputStream fis = getBaseContext().openFileInput("user.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[100];
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                usuario += readString;
                inputBuffer = new char[100];
            }
            isr.close();
        } catch (IOException excep) {
            usuario = "";
            excep.printStackTrace();
        }

        return usuario;
    }

    public void obtenerIdInstagram(String cuenta, final String token) {
        RestAPIAdapter restApiAdapter = new RestAPIAdapter();
        Gson gsonFotoUsuario = restApiAdapter.buildDesGsonFotoUser();
        IEndPointApi endpointApi = restApiAdapter.connectRestApiInsta(gsonFotoUsuario);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUser(cuenta, ConstantesRA.ACCESS_TOKEN);

        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                    Toast.makeText(getBaseContext(), "... ERROR", Toast.LENGTH_SHORT).show();
                } else {
                    registrarDatos(fotoResponse.getUser().getId(), token);
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                Log.e("--- ERROR DE CONEX ---", t.toString());
            }
        });
    }

    public void registrarDatos(final String idInstagram, final String token){
        RestAPIAdapter restApiAdapter = new RestAPIAdapter();
        IEndpoint endpoint = restApiAdapter.connectRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpoint.registrarID(idInstagram, token);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>(){


            @Override
            public void onResponse
                    (Call<UsuarioResponse> call, Response <UsuarioResponse> response){
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_USUARIO_INSTAGRAM: ", usuarioResponse.getId_dispositivo());
                Log.d("ID_DISPOSITIVO: ", usuarioResponse.getId_usuario_instagram());
            }

            @Override
            public void onFailure (Call<UsuarioResponse> call, Throwable t){
                Toast.makeText(getBaseContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}



    /*public void mascotasFavs(ArrayList<Mascota> mascotas){
        nombreMascota = new String[5];
        imgMascota = new int[5];
        pntMascota = new int[5];
        int i=0;
        for (Mascota masc:mascotas) {
            if (masc.isFav()) {
                nombreMascota[i] = masc.getNombre();
                imgMascota[i] = masc.getImg();
                pntMascota[i] = masc.getPuntuacion();
                i++;
                if (i == 5) {
                    i = 0;
                }
            }*/







