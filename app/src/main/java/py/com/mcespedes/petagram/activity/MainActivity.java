package py.com.mcespedes.petagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.adaptador.PageAdapter;
import py.com.mcespedes.petagram.fragment.PerfilFragment;
import py.com.mcespedes.petagram.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        toolbar = (Toolbar) findViewById(R.id.miActionBar);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setTitle(null);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        /*String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FIREBASE_TOKEN", token);*/

    }

   private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;

    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_dog);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_face_dog);
    }

    //se infla el menu en el mainactivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //se controla los items del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                //Toast.makeText(this, "activity_acerca_de", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ContactoActivity.class);
                startActivity(intent);
                break;
            case R.id.mAcercade:
                //Toast.makeText(this, "activity_contacto", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, AcercaDeActivity.class);
                startActivity(i);
                break;
            case R.id.mConfCuenta:
                Intent iCuenta = new Intent(this, ConfigurarCuentaActivity.class);
                startActivity(iCuenta);
                break;
            case R.id.mRecivNotif:
                Intent notif = new Intent(this, GuardarUsuarioInstagramActivity.class);
                startActivity(notif);
                //Toast.makeText(this, "Ya puedes recibir notificaciones !!!", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void irfavoritos(View v){

        Intent i = new Intent(this, FavoritosMascota.class);
        startActivity(i);

    }

}