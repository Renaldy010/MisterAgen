package com.example.win7.misteragen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.adapter.HomeAdapter;
import com.example.win7.misteragen.model.Product;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.ItemListener {

    @BindView(R.id.rvList)
    RecyclerView rvList;
    HomeAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setProductList();
        rvList.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new HomeAdapter(HomeActivity.this, productList, this);
        rvList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.loginPenjual:
                Intent intent = new Intent(HomeActivity.this, LoginPenjualActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setProductList() {
        productList = new ArrayList<>();
        productList.add(Product.createGas3Kg());
        productList.add(Product.createGas12Kg());
        productList.add(Product.createAquaDus());
        productList.add(Product.createAquaGalon());
    }

    @Override
    public void onClick(String productId, String productStock, String name, int image) {
        Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
        intent.putExtra("imageId", productId);
        intent.putExtra("imageStock", productStock);
        intent.putExtra("imageName", name);
        intent.putExtra("image", image);
        startActivity(intent);
    }
}
