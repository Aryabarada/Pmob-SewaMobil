package com.acer.rentalmobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseActivity(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long tambahPemesanan(String nama, String mobil, int lama, int total, int uang, int kembalian, String waktu_transaksi) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_MOBIL, mobil);
        values.put(DatabaseHelper.COLUMN_LAMA, lama);
        values.put(DatabaseHelper.COLUMN_TOTAL, total);
        values.put(DatabaseHelper.COLUMN_UANG, uang);
        values.put(DatabaseHelper.COLUMN_KEMBALIAN, kembalian);
        values.put(DatabaseHelper.COLUMN_WAKTU, waktu_transaksi);

        try {
            open();
            long id = database.insert(DatabaseHelper.TABLE_PEMESANAN, null, values);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;  // Return -1 jika terjadi kesalahan
        } finally {
            close();
        }
    }

    public Cursor ambilSemuaPemesanan() {
        open();
        return database.query(DatabaseHelper.TABLE_PEMESANAN, null, null, null, null, null, null);
    }
}
