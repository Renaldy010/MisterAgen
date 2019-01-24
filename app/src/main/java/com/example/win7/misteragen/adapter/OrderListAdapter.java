package com.example.win7.misteragen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.model.ProductOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    public Context ctx;
    public List<ProductOrder> productList;

    public OrderListAdapter(Context ctx, List<ProductOrder> productList) {
        this.ctx = ctx;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout_penjual, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItem)
        ImageView ivItem;
        @BindView(R.id.txtNama)
        TextView txtNama;
        @BindView(R.id.txtJumlahOrder)
        TextView txtJumlahOrder;
        @BindView(R.id.txtNoHandphone)
        TextView txtNoHandphone;
        @BindView(R.id.txtAtasNama)
        TextView txtAtasNama;
        @BindView(R.id.txtAlamatPembeli)
        TextView txtAlamatPembeli;

        ProductOrder item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ProductOrder item) {
            this.item = item;
            txtNama.setText(item.getNamaBarang());
            txtAtasNama.setText(item.getNamaPembeli());
            txtAlamatPembeli.setText(item.getAlamatPembeli());
            txtJumlahOrder.setText(item.getJumlahOrderPembeli());
            txtNoHandphone.setText(item.getNoHpPembeli());
//            ivItem.setImageResource(item.getImage());
        }

    }


}
