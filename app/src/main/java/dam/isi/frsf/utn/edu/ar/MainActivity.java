package dam.isi.frsf.utn.edu.ar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import dam.isi.frsf.utn.edu.ar.modelo.Cliente;
import dam.isi.frsf.utn.edu.ar.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    // widgets de la vista
    private Button btnHacerPlazoFijo;
    private EditText edtMonto;
    private EditText edtMail;
    private EditText edtCuit;
    private RadioButton optDolar;
    private RadioButton optPesos;
    private SeekBar seekDias;
    private Switch swAvisarVencimeinto;
    private ToggleButton togAccion;
    private CheckBox chkAceptoTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));
        cliente = new Cliente();

        // widgets de la vista
        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPF);
        edtMonto = (EditText) findViewById(R.id.edtMonto);
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtCuit = (EditText) findViewById(R.id.edtCuit);
        optDolar = (RadioButton) findViewById(R.id.optDolar);
        optPesos = (RadioButton) findViewById(R.id.optPesos);
        seekDias = (SeekBar) findViewById(R.id.seekDias);
        swAvisarVencimeinto = (Switch) findViewById(R.id.swAvisarVencimiento);
        togAccion = (ToggleButton) findViewById(R.id.togAccion);
        chkAceptoTerminos = (CheckBox) findViewById(R.id.chkAceptoTerminos);

        btnHacerPlazoFijo.setEnabled(false);
        swAvisarVencimeinto.setEnabled(false);
    }
}
