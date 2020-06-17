package com.example.digitalmarketlist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalmarketlist.R;
import com.example.digitalmarketlist.objetos.Lista;

import java.util.ArrayList;

public class MinhaLista extends RecyclerView.Adapter<MinhaLista.MinhaListaViewHolder> {
    private Context mContext;
    private ArrayList<Lista> mCursor;
    public MinhaLista(Context context, ArrayList<Lista> cursor) {
        mContext = context;
        mCursor = cursor;
    }
    static class MinhaListaViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView preco;
        TextView quantidade;
        @SuppressLint("CutPasteId")
        MinhaListaViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textview_name_item);
            preco = itemView.findViewById(R.id.textview_amount_item);
            quantidade = itemView.findViewById(R.id.textview_qnt_item);
        }
    }

    @NonNull
    @Override
    public MinhaListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_adicionar_lista, parent, false);
        return new MinhaListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaListaViewHolder holder, int position) {
        Lista lista = mCursor.get(position);
        holder.nome.setText(lista.getProduto());
        holder.preco.setText(String.valueOf(lista.getPreco()));
        holder.quantidade.setText(String.valueOf(lista.getQuantidade()));
    }
    @Override
    public int getItemCount() {
        return mCursor.size();
    }
}
