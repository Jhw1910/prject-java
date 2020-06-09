package com.example.digitalmarketlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button botaoConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoConfirmar = findViewById(R.id.btt_confirmar);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaInicial();
            }
        });
    }

    public void chamarTelaInicial() {
        Intent intent = new Intent(this, TelaInicialActivity.class);
        startActivity(intent);
    }
}
