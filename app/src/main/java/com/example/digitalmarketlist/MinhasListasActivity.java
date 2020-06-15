package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MinhasListasActivity extends AppCompatActivity {
    private Button novaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);

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
