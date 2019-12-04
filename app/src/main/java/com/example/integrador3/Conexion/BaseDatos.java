package com.example.integrador3.Conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.integrador3.Beans.Alimentos;
import com.example.integrador3.Beans.Consumo;
import com.example.integrador3.Beans.Usuarios;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by williangcarv on 22/08/17.
 */

public class BaseDatos extends SQLiteOpenHelper {
    public static final String NOMEDB = "atmb";
    public static final String LOCALDB = "/data/data/com.example.integrador3/databases/";
    private static final int VERSION = 3;
    private Context mContext;
    private SQLiteDatabase mSQSqLiteDatabase;


    public BaseDatos(Context context) {
        super(context, NOMEDB, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void openDataBase() {
        String dbPath = mContext.getDatabasePath(NOMEDB).getPath();
        if (mSQSqLiteDatabase != null && mSQSqLiteDatabase.isOpen()) {
            return;
        }
        mSQSqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public List<Alimentos> getallAlimento() {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        List<Alimentos> listPessoa = new ArrayList<>();
        String sql = "SELECT * FROM Alimentos ORDER BY Nombre";
        Cursor cursor = mSQSqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Alimentos p = new Alimentos();
                    p.setCodigo(cursor.getString(0));
                    p.setNombre(cursor.getString(1));
                    p.setCalorias(cursor.getInt(2));
                    p.setCarbohidratos(cursor.getInt(3));
                    p.setProteinas(cursor.getInt(4));
                    p.setGrasas(cursor.getInt(5));
                    p.setFibra(cursor.getInt(6));
                    listPessoa.add(p);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSQSqLiteDatabase.close();
        return listPessoa;

    }

    public List<Alimentos> FiltrarAlimenot(String search) {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        List<Alimentos> listPessoa = new ArrayList<>();
        String sql = "SELECT * FROM Alimentos WHERE Nombre LIKE'%" + search + "%'";
        Cursor cursor = mSQSqLiteDatabase.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Alimentos p = new Alimentos();
                    p.setCodigo(cursor.getString(0));
                    p.setNombre(cursor.getString(1));
                    p.setCalorias(cursor.getInt(2));
                    p.setCarbohidratos(cursor.getInt(3));
                    p.setProteinas(cursor.getInt(4));
                    p.setGrasas(cursor.getInt(5));
                    p.setFibra(cursor.getInt(6));
                    listPessoa.add(p);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        mSQSqLiteDatabase.close();
        return listPessoa;

    }

    public void AgregarUsuario(String nom, String dis, String cor, String pas) {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        String query = "create table if not exists usuarios (_ID integer primary key autoincrement," +
                "Nombre text, Distrito text, Correo text, Password text);";
        mSQSqLiteDatabase.execSQL(query);

        ContentValues valores = new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Distrito", dis);
        valores.put("Correo", cor);
        valores.put("Password", pas);
        mSQSqLiteDatabase.insert("usuarios", null, valores);
        mSQSqLiteDatabase.close();
    }

    public boolean ConsultarUsuario(String usu, String pas) {
        openDataBase();
        mSQSqLiteDatabase = this.getReadableDatabase();
        //String query = "SELECT *  FROM usuarios WHERE Nombre like '" + usu + "' and Password like '" + pas + "'";
        Cursor mcursor = mSQSqLiteDatabase.query("usuarios", new String[]{"_ID",
                "Nombre", "Distrito", "Correo", "Password"}, "Nombre like '" + usu + "' " +
                "and Password like '" + pas + "'", null, null, null, null);
        if (mcursor.getCount() > 0) {
            mcursor.close();
            mSQSqLiteDatabase.close();
            return true;
        } else {
            return false;
        }

    }

    public List<Usuarios> NomUsuario(String usu, String pas) {
        String nom;
        openDataBase();
        mSQSqLiteDatabase = this.getReadableDatabase();
        List<Usuarios> listusu = new ArrayList<>();
        //String query = "SELECT *  FROM usuarios WHERE Nombre like '" + usu + "' and Password like '" + pas + "'";
        Cursor mcursor = mSQSqLiteDatabase.query("usuarios", new String[]{"_ID",
                "Nombre", "Distrito", "Correo", "Password"}, "Nombre like '" + usu + "' " +
                "and Password like '" + pas + "'", null, null, null, null);
        if (mcursor.getCount() > 0) {
            if (mcursor.moveToFirst()) {
                do {
                    Usuarios u = new Usuarios();
                    u.setCodigo(Integer.parseInt(mcursor.getString(0)));
                    u.setNombre(mcursor.getString(1));
                    listusu.add(u);
                } while (mcursor.moveToNext());
            }

        }

        mcursor.close();
        mSQSqLiteDatabase.close();
        return listusu;

    }

    public void AgregarTMB(double tmb, Integer cod) {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        String query = "create table if not exists Valores_Metabolicos (Codigo integer primary key autoincrement," +
                "IMC number, TMB number, RMB number, CodUsu integer);";
        mSQSqLiteDatabase.execSQL(query);
        ContentValues valores = new ContentValues();
        //valores.put ("IMC",imc);
        valores.put("TMB", tmb);
        //valores.put("RMB",rmb);
        valores.put("CodUsu", cod);

        mSQSqLiteDatabase.insert("Valores_Metabolicos", null, valores);
        mSQSqLiteDatabase.close();
    }

    public void AgregarValores(Integer imc, Integer tmb, Integer rmb, Integer cod) {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        String query = "create table if not exists Valores_Metabolicos (Codigo integer primary key autoincrement," +
                "IMC number, TMB number, RMB number, CodUsu integer);";
        mSQSqLiteDatabase.execSQL(query);
        ContentValues valores = new ContentValues();
        valores.put("IMC", imc);
        valores.put("TMB", tmb);
        valores.put("RMB", rmb);
        valores.put("CodUsu", cod);

        mSQSqLiteDatabase.insert("Valores_Metabolicos", null, valores);
        mSQSqLiteDatabase.close();
    }


    public void crearTablaConsumo() {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        String query = "create table if not exists Consumo (idConsumo integer primary key autoincrement," +
                "fecha text, consumo text, calorias real, tipo text);";
        mSQSqLiteDatabase.execSQL(query);
    }

    public boolean registrarConsumo(ArrayList<Consumo> listaConsumo) {
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();

        for (Consumo consumo : listaConsumo) {
            ContentValues cv = new ContentValues();
            cv.put("fecha", consumo.getFecha());
            cv.put("consumo", consumo.getConsumo());
            cv.put("calorias", consumo.getCalorias());
            cv.put("tipo", consumo.getTipoConsumo());

            mSQSqLiteDatabase.insert("Consumo", "idConsumo", cv);
        }
        mSQSqLiteDatabase.close();

        return true;
    }

    public ArrayList<Consumo> sieteUltimosDiasRegistrados() {
        int contador = 0;
        ArrayList<Consumo> listaConsumos = new ArrayList<>();
        mSQSqLiteDatabase = this.getReadableDatabase();
        String[] columnas = new String[]{"fecha", "SUM(calorias)"};
        Cursor cursor = mSQSqLiteDatabase.query("Consumo", columnas, null,
                null, "fecha", null, "fecha asc");
        while (cursor.moveToNext()) {
            if (contador <= 7) {
                Consumo consumo = new Consumo();
                consumo.setFecha(cursor.getString(0));
                consumo.setCalorias(cursor.getDouble(1));
                listaConsumos.add(consumo);
                contador++;
            } else {
                break;
            }
        }
        return listaConsumos;
    }
}
