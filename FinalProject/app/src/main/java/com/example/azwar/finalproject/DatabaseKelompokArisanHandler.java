package com.example.azwar.finalproject;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.notification.StatusBarNotification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Azwar on 4/20/2017.
 */

public class DatabaseKelompokArisanHandler extends SQLiteOpenHelper {
    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "arisan_barang";

    // database tables
    public static final String TABLE_KELOMPOK_ARISAN = "kelompok_arisan";

    // kelompok arisan columns name
    public static final String KELOMPOK_ARISAN_ID = "id";
    public static final String KELOMPOK_ARISAN_NAMA = "nama";
    public static final String KELOMPOK_ARISAN_TIPE = "tipe";
    public static final String KELOMPOK_ARISAN_TANGGAL_MULAI = "tanggal_mulai";
    public static final String KELOMPOK_ARISAN_SETORAN = "setoran";
    public static final String KELOMPOK_ARISAN_STATUS = "status";
    public static final String KELOMPOK_ARISAN_BONUS = "bonus";

    public DatabaseKelompokArisanHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KELOMPOK_ARISAN_TABLE = "CREATE TABLE " + TABLE_KELOMPOK_ARISAN + "("
                + KELOMPOK_ARISAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + KELOMPOK_ARISAN_NAMA + " TEXT, "
                + KELOMPOK_ARISAN_TIPE + " TEXT, "
                + KELOMPOK_ARISAN_TANGGAL_MULAI + " DATE, "
                + KELOMPOK_ARISAN_SETORAN + " INT, "
                + KELOMPOK_ARISAN_STATUS + " TEXT, "
                + KELOMPOK_ARISAN_BONUS + " INT, "
                + ")";
        db.execSQL(CREATE_KELOMPOK_ARISAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KELOMPOK_ARISAN);

        // Create tables again
        onCreate(db);
    }

    // Adding new kelompok arisan
    public void addKelompokArisan(KelompokArisan kelompokArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String date = sdf.format(kelompokArisan.getTanggalMulai());

        ContentValues values = new ContentValues();
        values.put(KELOMPOK_ARISAN_NAMA, kelompokArisan.getNama());
        values.put(KELOMPOK_ARISAN_TIPE, kelompokArisan.getTipe());
        values.put(KELOMPOK_ARISAN_TANGGAL_MULAI, date);
        values.put(KELOMPOK_ARISAN_SETORAN, kelompokArisan.getSetoran());
        values.put(KELOMPOK_ARISAN_STATUS, kelompokArisan.getStatus());
        values.put(KELOMPOK_ARISAN_BONUS, kelompokArisan.getBonus());

        db.insert(TABLE_KELOMPOK_ARISAN, null, values);
        db.close();
    }

    public KelompokArisan getKelompokArisan(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_KELOMPOK_ARISAN,
                new String[]{
                        KELOMPOK_ARISAN_ID,
                        KELOMPOK_ARISAN_NAMA,
                        KELOMPOK_ARISAN_TIPE,
                        KELOMPOK_ARISAN_TANGGAL_MULAI,
                        KELOMPOK_ARISAN_SETORAN,
                        KELOMPOK_ARISAN_STATUS,
                        KELOMPOK_ARISAN_BONUS},
                KELOMPOK_ARISAN_ID + " = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        KelompokArisan kelompokArisan = null;

        if (kelompokArisan != null){
            cursor.moveToFirst();
            kelompokArisan = new KelompokArisan(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    sdf.parse(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5),
                    Integer.parseInt(cursor.getString(6))
            );
        }
        cursor.close();

        return kelompokArisan;
    }

    public List<KelompokArisan> getAllKelompokArisan() throws ParseException {
        List<KelompokArisan> kelompokArisanList = new ArrayList<KelompokArisan>();

        // select all query
        String selectQuery = "SELECT * FROM + " + TABLE_KELOMPOK_ARISAN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        if(cursor.moveToFirst()){
            do{
                KelompokArisan kelompokArisan = new KelompokArisan();
                kelompokArisan.setNama(cursor.getString(1));
                kelompokArisan.setTipe(cursor.getString(2));
                kelompokArisan.setTanggalMulai(sdf.parse(cursor.getString(3)));
                kelompokArisan.setSetoran(Integer.parseInt(cursor.getString(4)));
                kelompokArisan.setStatus(cursor.getString(5));
                kelompokArisan.setBonus(Integer.parseInt(cursor.getString(6)));

                kelompokArisanList.add(kelompokArisan);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return kelompokArisanList;
    }

    public int getKelompokArisanCount(){
        String countQuery = "SELECT * FROM " + TABLE_KELOMPOK_ARISAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateKelompokArisan(KelompokArisan kelompokArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String date = sdf.format(kelompokArisan.getTanggalMulai());

        ContentValues values = new ContentValues();
        values.put(KELOMPOK_ARISAN_NAMA, kelompokArisan.getNama());
        values.put(KELOMPOK_ARISAN_TIPE, kelompokArisan.getTipe());
        values.put(KELOMPOK_ARISAN_TANGGAL_MULAI, date);
        values.put(KELOMPOK_ARISAN_SETORAN, kelompokArisan.getSetoran());
        values.put(KELOMPOK_ARISAN_STATUS, kelompokArisan.getStatus());
        values.put(KELOMPOK_ARISAN_BONUS, kelompokArisan.getBonus());

        return db.update(
                TABLE_KELOMPOK_ARISAN,
                values,
                KELOMPOK_ARISAN_ID + " ?",
                new String[] {String.valueOf(kelompokArisan.getId())}
        );
    }

    public void deleteKelompokArisan(KelompokArisan kelompokArisan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_KELOMPOK_ARISAN,
                KELOMPOK_ARISAN_ID + " = ?",
                new String[] {String.valueOf(kelompokArisan.getId())}
        );
        db.close();
    }
}
