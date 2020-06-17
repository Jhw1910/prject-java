package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digitalmarketlist.adapter.MinhaLista;
import com.example.digitalmarketlist.objetos.Lista;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.DoublePredicate;

public class CadastroListasActivity extends AppCompatActivity {
    BancoDadosHelper bancoDadosHelper;
    private MinhaLista minhaListaAdapter;
    ArrayList<Lista> minhaLista = new ArrayList<>();
    private EditText nomeLista;
    private EditText nomeProduto;
    private EditText valorProduto;
    private TextView quantidadeProduto;
    private int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_listas);

        bancoDadosHelper = new BancoDadosHelper(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        minhaListaAdapter = new MinhaLista(this, minhaLista);
        recyclerView.setAdapter(minhaListaAdapter);
        nomeLista = findViewById(R.id.nome_lista_cadastro_listas);
        nomeProduto = findViewById(R.id.edittext_name);
        valorProduto = findViewById(R.id.valor_produto);
        quantidadeProduto = findViewById(R.id.textview_amount);
        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);
        Button adicionarLista = findViewById(R.id.btt_confirmar_cadastro_listas);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        adicionarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Long> ids = bancoDadosHelper.createLista(minhaLista);
                if(ids.size() > 0){
                    minhaLista.removeAll(minhaLista);
                    minhaListaAdapter.notifyDataSetChanged();
                    nomeLista.setText("");
                }
            }
        });
    }

    private void increase() {
        amount++;
        quantidadeProduto.setText(String.valueOf(amount));
    }
    private void decrease() {
        if (amount > 0) {
            amount--;
            quantidadeProduto.setText(String.valueOf(amount));
        }
    }

    private void addItem() {
        if (nomeProduto.getText().toString().trim().length() == 0 || amount == 0) {
            return;
        }
        String produto = nomeProduto.getText().toString();
        minhaLista.add(new Lista(nomeLista.getText().toString(), produto, Integer.parseInt(quantidadeProduto.getText().toString()), Double.parseDouble(valorProduto.getText().toString())));
        minhaListaAdapter.notifyDataSetChanged();

        nomeProduto.setText("");
        valorProduto.setText("");
        amount = 0;
    }

    private static class MoneyTextWatcher implements TextWatcher {
        private final WeakReference<EditText> editTextWeakReference;

        MoneyTextWatcher(EditText editText) {
            editTextWeakReference = new WeakReference<EditText>(editText);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            EditText editText = editTextWeakReference.get();
            if (editText == null) return;
            String s = editable.toString();
            if (s.isEmpty()) return;
            Locale myLocale = new Locale("pt", "BR");
            editText.removeTextChangedListener(this);
            String cleanString = s.replaceAll("[R$.,]", "");
            BigDecimal parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
            String formatted = NumberFormat.getCurrencyInstance(myLocale).format(parsed);
            editText.setText(formatted);
            editText.setSelection(formatted.length());
            editText.addTextChangedListener(this);
        }
    }
}
