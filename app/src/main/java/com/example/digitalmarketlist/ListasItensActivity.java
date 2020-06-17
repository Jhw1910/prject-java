package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.digitalmarketlist.adapter.MinhaLista;
import com.example.digitalmarketlist.objetos.Lista;

import java.util.ArrayList;

public class ListasItensActivity extends AppCompatActivity {
    BancoDadosHelper bancoDadosHelper;
    private MinhaLista minhaListaAdapter;
    ArrayList<Lista> minhaLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nomeLista");

        setContentView(R.layout.activity_listas_itens);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        minhaLista = bancoDadosHelper.buscarItemListas(nome);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        minhaListaAdapter = new MinhaLista(this, minhaLista);
        recyclerView.setAdapter(minhaListaAdapter);

    }
}
