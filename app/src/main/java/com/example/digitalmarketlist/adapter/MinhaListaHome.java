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

import java.util.ArrayList;

public class MinhaListaHome extends RecyclerView.Adapter<MinhaListaHome.MinhaListaHomeViewHolder> {
    private Context mContext;
    private ArrayList<String> mCursor;
    public MinhaListaHome(Context context, ArrayList<String> cursor) {
        mContext = context;
        mCursor = cursor;
    }
    static class MinhaListaHomeViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        @SuppressLint("CutPasteId")
        MinhaListaHomeViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome_lista);
        }
    }

    @NonNull
    @Override
    public MinhaListaHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_minha_lista, parent, false);
        return new MinhaListaHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaListaHomeViewHolder holder, int position) {
        String lista = mCursor.get(position);
        holder.nome.setText(lista);
    }
    @Override
    public int getItemCount() {
        return mCursor.size();
    }
}
