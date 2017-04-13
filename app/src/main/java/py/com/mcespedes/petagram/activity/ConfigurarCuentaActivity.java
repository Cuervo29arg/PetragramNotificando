package py.com.mcespedes.petagram.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import py.com.mcespedes.petagram.R;
import py.com.mcespedes.petagram.RestApi.model.JsonKeys;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    EditText editText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
    }

    public void guardarCuenta(View view){

        editText = (EditText) findViewById(R.id.etNombreCuenta);

        Toast.makeText(getBaseContext(), "La cuenta "+editText.getText().toString()+" fue guardada corretamente", Toast.LENGTH_LONG).show();

    }

}
