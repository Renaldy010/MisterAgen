package com.example.win7.misteragen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.shared.AppPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPenjualActivity extends AppCompatActivity {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_penjual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void validate() {
        if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
        } else if (!etEmail.getText().toString().equals("bagusputra0603@gmail.com") || !etPassword.getText().toString().equals("123123123")) {
            Toast.makeText(this, "Email salah", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginPenjualActivity.this, OrderListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btnLoginPenjual)
    public void onViewClicked() {
        Toast.makeText(this, "Email " + etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Pass " + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
        validate();
    }
}
