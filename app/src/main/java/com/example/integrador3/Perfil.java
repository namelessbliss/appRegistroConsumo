package com.example.integrador3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    Button btncalcularimc,btncalcularrmb;
    TextView txtrmb;
    TextView txtimc;
    String imc,rmb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btncalcularimc = (Button) findViewById(R.id.btncalcularimc);
        btncalcularrmb = (Button) findViewById(R.id.btncalcularRMB);
        btncalcularimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Perfil.this,CalcularIMC.class);
                startActivity(registerIntent);
            }
        });
        btncalcularrmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Perfil.this,CalcularRMB.class);
                startActivity(registerIntent);
            }
        });

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Perfil.this);
        SharedPreferences.Editor myditor = myPreferences.edit();

        txtrmb = findViewById(R.id.txtrmb);
        txtimc = findViewById(R.id.txtimc);
        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null){

            rmb=miBundle.getString("rmb");
            if(rmb!=null){
                myditor.putString("RMB",rmb);
                myditor.commit();

            }

            //txtrmb.setText(rmb);
            //txtimc.getText();
        }
        Bundle meBuncle=this.getIntent().getExtras();
        if(meBuncle!=null){
             imc = miBundle.getString("imc");
             if(imc!=null){
                 myditor.putString("IMC",imc);
                 myditor.commit();
             }

           // txtimc.setText(imc);
           // txtrmb.getText();
        }




        txtimc.setText(myPreferences.getString("IMC","-"));
        txtrmb.setText(myPreferences.getString("RMB","-"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
