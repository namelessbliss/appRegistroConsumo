package com.example.integrador3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.integrador3.Beans.Alimentos;
import com.example.integrador3.Conexion.BaseDatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AlimentosMacros extends AppCompatActivity {

    BaseDatos bd;

    TextView t_ali;
    TextView t_carbo;
    TextView t_prote;
    TextView t_grasa;
    TextView t_fib;
    TextView t_cal;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_macros);

      //  inicializarBancoDeDados();

        Toolbar tb = findViewById(R.id.toolbar_Alimentos);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        t_ali = findViewById(R.id.txt_Alimento);
        t_carbo = findViewById(R.id.txt_carbo);
        t_prote = findViewById(R.id.txt_prote);
        t_grasa = findViewById(R.id.txt_Grasa);
        t_fib = findViewById(R.id.txt_fibra);
        t_cal = findViewById(R.id.txt_cal);
        btn = findViewById(R.id.Btn_Buscar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlimentosMacros.this, ListaAlimentos.class);
                startActivity(intent);
            }
        });



        Bundle objenviado = getIntent().getExtras();
        Alimentos ali = null;

        if (objenviado!=null){
            ali = (Alimentos) objenviado.getSerializable("alimento");
            t_ali.setText(ali.getNombre());
            t_carbo.setText(ali.getCarbohidratos().toString());
            t_prote.setText(ali.getProteinas().toString());
            t_grasa.setText(ali.getGrasas().toString());
            t_fib.setText(ali.getFibra().toString());
            t_cal.setText(ali.getCalorias().toString());
        }

    }


    private void inicializarBancoDeDados() {
        bd = new BaseDatos(this);
        File database = getApplicationContext().getDatabasePath(BaseDatos.NOMEDB);
        if (database.exists() == false) {
            bd.getReadableDatabase();
            if (copiaBanco(this)) {
                alert("Banco copiado com sucesso");
            } else {
                alert("Erro ao copiar o banco de dados");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BaseDatos.NOMEDB);
            String outFile = BaseDatos.LOCALDB + BaseDatos.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int legth = 0;
            while ((legth = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, legth);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
