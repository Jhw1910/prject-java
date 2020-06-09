package com.example.digitalmarketlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listaDeCompra";

    private static final String TABLE_USUARIO = "usuarios";
    private static final String TABLE_PRODUTO = "produtos";
    private static final String TABLE_LISTA = "listas";
    private static final String TABLE_PRODUTO_LISTA = "produto_lista";

    // para todas tabelas
    private static final String ID = "id";
    private static final String CRIADO_EM = "criado_em";

    // usuário
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    //produto
    private static final String NOME_PRODUTO = "nome_produto";
    private static final String VALOR_PRODUTO = "valor_produto";

    //lista
    private static final String DONO_LISTA= "dono_lista";
    private static final String NOME_LISTA= "nome_lista";

    //produto lista
    private static final String PRODUTO_ID = "produto_id";
    private static final String LISTA_ID = "lista_id";

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO + "(" + ID + " INTEGER PRIMARY KEY," + EMAIL
            + " TEXT," + SENHA + " TEXT," + CRIADO_EM
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + TABLE_PRODUTO
            + "(" + ID + " INTEGER PRIMARY KEY," + NOME_PRODUTO + " TEXT,"
            + VALOR_PRODUTO + " DOUBLE,"
            + CRIADO_EM + " DATETIME" + ")";

    private static final String CREATE_TABLE_LISTA = "CREATE TABLE " + TABLE_LISTA
            + "(" + ID + " INTEGER PRIMARY KEY," + DONO_LISTA + " INTEGER,"
            + NOME_LISTA + " TEXT,"
            + CRIADO_EM + " DATETIME" + ")";

    private static final String CREATE_TABLE_PRODUTO_LISTA = "CREATE TABLE "
            + TABLE_PRODUTO_LISTA + "(" + ID + " INTEGER PRIMARY KEY,"
            + PRODUTO_ID + " INTEGER," + LISTA_ID + " INTEGER,"
            + CRIADO_EM + " DATETIME" + ")";

    public BancoDadosHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("aaaaaaa", "aaaaaaaaaaaaaaaaaaaa");
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_LISTA);
        db.execSQL(CREATE_TABLE_PRODUTO_LISTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO_LISTA);

        onCreate(db);
    }

    public long createUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMAIL, usuario.getEmail());
        values.put(SENHA, usuario.getSenha());
        values.put(CRIADO_EM, getDateTime());

        long usuario_id = db.insert(TABLE_USUARIO, null, values);

        return usuario_id;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
