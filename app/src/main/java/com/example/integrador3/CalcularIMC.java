package com.example.integrador3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcularIMC extends AppCompatActivity {
    EditText txtpeso, txtaltura;
    TextView txtImc,twmensaje,twmen;
    Button btnCalcular,btnBorrar;
    double peso, altura, imc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_imc);
        txtpeso = (EditText) findViewById(R.id.txtpeso);
        txtaltura = (EditText) findViewById(R.id.txtaltura);
        txtImc = (TextView) findViewById(R.id.twimc);
        twmensaje= (TextView) findViewById(R.id.twmensaje);
        twmen= (TextView)findViewById(R.id.twmen);

        btnCalcular = (Button) findViewById(R.id.btncalcular);
        btnBorrar = (Button) findViewById(R.id.borrar);

        txtImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent=new Intent(CalcularIMC.this,Perfil.class);

                Bundle miBundle=new Bundle();
                miBundle.putString("imc", String.valueOf(imc)) ;

                miIntent.putExtras(miBundle);

                startActivity(miIntent);
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                peso = Double.parseDouble(txtpeso.getText().toString());
                altura = Double.parseDouble(txtaltura.getText().toString());
                imc = peso / Math.pow(altura, 2);
                if (imc < 18.5) {
                    txtImc.setText("" + imc);
                    twmensaje.setText("Bajo de peso");
                    twmen.setText("Debe subir de peso");
                } else {
                    if (imc < 25) {
                        txtImc.setText("" + imc);
                            twmensaje.setText("Peso Normal");
                            twmen.setText("Esta en el peso ideal");
                        } else {
                            if (imc < 30) {
                                txtImc.setText("" + imc);
                                twmensaje.setText("Sobrepeso");
                            twmen.setText("Debe bajar de peso");
                        } else {
                            txtImc.setText("" + imc);
                            twmensaje.setText("Obesidad");
                            twmen.setText("Debe bajar de peso urgente");
                        }
                    }
                }

               /* Intent miIntent=new Intent(CalcularIMC.this,Perfil.class);

                Bundle miBundle=new Bundle();
                miBundle.putString("imc", String.valueOf(imc)) ;

                miIntent.putExtras(miBundle);

                startActivity(miIntent);

                */
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    }
