package com.example.digitalmarketlist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalmarketlist.R;
import com.example.digitalmarketlist.objetos.Produtos;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
    private Context mContext;
    private ArrayList<Produtos> mCursor;
    public GroceryAdapter(Context context, ArrayList<Produtos> cursor) {
        mContext = context;
        mCursor = cursor;
    }
    static class GroceryViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView countText;
        @SuppressLint("CutPasteId")
        GroceryViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_adicionar_lista, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        Produtos produto = mCursor.get(position);
        holder.nameText.setText(produto.getNome());
        holder.countText.setText(String.valueOf(produto.getValor()));
    }
    @Override
    public int getItemCount() {
        return mCursor.size();
    }
}
