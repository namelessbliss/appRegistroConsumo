package com.example.integrador3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ejercicios extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText txttiem;
    TextView Calorias;
    Button btncalcular;
    RadioButton rbt1, rbt2, rbt3, rbt4;
    RadioGroup grupo1, grupo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);
        txttiem = (EditText) findViewById(R.id.txttiem);
        rbt1 = (RadioButton) findViewById(R.id.rbtbadminton);
        rbt2 = (RadioButton) findViewById(R.id.rbtbaile);
        rbt3 = (RadioButton) findViewById(R.id.aerobicos);
        rbt4 = (RadioButton) findViewById(R.id.cardio);
        Calorias = (TextView) findViewById(R.id.Calorias);
        grupo1 = findViewById(R.id.radioGroup1);
        grupo1.setOnCheckedChangeListener(this);
        grupo2 = findViewById(R.id.radioGroup2);
        grupo2.setOnCheckedChangeListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void Calcular(View view) {
        String valor1_string = txttiem.getText().toString();
        double valor1_dou = Double.parseDouble(valor1_string);
        double calcular = 0;

        if (rbt1.isChecked()) {
            calcular = ((valor1_dou) * (160) * 0.045);
            String resultado = String.valueOf(calcular);
            Calorias.setText(resultado);
        } else if (rbt2.isChecked()) {
            calcular = ((valor1_dou) + (65) - (160) * 0.052);
            String resultado = String.valueOf(calcular);
            Calorias.setText(resultado);
        } else if (rbt3.isChecked()) {
            //TODO calcular calorias
        } else if (rbt4.isChecked()) {
            //TODO calcular calorias
        }

    }

    private Boolean changeGroup = false;

    @SuppressLint("ResourceType")
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group != null && checkedId > -1 && !changeGroup) {
            if (group == grupo1) {
                changeGroup = true;
                grupo2.clearCheck();
                changeGroup = false;
            } else if (group == grupo2) {
                changeGroup = true;
                grupo1.clearCheck();
                changeGroup = false;
            }
        }
    }
}
