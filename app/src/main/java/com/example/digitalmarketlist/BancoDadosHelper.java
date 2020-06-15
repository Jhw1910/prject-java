package com.example.digitalmarketlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.digitalmarketlist.objetos.GroceryContract;
import com.example.digitalmarketlist.objetos.Lista;
import com.example.digitalmarketlist.objetos.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BancoDadosHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listaDeCompra";

    private static final String TABLE_USUARIO = "usuarios";
    private static final String TABLE_PRODUTO = "produtos";
    private static final String TABLE_LISTA = "listas";

    // para todas tabelas
    private static final String ID = "id";
    private static final String CRIADO_EM = "criado_em";

    // usuário
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    //produto
    private static final String ID_LISTA = "id_lista";
    private static final String NOME_PRODUTO = "nome_produto";
    private static final String VALOR_PRODUTO = "valor_produto";

    //lista
    private static final String DONO_LISTA= "dono_lista";
    private static final String NOME_LISTA= "nome_lista";


    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + EMAIL + " TEXT,"
            + SENHA + " TEXT,"
            + CRIADO_EM + " DATETIME" + ")";

    private static final String CREATE_TABLE_PRODUTO = "CREATE TABLE " + TABLE_PRODUTO
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + ID_LISTA + " INTEGER,"
            + NOME_PRODUTO + " TEXT,"
            + VALOR_PRODUTO + " DOUBLE,"
            + CRIADO_EM + " DATETIME" + ")";

    private static final String CREATE_TABLE_LISTA = "CREATE TABLE " + TABLE_LISTA
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + DONO_LISTA + " INTEGER,"
            + NOME_LISTA + " TEXT,"
            + CRIADO_EM + " DATETIME" + ")";


    public BancoDadosHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                GroceryContract.GroceryEntry.TABLE_NAME + " (" +
                GroceryContract.GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryContract.GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                GroceryContract.GroceryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_LISTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
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

    long criarListaCompra(Lista lista){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DONO_LISTA, lista.getDono());
        values.put(NOME_LISTA, lista.getNome());
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
