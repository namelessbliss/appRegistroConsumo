package com.example.integrador3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    Button btnperfil, btncontrol, btnactividades, btnali, btnRegistroConsumo, btnResumen, btnCerrar;
    TextView tnom;
    int usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Menu.this);
        SharedPreferences.Editor myditor = myPreferences.edit();

        btnactividades = (Button) findViewById(R.id.btnactividades);
        btncontrol = (Button) findViewById(R.id.btncontrol);
        btnperfil = (Button) findViewById(R.id.btnPerfil);
        btnali = findViewById(R.id.btn_Alimentos);
        btnRegistroConsumo = findViewById(R.id.btn_Consumo);
        btnResumen = findViewById(R.id.btn_Resumen);
        btnCerrar = findViewById(R.id.btnCerrarSesion);
        tnom = findViewById(R.id.txtNombre);

        btnperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Menu.this, Perfil.class);
                startActivity(registerIntent);
            }
        });
        btncontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Menu.this, ControlMetabolico.class);
                Bundle miBundle = new Bundle();
                miBundle.putInt("cd", usu);

                registerIntent.putExtras(miBundle);
                startActivity(registerIntent);
            }
        });
        btnactividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Menu.this, Ejercicios.class);
                startActivity(registerIntent);
            }
        });
        btnali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iali = new Intent(Menu.this, ListaAlimentos.class);
                startActivity(iali);
            }
        });

        btnRegistroConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, RegistroConsumo.class);
                startActivity(i);
            }
        });

        btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ResumenConsumo.class);
                startActivity(i);
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        Bundle miBundle = this.getIntent().getExtras();

        if (miBundle != null) {

            String nom = miBundle.getString("Nombre");
            usu = miBundle.getInt("User");
            if (nom != null) {
                myditor.putString("NB", nom);
                myditor.putInt("UR", usu);
                myditor.commit();

            }
        }
        tnom.setText(myPreferences.getString("NB", "-"));
    }
}
