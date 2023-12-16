package com.acer.rentalmobil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class DataPemesananActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesanan);

        dbHelper = new DatabaseHelper(this);

        // Tampilkan data dari SQLite
        tampilkanData();
    }

    private void tampilkanData() {
        LinearLayout linearDataPemesanan = findViewById(R.id.linearDataPemesanan);
        linearDataPemesanan.removeAllViews(); // Bersihkan tampilan sebelum menambahkan data baru

        try {
            // Ambil semua data pemesanan dari SQLite
            Cursor cursor = dbHelper.ambilSemuaPemesanan();

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                    String nama = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA));
                    String mobil = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOBIL));
                    int lama = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAMA));
                    int total = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_TOTAL));
                    int uang = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_UANG));
                    int kembalian = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_KEMBALIAN));
                    String waktuTransaksi = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WAKTU));

                    // Tambahkan layout untuk setiap pemesanan
                    View pemesananLayout = View.inflate(this, R.layout.activity_item_pemesanan, null);

                    // Set teks untuk waktu transaksi
                    TextView subHeader = pemesananLayout.findViewById(R.id.subHeader);
                    subHeader.setText("Waktu Transaksi: " + waktuTransaksi);

                    // Set teks untuk data pemesanan
                    TextView textViewData = pemesananLayout.findViewById(R.id.textViewData);
                    textViewData.setText(
                            "Nama           : " + nama + "\n" +
                                    "Mobil          : " + mobil + "\n" +
                                    "Lama Sewa      : " + lama + " hari\n" +
                                    "Total Bayar    : " + total + "\n" +
                                    "Uang Bayar     : " + uang + "\n" +
                                    "Uang Kembalian : " + kembalian
                    );

                    // Tambahkan layout pemesanan ke tampilan
                    linearDataPemesanan.addView(pemesananLayout);

                    // Buat tombol hapus untuk setiap data
                    Button btnHapus = pemesananLayout.findViewById(R.id.btnHapus);
                    btnHapus.setTag(id); // Simpan ID untuk referensi saat menghapus
                    btnHapus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Panggil metode untuk menghapus data dari database berdasarkan ID
                            hapusData((int) v.getTag());
                            // Refresh tampilan setelah penghapusan
                            tampilkanData();
                        }
                    });
                }

                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menghapus data dari database berdasarkan ID
    private void hapusData(int id) {
        dbHelper.hapusPemesanan(id);
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
    public void btnkembali(View view) {
        Intent intent =new Intent(DataPemesananActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
