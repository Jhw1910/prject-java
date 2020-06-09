package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    BancoDadosHelper bancoDadosHelper;
    private Button botaoLogin;
    private Button botaoRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        botaoLogin = findViewById(R.id.btt_login_main);
        botaoRegistro = findViewById(R.id.btt_registrar_main);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarLogin();
            }
        });

        botaoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarRegistrar();
            }
        });
    }

    public void chamarLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void chamarRegistrar() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}
