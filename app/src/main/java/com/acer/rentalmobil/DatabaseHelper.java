package com.acer.rentalmobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rental_mobil.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_PEMESANAN = "pemesanan";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_MOBIL = "mobil";
    public static final String COLUMN_LAMA = "lama";
    public static final String COLUMN_TOTAL = "total";
    public static final String COLUMN_UANG = "uang";
    public static final String COLUMN_KEMBALIAN = "kembalian";
    public static final String COLUMN_WAKTU = "waktu_transaksi";

    private static final String CREATE_TABLE_PEMESANAN =
            "CREATE TABLE " + TABLE_PEMESANAN + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_MOBIL + " TEXT, " +
                    COLUMN_LAMA + " INTEGER, " +
                    COLUMN_TOTAL + " INTEGER, " +
                    COLUMN_UANG + " INTEGER, " +
                    COLUMN_KEMBALIAN + " INTEGER, " +
                    COLUMN_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PEMESANAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEMESANAN);
        onCreate(db);
    }

    // Metode untuk menambahkan data pemesanan ke database
    public long tambahPemesanan(String nama, String mobil, int lama, int total, int uang, int kembalian, String waktu_transaksi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_MOBIL, mobil);
        values.put(COLUMN_LAMA, lama);
        values.put(COLUMN_TOTAL, total);
        values.put(COLUMN_UANG, uang);
        values.put(COLUMN_KEMBALIAN, kembalian);
        values.put(COLUMN_WAKTU, waktu_transaksi);  // Menyimpan waktu transaksi
        long id = db.insert(TABLE_PEMESANAN, null, values);
        return id;
    }

    // Metode untuk mengambil semua data pemesanan dari database
    public Cursor ambilSemuaPemesanan() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PEMESANAN, null, null, null, null, null, null);
    }

    // Metode untuk menghapus data pemesanan dari database berdasarkan ID
    public void hapusPemesanan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PEMESANAN, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
