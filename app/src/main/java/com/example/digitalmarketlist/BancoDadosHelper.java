package com.example.digitalmarketlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.digitalmarketlist.objetos.Lista;
import com.example.digitalmarketlist.objetos.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private static final String DONO_LISTA= "dono_lista";
    private static final String NOME_LISTA= "nome_lista";
    private static final String NOME_PRODUTO= "nome_produto";
    private static final String PRECO_PRODUTO= "preco_produto";


    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO + "(" + ID + " INTEGER PRIMARY KEY," + EMAIL
            + " TEXT," + SENHA + " TEXT," + CRIADO_EM
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_LISTA = "CREATE TABLE " + TABLE_LISTA
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + DONO_LISTA + " INTEGER,"
            + NOME_LISTA + " TEXT,"
            + NOME_PRODUTO + " TEXT,"
            + PRECO_PRODUTO + " DOUBLE,"
            + CRIADO_EM + " DATETIME" + ")";


    public BancoDadosHelper(Context context){
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

    long criarListaCompra(Lista lista){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DONO_LISTA, lista.getDono());
        values.put(NOME_LISTA, lista.getNome());
        values.put(NOME_PRODUTO, lista.getProduto());
        values.put(PRECO_PRODUTO, lista.getPreco());
        values.put(CRIADO_EM, getDateTime());

        long lista_id = db.insert(TABLE_LISTA, null, values);

        return lista_id;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
