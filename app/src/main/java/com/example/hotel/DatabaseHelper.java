package com.example.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "otel_rezervasyon_okdb01";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_ODA = "CREATE TABLE oda (oda_id INTEGER PRIMARY KEY AUTOINCREMENT, oda_basligi TEXT)";
    private static final String CREATE_TABLE_REZERVASYON = "CREATE TABLE rezervasyon (rezervasyon_id INTEGER PRIMARY KEY AUTOINCREMENT, oda_basligi TEXT, musteri_adi TEXT, baslangic_tarihi TEXT, kac_gun INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ODA);
        db.execSQL(CREATE_TABLE_REZERVASYON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS oda");
        db.execSQL("DROP TABLE IF EXISTS rezervasyon");
        onCreate(db);
    }

    // Oda başlıklarını getir
    public List<String> getAllRooms() {
        List<String> roomList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT oda_basligi FROM oda", null);

        if (cursor.moveToFirst()) {
            do {
                String roomTitle = cursor.getString(cursor.getColumnIndex("oda_basligi"));
                roomList.add(roomTitle);
                System.out.println("Oda Başlığı: " + roomTitle);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return roomList;
    }

    // Rezervasyon ekle
    public long addReservation(String roomTitle, String customerName, String startDate, int duration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("oda_basligi", roomTitle);
        values.put("musteri_adi", customerName);
        values.put("baslangic_tarihi", startDate);
        values.put("kac_gun", duration);
        long reservationId = db.insert("rezervasyon", null, values);
        db.close();
        return reservationId;
    }

    // Oda Ekleme Metodu
    // DatabaseHelper sınıfındaki addRoom metodu
    public long addRoom(String roomTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("oda_basligi", roomTitle);
        long result = db.insert("oda", null, values);
        db.close();
        return result;
    }

    public List<Rezervasyon> getAllReservations() {
        List<Rezervasyon> reservationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rezervasyon", null);

        if (cursor.moveToFirst()) {
            do {
                String roomTitle = cursor.getString(cursor.getColumnIndex("oda_basligi"));
                String customerName = cursor.getString(cursor.getColumnIndex("musteri_adi"));
                String startDate = cursor.getString(cursor.getColumnIndex("baslangic_tarihi"));
                int duration = cursor.getInt(cursor.getColumnIndex("kac_gun"));

                Rezervasyon reservation = new Rezervasyon(roomTitle, customerName, startDate, duration);
                reservationList.add(reservation);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return reservationList;
    }

    public void removeRoom(String roomTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("oda", "oda_basligi = ?", new String[]{roomTitle});
        db.close();
    }



}
