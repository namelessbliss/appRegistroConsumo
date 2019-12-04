package com.example.integrador3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.integrador3.Conexion.BaseDatos;

public class ControlMetabolico extends AppCompatActivity {
EditText txtpeso, txttalla, txtedad;
TextView tvtmb,tvrecomendacion,tvrecomendacion2,tvrecomendacion3;
Button btncalcular;
int cod;
    BaseDatos bd;
RadioButton rbthombre, rbtmujer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_metabolico);
        txtpeso = (EditText) findViewById(R.id.txtpeso);
        txttalla = (EditText) findViewById(R.id.txtalt);
        txtedad = (EditText) findViewById(R.id.txtedad);
        tvtmb = (TextView) findViewById(R.id.tvtmb);
        tvrecomendacion = (TextView) findViewById(R.id.tvcn);
        tvrecomendacion2 = (TextView) findViewById(R.id.tvcpd);
        tvrecomendacion3 = (TextView) findViewById(R.id.tvcpsp);
        rbthombre= (RadioButton) findViewById(R.id.rbthombre);
        rbtmujer= (RadioButton) findViewById(R.id.rbtmujer);


        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null) {

           cod = miBundle.getInt("cd");
        }
        //tnom.setText(myPreferences.getString("NB","-"));
    }

    //metodo calcular
    public void Calcular (View view){
        String valor1_string = txtpeso.getText().toString();
        String valor2_string = txttalla.getText().toString();
        String valor3_string = txtedad.getText().toString();

        double valor1_dou = Double.parseDouble(valor1_string);
        double valor2_dou = Double.parseDouble(valor2_string);
        double valor3_dou = Double.parseDouble(valor3_string);
        double calcular=0;

        if(rbthombre.isChecked() == true){
             calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)+5);
            String resultado = String.valueOf(calcular);
            tvtmb.setText(resultado);
        }else if(rbtmujer.isChecked() == true){
             calcular = ((10*valor1_dou)+ (6.25*valor2_dou)-(5*valor3_dou)-161);
            String resultado = String.valueOf(calcular);
            tvtmb.setText(resultado);
        }
        double r1 = calcular*1.2;
        tvrecomendacion.setText(String.valueOf(r1));
       bd = new BaseDatos(this);
       bd.AgregarTMB(r1,cod );
        Toast.makeText(this,"Exito al guardar",Toast.LENGTH_SHORT).show();
    }
}
