package dam.isi.frsf.utn.edu.ar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import dam.isi.frsf.utn.edu.ar.modelo.Cliente;
import dam.isi.frsf.utn.edu.ar.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;

    //Probando git


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
    private TextView tvDiasSeleccionados;
    private TextView tvMensaje;
    private TextView tvMensaje_2;

    private int cantDiasPF = 10;
    private double monto = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = new PlazoFijo(getResources().getStringArray(R.array.tasas));                       //TODO: no sabemos si esta bien
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
        tvDiasSeleccionados = (TextView) findViewById(R.id.tvDiasSeleccionados);
        tvMensaje = (TextView) findViewById(R.id.tvMensajes);
        tvMensaje_2 = (TextView) findViewById(R.id.tvMensaje_2);

        btnHacerPlazoFijo.setEnabled(false);
        swAvisarVencimeinto.setEnabled(false);

        setSeekBar();

        chkTerminos();
    }

    public void onClick(View v){
        if(verificarDatosPlazoFijo()){
            tvMensaje.setTextColor(Color.GREEN);
            tvMensaje.setText("El plazo fijo se realizó con éxito");
            tvMensaje_2.setTextColor(Color.BLUE);
            tvMensaje_2.setText("Dias: " + cantDiasPF +
                    "\nMonto: " + edtMonto.getText().toString());
        }
        else{
            tvMensaje.setTextColor(Color.RED);
            tvMensaje.setText("Los datos ingresados son incorrectos");

            tvMensaje_2.setText("");

            Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarDatosPlazoFijo(){
        boolean verificacionExitosa = true;
        if((edtMail.getText().toString().length() == 0) || edtMail == null){
            verificacionExitosa = false;
        }

        if(edtCuit.getText().toString().length() == 0 || edtCuit == null){
            verificacionExitosa = false;
        }

        monto = Double.parseDouble(edtMonto.getText().toString());
        if(monto == 0.0){
            verificacionExitosa = false;
        }

        if((cantDiasPF < 10)){
            verificacionExitosa = false;
        }

        return verificacionExitosa;
    }

    public void chkTerminos(){
        chkAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!chkAceptoTerminos.isChecked()){
                    btnHacerPlazoFijo.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Es obligatorio aceptar las condiciones", Toast.LENGTH_LONG).show();
                }
                else{
                    btnHacerPlazoFijo.setEnabled(true);
                }
            }
        });
    }

    public void setSeekBar(){

        tvDiasSeleccionados.setText(" " + seekDias.getProgress() );

        //dar valor maximo y minimo a seekbar
        seekDias.setMin(10);
        seekDias.setMax(180);

        seekDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,int i,boolean b)
            {
                cantDiasPF = i;
                tvDiasSeleccionados.setText("" + i + " Días");

                //Setea los dias en el plazo fijo
                pf.setDias(i);

                // actualiza el calculo de los intereses pagados
                pf.intereses();                                                                 //TODO:los intereses donde los guardamos?
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //No hacer nada
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                //No hacer nada
            }
        });
    }
}
