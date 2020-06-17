package com.example.digitalmarketlist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.digitalmarketlist.objetos.Lista;
import com.example.digitalmarketlist.objetos.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listaDeCompra";

    private static final String TABLE_USUARIO = "usuarios";
    private static final String TABLE_LISTA = "listas";

    // para todas tabelas
    private static final String ID = "id";
    private static final String CRIADO_EM = "criado_em";

    // usuário
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    //lista
    private static final String NOME = "nome";
    private static final String PRODUTO = "produto";
    private static final String QUANTIDADE = "quantidade";
    private static final String PRECO = "preco";


    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + EMAIL + " TEXT,"
            + SENHA + " TEXT,"
            + CRIADO_EM + " DATETIME" + ")";

    private static final String CREATE_TABLE_LISTA = "CREATE TABLE " + TABLE_LISTA
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + NOME + " TEXT,"
            + PRODUTO + " TEXT,"
            + QUANTIDADE + " INT,"
            + PRECO + " DOUBLE,"
            + CRIADO_EM + " DATETIME" + ")";


    BancoDadosHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_LISTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTA);

        onCreate(db);
    }

    long createUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMAIL, usuario.getEmail());
        values.put(SENHA, usuario.getSenha());
        values.put(CRIADO_EM, getDateTime());

        return db.insert(TABLE_USUARIO, null, values);
    }

    ArrayList<Long> createLista(ArrayList<Lista> listas) {
        int tamanhoLista = listas.size();
        ArrayList<Long> ids = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < tamanhoLista; i++) {
                values.put(NOME, listas.get(i).getNome());
                values.put(PRODUTO, listas.get(i).getProduto());
                values.put(QUANTIDADE, listas.get(i).getQuantidade());
                values.put(PRECO, listas.get(i).getPreco());
                values.put(CRIADO_EM, getDateTime());

                long id = db.insert(TABLE_LISTA, null, values);
                ids.add(id);
            }
        }catch(Exception e){
            Log.e("Error", Objects.requireNonNull(e.getLocalizedMessage()));
        }
        return ids;
    }

    Usuario buscarUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USUARIO + " WHERE "
                + EMAIL + " = '" + email + "' AND " + SENHA + " = '" + senha + "'";

        Log.e("LOG", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null){
            c.moveToFirst();
        }

        assert c != null;
        if (c.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(c.getInt(c.getColumnIndex(ID)));
            usuario.setEmail((c.getString(c.getColumnIndex(EMAIL))));
            usuario.setSenha((c.getString(c.getColumnIndex(SENHA))));

            c.close();
            return usuario;
        }
        return null;
    }

    ArrayList<String> buscarListas() {
        ArrayList<String> listas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * from listas GROUP BY nome";

        Log.e("LOG", selectQuery);

        @SuppressLint("Recycle") Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                String lista = "";
                lista = c.getString(c.getColumnIndex(NOME));
                listas.add(lista);
            } while (c.moveToNext());
        }
        return listas;
    }

    ArrayList<Lista> buscarItemListas(String nome) {
        ArrayList<Lista> listas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_LISTA + " WHERE "
                + NOME + " = '" + nome + "'";

        Log.e("LOG", selectQuery);

        @SuppressLint("Recycle") Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Lista lista = new Lista();
                lista.setNome(c.getString(c.getColumnIndex(NOME)));
                lista.setProduto(c.getString(c.getColumnIndex(PRODUTO)));
                lista.setQuantidade(c.getInt(c.getColumnIndex(QUANTIDADE)));
                lista.setPreco(c.getDouble(c.getColumnIndex(PRECO)));
                listas.add(lista);
            } while (c.moveToNext());
        }
        return listas;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
