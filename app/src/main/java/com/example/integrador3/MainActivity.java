package com.example.integrador3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.integrador3.Beans.Usuarios;
import com.example.integrador3.Conexion.BaseDatos;

import java.util.ArrayList;
import java.util.List;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {
    TextView mTextViewRegister;
    Button btnIngresar;
SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BDTMB",null,1);
    BaseDatos bd;
    List<Usuarios> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,Registro.class);
                startActivity(registerIntent);
            }
        });

       btnIngresar=(Button)findViewById(R.id.btnIngresar);
       btnIngresar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText txtusu= (EditText)findViewById(R.id.txtUsuarios);
               EditText txtpas= (EditText)findViewById(R.id.txtPassword);
   bd = new BaseDatos(MainActivity.this);
               try {
                   boolean cursor = bd.ConsultarUsuario(txtusu.getText().toString(),
                           txtpas.getText().toString());
                   if (cursor=true){
                       list = new ArrayList<>();
                       list.clear();
                       list = bd.NomUsuario(txtusu.getText().toString(),txtpas.getText().toString());
                       Usuarios u = list.get(0);
                       String n = u.getNombre();
                       int user = u.getCodigo();
                       Intent i= new Intent(getApplicationContext(),Menu.class);
                       Bundle miBundle=new Bundle();
                       miBundle.putString("Nombre", n) ;
                       miBundle.putInt("User", user);
                       i.putExtras(miBundle);
                       startActivity(i);
                   } else{
                       Toast.makeText(getApplicationContext(),"usuario y/o password incorrectos",
                               Toast.LENGTH_LONG).show();
                   }
                   txtusu.setText("");
                   txtpas.setText("");
                   txtusu.findFocus();
               } catch (SQLException e) {
                   e.printStackTrace();
               }


               bd.close();
           }

       });
    }
}
