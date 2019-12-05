package com.example.integrador3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integrador3.Beans.Consumo;
import com.example.integrador3.Conexion.BaseDatos;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ResumenConsumo extends AppCompatActivity {

    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<String>();
    BarDataSet bardataset;
    BarData data;
    BarChart barChart;

    private BaseDatos mSqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_consumo);
        mSqlDb = new BaseDatos(this);

        barChart = findViewById(R.id.chart1);

        getSetData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getSetData() {
        ArrayList<Consumo> lista = new ArrayList<>();
        lista = mSqlDb.sieteUltimosDiasRegistrados();
        for (int j = 0; j < 7; j++) {
            if (j >= lista.size()) {
                entries.add(new BarEntry(0f, j));
                bardataset = new BarDataSet(entries, "Cells");
                labels.add("Sin Datos");
                data = new BarData(labels, bardataset);
            } else {
                float val = (float) lista.get(j).getCalorias();
                entries.add(new BarEntry(val, j));
                bardataset = new BarDataSet(entries, "Cells");

                labels.add(lista.get(j).getFecha());
                data = new BarData(labels, bardataset);
            }
        }


        barChart.setData(data);
        barChart.setDescription("Resumen semanal");
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(1000);
    }

}
