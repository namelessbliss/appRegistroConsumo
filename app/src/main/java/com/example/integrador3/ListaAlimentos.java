package com.example.integrador3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.integrador3.Beans.Alimentos;
import com.example.integrador3.Conexion.BaseDatos;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListaAlimentos extends AppCompatActivity {
    ListView listViewAlimentos;
    SearchView sv;

    List<Alimentos> listaAlimentos = new ArrayList<>();
    ArrayAdapter<Alimentos> arrayAdapter;
     BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alimentos);

        inicializarComponentes();
        inicializarBancoDeDados();
        popularLista();

        Toolbar tb = findViewById(R.id.toolbar_buscar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getAlimento(newText);
            return false;
            }
        });

        listViewAlimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
           /* String info = "Alimento:"+ listaAlimentos.get(pos).getNombre()+"\n";
                    info+="Carbohidratos:"+listaAlimentos.get(pos).getCarbohidratos()+"\n";
                info+="Proteinas:"+listaAlimentos.get(pos).getProteinas()+"\n";
                info+="Grasas:"+listaAlimentos.get(pos).getGrasas()+"\n";
                info+="Fibra:"+listaAlimentos.get(pos).getFibra()+"\n";
                info+="Calorias:"+listaAlimentos.get(pos).getCalorias();

                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();

            */
                Alimentos ali = listaAlimentos.get(pos);
                Intent entra = new Intent(ListaAlimentos.this,AlimentosMacros.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("alimento",ali);
                entra.putExtras(bundle);
                startActivity(entra);

        }});
    }

    private void inicializarComponentes() {
        listViewAlimentos = findViewById(R.id.listV_Alimentos);
        sv= findViewById(R.id.sv);
    }

    private void popularLista() {
        bd = new BaseDatos(this);
        listaAlimentos.clear();
        listaAlimentos = bd.getallAlimento();
        arrayAdapter = new ArrayAdapter<Alimentos>(this,android.R.layout.simple_list_item_1,listaAlimentos);
        listViewAlimentos.setAdapter(arrayAdapter);
    }

    private void getAlimento(String searchTerm)
    {
        bd = new BaseDatos(this);
        listaAlimentos.clear();

        listaAlimentos = bd.FiltrarAlimenot(searchTerm);
        arrayAdapter = new ArrayAdapter<Alimentos>(this,android.R.layout.simple_list_item_1,listaAlimentos);
        listViewAlimentos.setAdapter(arrayAdapter);

    }

    private void inicializarBancoDeDados() {
        bd = new BaseDatos(this);
        File database = getApplicationContext().getDatabasePath(BaseDatos.NOMEDB);
        if (database.exists() == false){
            bd.getReadableDatabase();
            if (copiaBanco(this)){
                alert("Banco copiado com sucesso");
            }else{
                alert("Erro ao copiar o banco de dados");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BaseDatos.NOMEDB);
            String outFile = BaseDatos.LOCALDB + BaseDatos.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[2048];
            int legth = 0;
            while ((legth = inputStream.read(buff))>0){
                outputStream.write(buff,0,legth);
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
