package com.example.digitalmarketlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digitalmarketlist.objetos.Usuario;

public class LoginActivity extends AppCompatActivity {
    BancoDadosHelper bancoDadosHelper;
    private Button botaoConfirmar;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        botaoConfirmar = findViewById(R.id.btt_confirmar);
        email = findViewById(R.id.inserir_email_login);
        senha = findViewById(R.id.inserir_senha_login);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = bancoDadosHelper.buscarUsuario(email.getText().toString(), senha.getText().toString());
                if(usuario == null){
                    chamarDialogoUsuarioNaoEncontrado();
                    return;
                }
                chamarTelaInicial();
            }
        });
    }

    private void chamarDialogoUsuarioNaoEncontrado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Usuário não encontrado.");
        builder.setMessage("Ops! Nenhum usuário encontrado.");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void chamarTelaInicial() {
        Intent intent = new Intent(this, TelaInicialActivity.class);
        startActivity(intent);
    }
}
