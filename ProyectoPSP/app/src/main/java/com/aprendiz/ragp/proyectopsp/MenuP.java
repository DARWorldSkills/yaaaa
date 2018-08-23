package com.aprendiz.ragp.proyectopsp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.proyectopsp.Controllers.Menu_proyecto;
import com.aprendiz.ragp.proyectopsp.models.AdapterI;
import com.aprendiz.ragp.proyectopsp.models.CProyecto;
import com.aprendiz.ragp.proyectopsp.models.ManagerDB;

import java.util.List;

public class MenuP extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    public static CProyecto cProyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        inizialite();
        inputAdapter();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MenuP.this);
                dialog.setContentView(R.layout.item_proyecto_d);
                final EditText txtNombre = dialog.findViewById(R.id.txtNombre);
                Button btnAceptar = dialog.findViewById(R.id.btnAceptar);
                Button btnCancelar = dialog.findViewById(R.id.btnCancelar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombre = txtNombre.getText().toString();
                        if (nombre.length()>0) {
                            ManagerDB managerDB = new ManagerDB(MenuP.this);
                            CProyecto cProyecto = new CProyecto();
                            cProyecto.setNombre(nombre);
                            managerDB.insertProject(cProyecto);
                            Toast.makeText(MenuP.this, "Se ha creado el proyecto correctamente", Toast.LENGTH_SHORT).show();
                            inputAdapter();
                            dialog.cancel();

                        }else {
                            txtNombre.setError("Por favor ingrese el nombre");
                            Toast.makeText(MenuP.this, "No puede dejar el campo vacio", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
    }



    private void inizialite() {
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    private void inputAdapter() {
        ManagerDB managerDB = new ManagerDB(this);
        final List<CProyecto> cProyectos = managerDB.selectProject();
        AdapterI adapterI = new AdapterI(cProyectos);
        recyclerView.setAdapter(adapterI);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterI.setMlistener(new AdapterI.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                cProyecto=cProyectos.get(position);
                Intent intent = new Intent(MenuP.this,Menu_proyecto.class);
                startActivity(intent);

            }
        });


    }
}
