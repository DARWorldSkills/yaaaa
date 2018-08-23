package com.aprendiz.ragp.proyectopsp.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aprendiz.ragp.proyectopsp.R;

public class Menu_proyecto extends AppCompatActivity implements View.OnClickListener {

    Button btnTimer, btnDefect, btnPlanSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyecto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        inicializar();
        llamar();
    }

    private void llamar() {

        btnTimer.setOnClickListener(this);
        btnDefect.setOnClickListener(this);
        btnPlanSummary.setOnClickListener(this);
    }

    private void inicializar() {

        btnTimer = findViewById(R.id.btnTimerLog);
        btnDefect = findViewById(R.id.btnDefectLog);
        btnPlanSummary = findViewById(R.id.btnPlanSummary);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTimerLog:
                Intent intent = new Intent(Menu_proyecto.this, Timer_Log.class);
                startActivity(intent);

                break;


            case R.id.btnDefectLog:
                Intent intent1 = new Intent(Menu_proyecto.this, Defect_Log.class);
                startActivity(intent1);


                break;


            case R.id.btnPlanSummary:

                Intent intent2 = new Intent(Menu_proyecto.this, MainActivity.class);
                startActivity(intent2);


                break;

        }
    }
}
