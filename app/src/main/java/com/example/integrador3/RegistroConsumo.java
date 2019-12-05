package com.example.integrador3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.integrador3.Beans.Alimentos;
import com.example.integrador3.Beans.Consumo;
import com.example.integrador3.Conexion.BaseDatos;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistroConsumo extends AppCompatActivity {

    private BaseDatos mSqlDb;
    Consumo consumo;
    ArrayList<Consumo> listaConsumo;

    ArrayList<Alimentos> listaProductosSelect;

    TableLayout tablaAlimentos;
    RadioGroup rgTipo;

    int tipoConsumo = 1;

    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_consumo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Alimentos);
        setSupportActionBar(toolbar);

        mSqlDb = new BaseDatos(this);

        tablaAlimentos = findViewById(R.id.tablaAlimentos);
        rgTipo = findViewById(R.id.radioGroup);

        consumo = new Consumo();
        listaConsumo = new ArrayList<>();

        obtenerFechaActual();

        obtenerListaProductoSeleccionada();

        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbDesayuno)
                    tipoConsumo = 1;
                else if (i == R.id.rbAlmuerzo)
                    tipoConsumo = 2;
                else if (i == R.id.rbCena)
                    tipoConsumo = 3;
                else if (i == R.id.rbOtro)
                    tipoConsumo = 4;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void obtenerFechaActual() {
        //fecha
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int month = mMonth + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        fecha = mDay + "-" + month + "-" + mYear;
    }

    private void obtenerListaProductoSeleccionada() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            listaProductosSelect = new ArrayList<>();
            Bundle b2 = b.getBundle("bundle");
            listaProductosSelect = (ArrayList<Alimentos>) b2.getSerializable("listaAlimento");

            llenarTabla(listaProductosSelect);
        }
    }

    private void llenarTabla(final ArrayList<Alimentos> listaProductosSelect) {
        int id = 0;

        for (Alimentos objAlimento : listaProductosSelect) {

            Consumo objConsumo = new Consumo();
            objConsumo.setConsumo(objAlimento.getNombre());
            //objConsumo.setCalorias(objAlimento.getCalorias());
            objConsumo.setFecha(fecha);
            listaConsumo.add(objConsumo);

            //Instancia de nueva fila
            TableRow row = new TableRow(this);
            //Instancias de objetos de la ui
            TextView alimento = new TextView(this);
            TextView calorias = new TextView(this);
            final EditText cantidad = new EditText(this);
            final TextView total = new TextView(this);

            alimento.setText(objAlimento.getNombre());
            alimento.setPadding(2, 1, 2, 1);
            alimento.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textoTabla));
            alimento.setMaxLines(1);
            alimento.setGravity(Gravity.CENTER);

            calorias.setText(String.valueOf(objAlimento.getCalorias()));
            alimento.setPadding(2, 1, 2, 1);
            calorias.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textoTabla));
            calorias.setMaxLines(1);
            calorias.setGravity(Gravity.CENTER);

            cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
            cantidad.setId(id);
            cantidad.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textoTabla));
            cantidad.setMaxLines(1);
            cantidad.setText("1");
            //Limita cantidad de caracteres
            InputFilter[] fa = new InputFilter[1];
            fa[0] = new InputFilter.LengthFilter(8);
            cantidad.setFilters(fa);
            cantidad.setGravity(Gravity.CENTER);

            total.setMaxLines(1);
            total.setText(String.valueOf(0));
            total.setId(id);
            total.setPadding(2, 1, 2, 1);
            total.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textoTabla));
            total.setGravity(Gravity.CENTER);
            total.setText(objAlimento.getCalorias() * Integer.parseInt(cantidad.getText().toString()) + "");
            listaConsumo.get(id).setCalorias(Double.parseDouble(total.getText().toString()));

            row.addView(alimento);
            row.addView(calorias);
            row.addView(cantidad);
            row.addView(total);
            tablaAlimentos.addView(row);

            cantidad.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int i, KeyEvent keyEvent) {
                    if (keyEvent != null) {
                        EditText editText = (EditText) v;
                        int index = editText.getId();
                        int cantidad;
                        double suma;
                        if (!editText.getText().toString().isEmpty()) {
                            cantidad = Integer.parseInt(editText.getText().toString());
                            suma = listaProductosSelect.get(index).getCalorias() * cantidad;
                            // actualiza la lista detalle
                            listaConsumo.get(index).setCalorias(suma);
                        } else {
                            cantidad = 0;
                            suma = 0;
                        }
                        total.setText(String.valueOf(suma));
                    }
                    return false;
                }
            });

            id++;
        }
    }

    public void onRegistrarConsumo(View view) {
        establecerTipoConsumo(tipoConsumo);
        ocultarTeclado(tablaAlimentos);
        registrarConsumo();
    }

    public static void ocultarTeclado(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void registrarConsumo() {

        mSqlDb.registrarConsumo(listaConsumo);

        Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void onAddItemAlimento(View view) {
        Intent intent = new Intent(getApplicationContext(), SeleccionAlimento.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void establecerTipoConsumo(int tipo) {
        String sTipo = "";
        switch (tipo) {
            case 1: //Desayuno
                sTipo = "Desayuno";
                break;
            case 2: //alm
                sTipo = "Almuerzo";
                break;
            case 3: // cena
                sTipo = "Cena";
                break;
            case 4:
                sTipo = "Otro";
                break;
        }

        for (Consumo consumo : listaConsumo) {
            consumo.setTipoConsumo(sTipo);
        }
    }

}
