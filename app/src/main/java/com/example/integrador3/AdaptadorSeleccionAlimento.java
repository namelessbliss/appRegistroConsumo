package com.example.integrador3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.integrador3.Beans.Alimentos;

import java.util.List;

public class AdaptadorSeleccionAlimento extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<Alimentos> lista;

    public AdaptadorSeleccionAlimento(Context context, int layout, List<Alimentos> list) {
        this.contexto = context;
        this.layout = layout;
        this.lista = list;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        AdaptadorSeleccionAlimento.ViewHolder holder;

        if (convertView == null) {

            convertView = LayoutInflater.from(contexto).inflate(layout, null);
            holder = new AdaptadorSeleccionAlimento.ViewHolder();
            holder.nombreAlimento = (CheckedTextView) convertView.findViewById(R.id.tvNombreALimento);
            holder.calorias = (TextView) convertView.findViewById(R.id.tvCalorias);
            convertView.setTag(holder);

        } else {

            holder = (AdaptadorSeleccionAlimento.ViewHolder) convertView.getTag();

            Alimentos alimento = lista.get(position);

            if (alimento.getNombre().isEmpty()) {
                holder.nombreAlimento.setVisibility(View.GONE);
                holder.calorias.setVisibility(View.GONE);
            } else {
                holder.nombreAlimento.setVisibility(View.VISIBLE);
                holder.calorias.setVisibility(View.VISIBLE);
            }

            if (alimento.isSelected()) {
                if (!alimento.getNombre().isEmpty())
                    holder.nombreAlimento.setChecked(true);
            } else
                holder.nombreAlimento.setChecked(false);

        }

        final Alimentos currentItem = (Alimentos) getItem(position);

        if (currentItem.getNombre().isEmpty()) {
            holder.nombreAlimento.setVisibility(View.GONE);
            holder.calorias.setVisibility(View.GONE);
        } else {
            holder.nombreAlimento.setVisibility(View.VISIBLE);
            holder.calorias.setVisibility(View.VISIBLE);
        }

        holder.nombreAlimento.setText(currentItem.getNombre());
        if (!currentItem.getNombre().isEmpty())
            holder.calorias.setText("Calorias: " + currentItem.getCalorias());
        else
            holder.calorias.setText("");

        return convertView;
    }

    public void updateRecords(List<Alimentos> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private CheckedTextView nombreAlimento;
        private TextView calorias;
    }
}
