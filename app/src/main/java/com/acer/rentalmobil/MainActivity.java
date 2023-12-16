package com.acer.rentalmobil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    TextView linkTextView;
    private TextView nama_penyewa,jenis_mobil,lama_sewa,total,uang_bayar,uang_kembali;
    private final DatabaseActivity SQLite = new DatabaseActivity(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkTextView = findViewById(R.id.activity_main_hyperlink);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        nama_penyewa = findViewById(R.id.nama_penyewa);
        jenis_mobil = findViewById(R.id.jenis_mobil);
        lama_sewa = findViewById(R.id.lama_sewa);
        total = findViewById(R.id.total);
        uang_bayar = findViewById(R.id.uang_bayar);
        uang_kembali = findViewById(R.id.uang_kembali);

    }

    public void DaftarMobilBtn(View view) {
        Intent intent =new Intent(MainActivity.this, DaftarMobilActivity.class);
        startActivity(intent);
    }
    public void SewaBtn(View view) {
        Intent intent =new Intent(MainActivity.this, SewaMobilActivity.class);
        startActivity(intent);
    }
    public void RiwayatPemesananBtn(View view) {
        Intent intent = new Intent(MainActivity.this, DataPemesananActivity.class);
        startActivity(intent);
    }
    public void ContactBtn(View view) {
        Intent intent =new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }
}