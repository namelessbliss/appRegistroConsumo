package com.example.integrador3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integrador3.Conexion.BaseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Registro extends AppCompatActivity {
    Button btnGrabarUsu;
    EditText txtNomUsu, txtDisUsu, txtCorUsu, txtPasUsu;
    // SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BDTMB",null, 1);
    BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnGrabarUsu = (Button) findViewById(R.id.btnGrabarUsu);
        txtNomUsu = (EditText) findViewById(R.id.txtNomUsu);
        txtDisUsu = (EditText) findViewById(R.id.txtDisUsu);
        txtCorUsu = (EditText) findViewById(R.id.txtCorUsu);
        txtPasUsu = (EditText) findViewById(R.id.txtPasUsu);

        inicializarBancoDeDados();

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agregar();

                Toast.makeText(getApplicationContext(), "Registro Almacenado con Exito"
                        , Toast.LENGTH_LONG).show();
                Intent registerIntent = new Intent(Registro.this, MainActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    public void Agregar() {
        txtNomUsu = (EditText) findViewById(R.id.txtNomUsu);
        txtDisUsu = (EditText) findViewById(R.id.txtDisUsu);
        txtCorUsu = (EditText) findViewById(R.id.txtCorUsu);
        txtPasUsu = (EditText) findViewById(R.id.txtPasUsu);

        bd = new BaseDatos(this);
               /* helper.abrir();
                helper.insertarReg(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtDisUsu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtPasUsu.getText()));
                helper.cerrar();

                */
        bd.AgregarUsuario(String.valueOf(txtNomUsu.getText()),
                String.valueOf(txtDisUsu.getText()),
                String.valueOf(txtCorUsu.getText()),
                String.valueOf(txtPasUsu.getText()));
    }

    private void inicializarBancoDeDados() {
        bd = new BaseDatos(this);
        File database = getApplicationContext().getDatabasePath(BaseDatos.NOMEDB);
        if (database.exists() == false) {
            bd.getReadableDatabase();
            if (copiaBanco(this)) {
                bd.crearTablaConsumo();
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
            byte[] buff = new byte[2048];
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
