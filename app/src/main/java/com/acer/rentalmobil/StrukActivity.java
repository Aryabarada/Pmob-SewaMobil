package com.acer.rentalmobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StrukActivity extends AppCompatActivity {
    TextView nama_penyewa, jenis_mobil, lama_sewa, total, uang_bayar, uang_kembali, waktu_transaksi;
    private DatabaseActivity dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);

        nama_penyewa = findViewById(R.id.nama_penyewa);
        jenis_mobil = findViewById(R.id.jenis_mobil);
        lama_sewa = findViewById(R.id.lama_sewa);
        total = findViewById(R.id.total);
        uang_bayar = findViewById(R.id.uang_bayar);
        uang_kembali = findViewById(R.id.uang_kembali);
        waktu_transaksi = findViewById(R.id.waktu_transaksi);

        dbHelper = new DatabaseActivity(this);

        // Tampilkan data struk
        tampilkanStruk();
    }

    private void tampilkanStruk() {
        Intent intent = getIntent();

        String nama = intent.getStringExtra("nama");
        String mobil = intent.getStringExtra("mobil");
        int lama = intent.getIntExtra("lama", 0);
        int ttl_hargasewa = intent.getIntExtra("total", 0);
        int uang = intent.getIntExtra("uang", 0);
        int kembalian = intent.getIntExtra("kembalian", 0);
        String waktu_transaksiValue  = intent.getStringExtra("waktu_transaksi");

        nama_penyewa.setText(nama);
        jenis_mobil.setText(mobil);
        lama_sewa.setText(String.valueOf(lama));
        total.setText(String.valueOf(ttl_hargasewa));
        uang_bayar.setText(String.valueOf(uang));
        uang_kembali.setText(String.valueOf(kembalian));
        waktu_transaksi.setText(waktu_transaksiValue);

        // Simpan data ke SQLite
        long id = dbHelper.tambahPemesanan(nama, mobil, lama, ttl_hargasewa, uang, kembalian, waktu_transaksiValue);
        if (id != -1) {
            Toast.makeText(this, "Data pemesanan berhasil disimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menyimpan data pemesanan", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnkembali(View view) {
        Intent intent =new Intent(StrukActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
