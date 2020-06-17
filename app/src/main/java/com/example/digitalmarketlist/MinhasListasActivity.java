package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.digitalmarketlist.adapter.MinhaLista;
import com.example.digitalmarketlist.adapter.MinhaListaHome;
import com.example.digitalmarketlist.objetos.Lista;

import java.util.ArrayList;

public class MinhasListasActivity extends AppCompatActivity {
    private MinhaListaHome minhaListaAdapter;
    ArrayList<String> minhaLista = new ArrayList<>();
    private Button novaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

        minhaLista.add("Teste");
        minhaLista.add("Teste 01");
        minhaLista.add("Teste 02");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        minhaListaAdapter = new MinhaListaHome(this, minhaLista);
        recyclerView.setAdapter(minhaListaAdapter);

        novaLista = findViewById(R.id.btt_novo_minhas_listas);

        novaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MinhasListasActivity.this, CadastroListasActivity.class);
                startActivity(intent);
            }
        });
    }
}
