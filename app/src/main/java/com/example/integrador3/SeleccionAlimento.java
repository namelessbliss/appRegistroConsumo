package com.example.integrador3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.integrador3.Beans.Alimentos;
import com.example.integrador3.Conexion.BaseDatos;

import java.util.ArrayList;

public class SeleccionAlimento extends AppCompatActivity {

    private BaseDatos mSqlDb;

    private SearchView searchView;

    private boolean cargaInicial = false;

    private ListView lvAlimentosPicker;
    private AdaptadorSeleccionAlimento adaptadorListView;

    private MenuItem guardarSeleccion;

    ArrayList<Alimentos> listaAlimento;
    ArrayList<Alimentos> listaSeleccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_alimento);

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);

        mSqlDb = new BaseDatos(this);
        listaAlimento = new ArrayList<>();
        listaSeleccion = new ArrayList<>();

        getAlimentos();

        lvAlimentosPicker = (ListView) findViewById(R.id.lvAlimentosPicker);

        adaptadorListView = new AdaptadorSeleccionAlimento(this, R.layout.list_view_pick_alimento, listaAlimento);

        lvAlimentosPicker.setAdapter(adaptadorListView);

        registerForContextMenu(lvAlimentosPicker);

        mostrarListViewMultipleChoiceChecked();

        // Establece accion para la barra de busqueda
        habilitarBusqueda();

        cargaInicial = true;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getAlimentos() {
        listaAlimento = (ArrayList<Alimentos>) mSqlDb.getallAlimento();
    }

    private void mostrarListViewMultipleChoiceChecked() {

        this.lvAlimentosPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alimentos alimento = (Alimentos) parent.getAdapter().getItem(position);
                if (!alimento.getNombre().isEmpty()) {
                    if (alimento.isSelected()) {
                        alimento.setSelected(false);
                        listaSeleccion.remove(alimento);
                    } else {
                        alimento.setSelected(true);
                        listaSeleccion.add(alimento);
                    }
                    listaAlimento.set(position, alimento);

                    adaptadorListView.updateRecords(listaAlimento);
                }
            }
        });

    }

    private void habilitarBusqueda() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (cargaInicial) {
                    if (newText.toLowerCase().isEmpty()) {
                        listaAlimento.set(0, new Alimentos("", "", 0, 0, 0, 0, 0));
                        adaptadorListView.updateRecords(listaAlimento);
                    } else {
                        for (Alimentos alimento : listaAlimento) {
                            if (alimento.getNombre().toLowerCase().contains(newText.toLowerCase())) {
                                listaAlimento.set(0, alimento);
                            }
                        }
                        adaptadorListView.notifyDataSetChanged();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el option menu con nuestro layout personalizado
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_seleccion_alimento, menu);
        // luego de inflar , recogemos las referencias a los botones que nos interesan
        guardarSeleccion = menu.findItem(R.id.Guardar);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Eventos para los clicks en los botones del option menu
        switch (item.getItemId()) {
            case R.id.Guardar:
                guardarSeleccion(new RegistroConsumo());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void guardarSeleccion(AppCompatActivity activity) {

        Bundle b = new Bundle();

        b.putSerializable("listaAlimento", listaSeleccion);
        Intent intent = new Intent(SeleccionAlimento.this, activity.getClass());
        intent.putExtra("bundle", b);
        startActivity(intent);
        finish();
    }

}
