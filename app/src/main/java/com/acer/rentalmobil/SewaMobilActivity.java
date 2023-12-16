package com.acer.rentalmobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acer.rentalmobil.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SewaMobilActivity extends AppCompatActivity {
    boolean isTmblOKClicked = false;
    int harga_sewa_mobil,jml_lmsw,ttl_hargasewa,jml_uang;
    String s_nama;
    Spinner ad_listmobil;
    TextView harga_mobil;
    EditText lama_sewa,uangbayar,nama_penyewa;
    String list_mobil[] = {"Honda Jazz","Honda Brio","Toyota Avanza","Toyota Fortuner"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_mobil);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        ad_listmobil = findViewById(R.id.ad_listmobil);
        harga_mobil = findViewById(R.id.harga_mobil);
        lama_sewa = findViewById(R.id.lama_sewa);
        uangbayar = findViewById(R.id.uangbayar);

        ArrayAdapter ad_mbl = new ArrayAdapter(SewaMobilActivity.this,R.layout.support_simple_spinner_dropdown_item,list_mobil);
        ad_listmobil.setAdapter(ad_mbl);
    }

    public void tmbl_OK (View view) {
        jml_lmsw= Integer.parseInt(lama_sewa.getText().toString());
        s_nama = nama_penyewa.getText().toString();


        if(ad_listmobil.getSelectedItem().toString()=="Honda Jazz"){
            harga_sewa_mobil = 300000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listmobil.getSelectedItem().toString() == "Honda Brio"){
            harga_sewa_mobil = 350000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listmobil.getSelectedItem().toString() == "Toyota Avanza"){
            harga_sewa_mobil = 600000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listmobil.getSelectedItem().toString() == "Toyota Fortuner"){
            harga_sewa_mobil = 700000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        }

        isTmblOKClicked = true;
    }

    public void tombol_sewa2(View view) {
        if (isTmblOKClicked) {
            jml_uang = Integer.parseInt(uangbayar.getText().toString());
            if (jml_uang < ttl_hargasewa) {
                Toast.makeText(this, "UANG ANDA KURANG", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SewaMobilActivity.this, StrukActivity.class);

                intent.putExtra("nama", s_nama);
                intent.putExtra("mobil", ad_listmobil.getSelectedItem().toString());
                intent.putExtra("lama", jml_lmsw);
                intent.putExtra("total", ttl_hargasewa);
                intent.putExtra("uang", jml_uang);
                intent.putExtra("kembalian", jml_uang - ttl_hargasewa);
                intent.putExtra("waktu_transaksi", getCurrentDateTime());

                startActivity(intent);
            }
        }
        else{
            Toast.makeText(this, "Klik Tombol 'LIHAT HARGA' Terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnkembali(View view) {
        Intent intent =new Intent(SewaMobilActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}