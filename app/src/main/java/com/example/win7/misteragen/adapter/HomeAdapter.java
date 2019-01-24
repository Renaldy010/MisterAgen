package com.example.win7.misteragen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.activity.OrderActivity;
import com.example.win7.misteragen.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public Context ctx;
    public List<Product> productList;
    public ItemListener itemListener;

    public HomeAdapter(Context ctx, List<Product> productList, ItemListener itemListener) {
        this.ctx = ctx;
        this.productList = productList;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface ItemListener {
        void onClick(String productId, String stock, String name, int image);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.txtJumlahHarga)
        TextView txtJumlahHarga;
        @BindView(R.id.txtJumlahStok)
        TextView txtJumlahStok;
        @BindView(R.id.txtNama)
        TextView txtNama;
        Product item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ivItem)
        public void onViewClicked() {
            itemListener.onClick(item.getId(), item.getStok(), item.getNama(), item.getImage());
        }

        public void bind(Product item){
            this.item = item;
            txtNama.setText(item.getNama());
            txtJumlahHarga.setText(item.getHarga());
            txtJumlahStok.setText(item.getStok());
            ivItem.setImageResource(item.getImage());
        }

    }



}
