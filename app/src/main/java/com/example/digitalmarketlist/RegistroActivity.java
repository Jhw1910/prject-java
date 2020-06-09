package com.example.digitalmarketlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digitalmarketlist.objetos.Usuario;

public class RegistroActivity extends AppCompatActivity {
    BancoDadosHelper bancoDadosHelper;
    private Button botaoVoltar;
    private Button botaoRegistro;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        botaoVoltar = findViewById(R.id.btt_voltar_registro);
        botaoRegistro = findViewById(R.id.btt_confirmar_registro);
        email = findViewById(R.id.email_registro);
        senha = findViewById(R.id.senha_registro);

        botaoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().matches("") || senha.getText().toString().matches("")){
                    chamarDialogoCamposVazio();
                    return;
                }
                if(!eEmailValido(email.getText().toString())){
                    chamarDialogoEmailInvalido();
                    return;
                }
                Usuario user1 = new Usuario(email.getText().toString(), senha.getText().toString());
                long tag1_id = bancoDadosHelper.createUsuario(user1);
                if(tag1_id > 0){
                    chamarDialogoOk();
                }
            }
        });
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean eEmailValido(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void chamarDialogoCamposVazio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
        builder.setTitle("Email/Senha");
        builder.setMessage("Informe um email/senha para continuar.");
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

    private void chamarDialogoEmailInvalido() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
        builder.setTitle("Email invalido");
        builder.setMessage("Informe um email válido: " + email.getText().toString());
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

    private void chamarDialogoOk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
        builder.setTitle("Usuário criado com sucesso!");
        builder.setMessage("Usuário com o email: " + email.getText().toString() + " foi criado com sucesso.");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
