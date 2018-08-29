package dam.isi.frsf.utn.edu.ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dam.isi.frsf.utn.edu.ar.modelo.Cliente;
import dam.isi.frsf.utn.edu.ar.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
