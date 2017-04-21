package com.example.azwar.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azwar on 4/21/2017.
 */

public class DatabaseAnggotaArisanHandler extends SQLiteOpenHelper {
    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "arisan_barang";

    // database tables
    public static final String TABLE_ANGGOTA_ARISAN = "anggota_arisan";

    // kelompok arisan columns name
    public static final String ANGGOTA_ARISAN_ID = "id";
    public static final String ANGGOTA_ARISAN_NAMA = "nama";
    public static final String ANGGOTA_ARISAN_NO_HP = "no_hp";
    public static final String ANGGOTA_ARISAN_ALAMAT = "alamat";
    public static final String ANGGOTA_ARISAN_KELOMPOK_ARISAN_ID = "kelompok_arisan_id";

    public DatabaseAnggotaArisanHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ANGGOTA_ARISAN_TABLE = "CREATE TABLE " + TABLE_ANGGOTA_ARISAN + "("
                + ANGGOTA_ARISAN_ID + " INTEGER PRIMARY KEY NOT NULL AUTO INCREMENT, "
                + ANGGOTA_ARISAN_NAMA + " TEXT, "
                + ANGGOTA_ARISAN_NO_HP + " INT, "
                + ANGGOTA_ARISAN_ALAMAT + " TEXT, "
                + ANGGOTA_ARISAN_KELOMPOK_ARISAN_ID + " INT, "
                + ")";
        db.execSQL(CREATE_ANGGOTA_ARISAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANGGOTA_ARISAN);

        // Create tables again
        onCreate(db);
    }

    // Adding new kelompok arisan
    public void addAnggotaArisan(AnggotaArisan anggotaArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANGGOTA_ARISAN_NAMA, anggotaArisan.getNama());
        values.put(ANGGOTA_ARISAN_NO_HP, anggotaArisan.getNoHp());
        values.put(ANGGOTA_ARISAN_ALAMAT, anggotaArisan.getAlamat());
        values.put(ANGGOTA_ARISAN_KELOMPOK_ARISAN_ID, anggotaArisan.getKelompokArisanId());

        db.insert(TABLE_ANGGOTA_ARISAN, null, values);
        db.close();
    }

    public AnggotaArisan getAnggotaArisan(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_ANGGOTA_ARISAN,
                new String[]{
                        ANGGOTA_ARISAN_ID,
                        ANGGOTA_ARISAN_NAMA,
                        ANGGOTA_ARISAN_NO_HP,
                        ANGGOTA_ARISAN_ALAMAT,
                        ANGGOTA_ARISAN_KELOMPOK_ARISAN_ID},
                ANGGOTA_ARISAN_ID + " = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        AnggotaArisan anggotaArisan = null;

        if (anggotaArisan != null){
            cursor.moveToFirst();
            anggotaArisan = new AnggotaArisan(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4))
            );
        }
        cursor.close();

        return anggotaArisan;
    }

    public List<AnggotaArisan> getAllAnggotaArisan() throws ParseException {
        List<AnggotaArisan> anggotaArisanList = new ArrayList<AnggotaArisan>();

        // select all query
        String selectQuery = "SELECT * FROM + " + TABLE_ANGGOTA_ARISAN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        if(cursor.moveToFirst()){
            do{
                AnggotaArisan anggotaArisan = new AnggotaArisan();
                anggotaArisan.setNama(cursor.getString(1));
                anggotaArisan.setNoHp(Integer.parseInt(cursor.getString(2)));
                anggotaArisan.setAlamat(cursor.getString(3));
                anggotaArisan.setKelompokArisanId(Integer.parseInt(cursor.getString(4)));

                anggotaArisanList.add(anggotaArisan);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return anggotaArisanList;
    }

    public int getAnggotaArisanCount(){
        String countQuery = "SELECT * FROM " + TABLE_ANGGOTA_ARISAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateAnggotaArisan(AnggotaArisan anggotaArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANGGOTA_ARISAN_NAMA, anggotaArisan.getNama());
        values.put(ANGGOTA_ARISAN_NO_HP, anggotaArisan.getNoHp());
        values.put(ANGGOTA_ARISAN_ALAMAT, anggotaArisan.getAlamat());
        values.put(ANGGOTA_ARISAN_KELOMPOK_ARISAN_ID, anggotaArisan.getKelompokArisanId());

        return db.update(
                TABLE_ANGGOTA_ARISAN,
                values,
                ANGGOTA_ARISAN_ID + " ?",
                new String[] {String.valueOf(anggotaArisan.getId())}
        );
    }

    public void deleteAnggotaArisan(AnggotaArisan anggotaArisan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_ANGGOTA_ARISAN,
                ANGGOTA_ARISAN_ID + " = ?",
                new String[] {String.valueOf(anggotaArisan.getId())}
        );
        db.close();
    }
}
