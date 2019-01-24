package com.example.win7.misteragen.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.win7.misteragen.R;
import com.example.win7.misteragen.shared.AppPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etNoTelp)
    EditText etNoTelp;
    @BindView(R.id.etAlamat)
    EditText etAlamat;

    private FirebaseAuth mAuth;
    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        appPreference = new AppPreference(getApplicationContext());
    }

    public void validate() {
        if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etName.getText().toString().isEmpty() || etAlamat.getText().toString().isEmpty() || etNoTelp.getText().toString().isEmpty()) {
            Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
        } else if (etUsername.getText().toString().length() < 5) {
            Toast.makeText(this, "Nama minimal 5 karakter", Toast.LENGTH_SHORT).show();
        } else if (etPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
        } else {
            register();
        }
    }

    public void register() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(etUsername.getText().toString(), etPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (mAuth.getCurrentUser() != null) {
                            mAuth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(etUsername.getText().toString()).build())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            appPreference.setUserName(etName.getText().toString());
                                            appPreference.setUserAddress(etAlamat.getText().toString());
                                            appPreference.setUserPhonenumber(etNoTelp.getText().toString());
                                            appPreference.setUserEmail(etUsername.getText().toString());
                                            appPreference.setUserPassword(etPassword.getText().toString());
                                            Toast.makeText(RegisterActivity.this, "Register berhasil", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("name").setValue(etUsername.getText().toString(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Log.e("RegisterActivity", "onComplete: " + databaseError, databaseError.toException());
                                }
                                if (databaseReference != null) {
                                    Log.i("RegisterActivity", "onComplete: Success register to database");
                                }
                            }
                        });
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btnRegister_Register, R.id.btnBack_Register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister_Register:
                //validate();
                register();
                break;
            case R.id.btnBack_Register:
                finish();
                break;
        }
    }
}
