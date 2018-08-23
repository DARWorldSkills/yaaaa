package com.aprendiz.ragp.proyectopsp.Controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aprendiz.ragp.proyectopsp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Defect_Log extends AppCompatActivity implements View.OnClickListener {

    EditText txtFechaDefect, txtComentarioDefect, txtFix;
    Button btnDate, btnInicio, btnFin, btnReiniciar;
    Spinner SpinnerType, SpinnerPhaseInjected, SpinnerPhaseRemovede;

    Date date;

    Thread thread;
    int [] tiempo = {0,0};


    boolean bandera = true;
    boolean bandera1 = false;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                   validar();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect__log);

        inicializar();
        ListarSpinner();
        llamar();
        //validar();

        cronometro();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void cronometro() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (bandera){
                    try {
                        {
                            Thread.sleep(1000);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (bandera1){
                                    tiempo[0]++;
                                    if (tiempo [0] == 60){
                                        tiempo[1]++;
                                        tiempo[0] =0;
                                    }

                                    if (tiempo[0] >= 0 && tiempo[0] <10){
                                        if (tiempo[0] >= 0 && tiempo[0] < 10){
                                            txtFix.setText("0" + tiempo[1] + ":" + "0" + tiempo[0]);
                                        }else {

                                            txtFix.setText(tiempo[1] + ":" + "0" + tiempo[0]);
                                        }
                                    }


                                    if (tiempo[0] >= 10 && tiempo[0] <60){
                                        if (tiempo[0] >= 0 && tiempo[0] <10){
                                            txtFix.setText("0" + tiempo[1] + ":" + tiempo[0]);

                                        }else{

                                            txtFix.setText(tiempo[1] + ":" + tiempo[0]);
                                        }
                                    }
                                }

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void validar() {

        int validar = 0;

        if (txtFechaDefect.getText().toString().length()> 0){
            validar++;
        }else {
            txtFechaDefect.setError("Es necesario iniciar el cronometro");

        }if (txtFix.getText().toString().length()> 0){
            validar++;

        }else {
            txtFix.setError("Es necesario iniciar el cronometro");
        }



    }

    private void llamar() {

        btnDate.setOnClickListener(this);
        btnInicio.setOnClickListener(this);
        btnFin.setOnClickListener(this);
        btnReiniciar.setOnClickListener(this);


    }

    private void ListarSpinner() {

        List<String>Type = new ArrayList();
        Type.add("Documentation");
        Type.add("Syntax");
        Type.add("Build");
        Type.add("Packege");
        Type.add("Assigment");
        Type.add("Interface");
        Type.add("Checking");
        Type.add("Date");
        Type.add("Function");
        Type.add("System");
        Type.add("Environment");

        List<String>Phases = new ArrayList<>();
        Phases.add("PLAN");
        Phases.add("DLD");
        Phases.add("CODE");
        Phases.add("COMPILE");
        Phases.add("UT");
        Phases.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,Type);
        SpinnerType.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Phases);
        SpinnerPhaseInjected.setAdapter(adapter1);
        SpinnerPhaseRemovede.setAdapter(adapter1);

    }

    private void inicializar() {

        txtFechaDefect = findViewById(R.id.txtFechaDefect);
        txtComentarioDefect = findViewById(R.id.txtComentarioDefect);
        txtFix = findViewById(R.id.txtCronometro);

        SpinnerType = findViewById(R.id.SpinnerType);
        SpinnerPhaseInjected = findViewById(R.id.SpinnerPhaseInjected);
        SpinnerPhaseRemovede = findViewById(R.id.SpinnerPhaseRemovede);

        btnDate = findViewById(R.id.btnDate);
        btnInicio = findViewById(R.id.btnInicio);
        btnFin = findViewById(R.id.btnStop);
        btnReiniciar = findViewById(R.id.btnReinicar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnDate:

                date = new Date();
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fecha1 = fecha.format(date);
                txtFechaDefect.setText(fecha1);

            break;


            case R.id.btnInicio:
                iniciar();

                break;

            case R.id.btnStop:
            parar();

                break;



            case R.id.btnReinicar:
                reiniciar();

                break;

        }
    }

    private void reiniciar() {
        bandera1 = false;
        tiempo[0]=0;
        tiempo[1]=0;
        txtFix.setText("0" + tiempo[1] + ":" + "0" + tiempo[0]);
    }

    private void parar() {

        bandera1 = false;
    }

    private void iniciar() {

        bandera1 = true;

    }
}
