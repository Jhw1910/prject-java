package com.example.digitalmarketlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digitalmarketlist.adapter.GroceryAdapter;
import com.example.digitalmarketlist.objetos.GroceryContract;
import com.example.digitalmarketlist.objetos.Produtos;

import java.util.ArrayList;
import java.util.List;

public class CadastroListasActivity extends AppCompatActivity {
    private GroceryAdapter mAdapter;
    ArrayList<Produtos> produtos = new ArrayList<>();
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_listas);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroceryAdapter(this, produtos);
        recyclerView.setAdapter(mAdapter);
        mEditTextName = findViewById(R.id.edittext_name);
        mTextViewAmount = findViewById(R.id.textview_amount);
        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);

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
    }

    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }
    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    private void addItem() {
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        produtos.add(new Produtos(1, name, "3"));
        mAdapter.notifyDataSetChanged();
    }
}
