package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.digitalmarketlist.adapter.MinhaLista;
import com.example.digitalmarketlist.adapter.MinhaListaHome;
import com.example.digitalmarketlist.objetos.Lista;

import java.util.ArrayList;

public class MinhasListasActivity extends AppCompatActivity implements MinhaListaHome.OnListaListener {
    BancoDadosHelper bancoDadosHelper;
    ArrayList<String> minhaLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        minhaLista = bancoDadosHelper.buscarListas();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MinhaListaHome minhaListaAdapter = new MinhaListaHome(this, minhaLista, this);
        recyclerView.setAdapter(minhaListaAdapter);

        Button novaLista = findViewById(R.id.btt_novo_minhas_listas);

        novaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MinhasListasActivity.this, CadastroListasActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListaClick(int position) {
        Intent intent = new Intent(this, ListasItensActivity.class);
        intent.putExtra("nomeLista", minhaLista.get(position));
        startActivity(intent);
    }
}
