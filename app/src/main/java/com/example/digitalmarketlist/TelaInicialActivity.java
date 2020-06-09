package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaInicialActivity extends AppCompatActivity {
    private Button sair;
    private Button carrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        sair = findViewById(R.id.btt_sair_tela_inicial);
        carrinho = findViewById(R.id.btt_carrinho_tela_inicial);

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialActivity.this, CarrinhoFinalActivity.class);
                startActivity(intent);
            }
        });
    }
}
