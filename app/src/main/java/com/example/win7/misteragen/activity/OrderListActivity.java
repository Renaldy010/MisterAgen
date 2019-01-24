package com.example.win7.misteragen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.adapter.OrderListAdapter;
import com.example.win7.misteragen.model.Product;
import com.example.win7.misteragen.model.ProductOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity implements ValueEventListener {

    @BindView(R.id.rvOrderItem)
    RecyclerView rvOrderItem;
    OrderListAdapter orderListAdapter;
    List<ProductOrder> productList;
    List<String> productIdList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
//        setProductList();
        databaseReference = FirebaseDatabase.getInstance().getReference("product");
        rvOrderItem.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ProductOrder productOrder = new ProductOrder();
                    productOrder.setAlamatPembeli(data.child("alamatPembeli").getValue(String.class));
//                    productOrder.setImage(data.child().getImage());
                    productOrder.setJumlahOrderPembeli(data.child("jumlahOrderPembeli").getValue(String.class));
                    productOrder.setNamaBarang(data.child("namaBarang").getValue(String.class));
                    productOrder.setNamaPembeli(data.child("namaPembeli").getValue(String.class));
                    productOrder.setNoHpPembeli(data.child("noHpPembeli").getValue(String.class));
                    productList.add(productOrder);
                }
                orderListAdapter = new OrderListAdapter(OrderListActivity.this, productList);
                rvOrderItem.setAdapter(orderListAdapter);
                orderListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("OrderListActivity", "Failed to get data", databaseError.toException());
            }
        });



    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

//    public void setProductList() {
//        productList = new ArrayList<>();
//        productList.add(ProductOrder.createAquaGalon());
//        productList.add(ProductOrder.createAquaDus());
//        productList.add(ProductOrder.createGas3Kg());
//    }

}
