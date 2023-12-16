package com.acer.rentalmobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarMobilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil);
    }

    public void btnsewa(View view) {
        Intent intent =new Intent(DaftarMobilActivity.this, SewaMobilActivity.class);
        startActivity(intent);
    }

    public void btnkembali(View view) {
        Intent intent =new Intent(DaftarMobilActivity.this, MainActivity.class);
        startActivity(intent);
    }
}