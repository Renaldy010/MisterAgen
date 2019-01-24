package com.example.win7.misteragen.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.model.Product;
import com.example.win7.misteragen.model.ProductOrder;
import com.example.win7.misteragen.shared.AppPreference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.etJumlahOrder)
    EditText etJumlahOrder;
    @BindView(R.id.etAlamat)
    EditText etAlamat;

    String currId = "";
    String currStock = "";
    String currName = "";
    int img = -1;

    AppPreference appPreference;
    DatabaseReference productDatabaseRef;
    DatabaseReference userDatabaseRef;
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appPreference = new AppPreference(getApplicationContext());
        productDatabaseRef = FirebaseDatabase.getInstance().getReference("product");
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("user");
        storage = FirebaseStorage.getInstance().getReference();

        currId = getIntent().getStringExtra("imageId");
        currStock = getIntent().getStringExtra("imageStock");
        currName = getIntent().getStringExtra("imageName");
        img = getIntent().getIntExtra("image", -1);
        setup(currId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadImage(){
//        Uri file = Uri.fromFile()
//        StorageReference storageReference = storage.child("image/" + )
    }

    public void setup(String id) {
        switch (id) {
            case "1":
                ivImage.setImageResource(R.mipmap.ic_gas_3_kg);
                break;
            case "2":
                ivImage.setImageResource(R.mipmap.ic_gas_12_kg);
                break;
            case "3":
                ivImage.setImageResource(R.mipmap.ic_aqua_galon);
                break;
            case "4":
                ivImage.setImageResource(R.mipmap.ic_aqua_dus);
                break;
        }
    }

    public void addOrder() {
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setId(uid);
        productOrder.setAlamatPembeli(etAlamat.getText().toString());
        productOrder.setJumlahOrderPembeli(etJumlahOrder.getText().toString());
        productOrder.setNamaPembeli(appPreference.getUserName());
        productOrder.setNoHpPembeli(appPreference.getUserPhonenumber());
        productOrder.setNamaBarang(currName);
        productOrder.setImage(img);

        final String prodId = productDatabaseRef.push().getKey();

        productDatabaseRef.child(prodId).setValue(productOrder).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                userDatabaseRef.child(uid).child("product").child(prodId).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(OrderActivity.this, "Data posted on firebase", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: Fail to post user product", e);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: Fail to post user product", e);
            }
        });
    }

    @OnClick(R.id.btnOrder)
    public void onViewClicked() {
        if (etJumlahOrder.getText().toString().isEmpty() || etAlamat.getText().toString().isEmpty()) {
            Toast.makeText(this, "Data harus diisi semua", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(etJumlahOrder.getText().toString()) > Integer.parseInt(currStock)) {
            Toast.makeText(this, "Jumlah order tidak boleh melebihi stock", Toast.LENGTH_SHORT).show();
        } else if (!etAlamat.getText().toString().startsWith("Jl.")) {
            Toast.makeText(this, "Alamat harus dimulai dengan Jl.", Toast.LENGTH_SHORT).show();
        } else {
            addOrder();
            Toast.makeText(this, "Pesanan berhasil dilakukan", Toast.LENGTH_SHORT).show();
        }
    }
}