package com.example.integrador3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalcularRMB extends AppCompatActivity {
    Spinner spiner;
    EditText txtpeso, txttalla, txtedad;
    TextView tvtmb,tvrecomendacion,tvrecomendacion2,tvrecomendacion3;
    Button btncalcular;
    RadioButton rbthombre, rbtmujer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_rmb);
        txtpeso = (EditText) findViewById(R.id.txtpeso);
        txttalla = (EditText) findViewById(R.id.txtalt);
        txtedad = (EditText) findViewById(R.id.txtedad);
        tvtmb = (TextView) findViewById(R.id.tvtmb);
        tvrecomendacion = (TextView) findViewById(R.id.tvcn);
        tvrecomendacion2 = (TextView) findViewById(R.id.tvcpd);
        tvrecomendacion3 = (TextView) findViewById(R.id.tvcpsp);
        rbthombre= (RadioButton) findViewById(R.id.rbthombre);
        rbtmujer= (RadioButton) findViewById(R.id.rbtmujer);
        spiner = (Spinner) findViewById(R.id.spiner);



        String[]opciones = {"Seleccionar actvidad","-baja-","Media", "Alta", "Muy alta"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spiner.setAdapter(adapter);

        tvtmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent=new Intent(CalcularRMB.this,Perfil.class);

                Bundle miBundle=new Bundle();
                miBundle.putString("rmb",tvtmb.getText().toString());

                miIntent.putExtras(miBundle);

                startActivity(miIntent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //metodo calcular
    public void Calcular (View view){
        String select = spiner.getSelectedItem().toString();
        String valor1_string = txtpeso.getText().toString();
        String valor2_string = txttalla.getText().toString();
        String valor3_string = txtedad.getText().toString();

        double valor1_dou = Double.parseDouble(valor1_string);
        double valor2_dou = Double.parseDouble(valor2_string);
        double valor3_dou = Double.parseDouble(valor3_string);
        double calcular=0;
        double calact=0;


       /* if(rbthombre.isChecked() == true){
            calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            String resultado = String.valueOf(calcular);
            tvtmb.setText(resultado);
        }else if(rbtmujer.isChecked() == true){
            calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            String resultado = String.valueOf(calcular);
            tvtmb.setText(resultado);
        }*/

        if (select.equals("-baja-")){
            if(rbthombre.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            }else if(rbtmujer.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            }
            calact=calcular*1.3;

        }
        if (select.equals("Media")){
            if(rbthombre.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            }else if(rbtmujer.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            }
            calact=calcular*1.5;

        }
        if (select.equals("Alta")){
            if(rbthombre.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            }else if(rbtmujer.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            }
            calact=calcular*1.7;

        }
        if (select.equals("Muy alta")){
            if(rbthombre.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            }else if(rbtmujer.isChecked() == true){
                calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            }
            calact=calcular*1.8;

        }
        //Obtenemos una instancia de
//la clase
        NumberFormat nf = NumberFormat.getInstance();
//Establecemos el numero de decimales
        nf.setMaximumFractionDigits(2);
//Convertimos el numero
        String st=nf.format(calact);
//lo vuelves a convertir a doubl

        String resultado = st;
        tvtmb.setText(resultado);
      double r1 = calact*1.35;
        String sr1=nf.format(r1);
        tvrecomendacion.setText(sr1);
        double r2 = calact*1.2;
        String sr2=nf.format(r2);
        tvrecomendacion2.setText(sr2);
        double r3 = calact*1.90;
        String sr3=nf.format(r3);
        tvrecomendacion3.setText(sr3);

      /*  switch (view.getId()){
            case R.id.btncalcular:

                Intent miIntent=new Intent(CalcularRMB.this,Perfil.class);

                Bundle miBundle=new Bundle();
                miBundle.putString("rmb",tvtmb.getText().toString());

                miIntent.putExtras(miBundle);

                startActivity(miIntent);
                break;
        }*/

    }

}

