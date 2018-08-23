package com.aprendiz.ragp.proyectopsp.Controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
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

public class Timer_Log extends AppCompatActivity {

    Spinner SpinnerPhase;
    EditText txtHoraInicio, txtInterrupcion, txtHorafin, txtHoraDelta, txtComentario;
    Button btnHoraStart, btnHoraStop;

    Date date;



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
        setContentView(R.layout.activity_timer__log);

        inicializar();
        ListaPhase();
       // validar();



        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        btnHoraStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularhora();
            }
        });
        btnHoraStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularhoraFin();
            }
        });
    }

    @Override
    public MenuInflater getMenuInflater() {


        return super.getMenuInflater();
    }

    private void validar() {
        int validar = 0;
        if (txtHoraInicio.getText().toString().length()>0){
            validar++;

        }else{
            txtHoraInicio.setError("Es necesario obtener la hora de inicio");
        }
        if (txtHorafin.getText().toString().length()>0){
            validar++;

        }else {
            txtHorafin.setError("Es necesario este campo");
        }if (txtHoraDelta.getText().toString().length()> 0){
            validar++;



        }else txtHoraDelta.setError("Es necesario este campo");



    }

    private void calcularhoraFin() {

        date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha1 = fecha.format(date);
        txtHorafin.setText(fecha1);
    }

    private void calcularhora() {

        date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha1 = fecha.format(date);
        txtHoraInicio.setText(fecha1);



    }

    private void ListaPhase() {

        List<String>phase = new ArrayList();
        phase.add("PLAN");
        phase.add("DLD");
        phase.add("CODE");
        phase.add("COMPILE");
        phase.add("UT");
        phase.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, phase);
        SpinnerPhase.setAdapter(adapter);

    }

    private void inicializar() {

        SpinnerPhase = findViewById(R.id.SpinnerFase);

        txtHoraInicio = findViewById(R.id.txtHoraInicio);
        txtInterrupcion = findViewById(R.id.txtInterrupcion);
        txtHorafin = findViewById(R.id.txtHoraFin);
        txtHoraDelta = findViewById(R.id.txtHoraDelta);
        txtComentario = findViewById(R.id.txtComentario);

        btnHoraStart = findViewById(R.id.btnHoraStart);
        btnHoraStop = findViewById(R.id.btnHoraFin);


    }

}
