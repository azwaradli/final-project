package com.example.azwar.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Azwar on 4/20/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "arisan_barang";

    // Table Names
    private static final String TABLE_KELOMPOK_ARISAN = "kelompok_arisan";
    private static final String TABLE_ANGGOTA_ARISAN = "anggota_arisan";
    private static final String TABLE_BARANG = "barang";
    private static final String TABLE_KERANJANG = "keranjang";
    private static final String TABLE_PERTANYAAN = "pertanyaan";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";

    // KELOMPOK ARISAN Table - columns names
    private static final String KEY_TIPE = "tipe";
    private static final String KEY_TANGGAL_MULAI = "tanggal_mulai";
    private static final String KEY_SETORAN = "setoran";
    private static final String KEY_STATUS = "status";
    private static final String KEY_BONUS = "bonus";

    // ANGGOTA ARISAN Table - column names
    private static final String KEY_NO_HP = "no_hp";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_ID_KELOMPOK_ARISAN = "kelompok_arisan_id";

    // BARANG Table - column names
    private static final String KEY_HARGA = "harga";
    private static final String KEY_KODE_PRODUK = "kode_produk";
    private static final String KEY_STOK = "stok";
    private static final String KEY_DESKRIPSI = "deskripsi";
    private static final String KEY_SPESIFIKASI = "spesifikasi";

    // KERANJANG Table - column names
    private static final String KEY_ID_BUTTON = "id_button";
    private static final String KEY_ID_BARANG = "id_barang";

    // PERTANYAAN Table - column names
    private static final String KEY_PERTANYAAN = "pertanyaan";

    private static final String CREATE_KELOMPOK_ARISAN_TABLE = "CREATE TABLE " + TABLE_KELOMPOK_ARISAN + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_NAMA + " TEXT, "
            + KEY_TIPE + " TEXT, "
            + KEY_TANGGAL_MULAI + " DATE, "
            + KEY_SETORAN + " INTEGER, "
            + KEY_STATUS + " TEXT, "
            + KEY_BONUS + " INT"
            + ")";

    private static final String CREATE_ANGGOTA_ARISAN_TABLE = "CREATE TABLE " + TABLE_ANGGOTA_ARISAN + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_NAMA + " TEXT, "
            + KEY_NO_HP + " INTEGER, "
            + KEY_ALAMAT + " TEXT, "
            + KEY_ID_KELOMPOK_ARISAN + " INT, "
            + "FOREIGN KEY (" + KEY_ID_KELOMPOK_ARISAN + ") REFERENCES " + TABLE_KELOMPOK_ARISAN + "(" + KEY_ID + ")"
            + ")";

    private static final String CREATE_BARANG_TABLE = "CREATE TABLE " + TABLE_BARANG + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_NAMA + " TEXT, "
            + KEY_HARGA + " INTEGER, "
            + KEY_KODE_PRODUK + " TEXT, "
            + KEY_STOK + " INTEGER, "
            + KEY_DESKRIPSI + " TEXT, "
            + KEY_SPESIFIKASI + " TEXT"
            + ")";

    private static final String CREATE_KERANJANG_TABLE = "CREATE TABLE " + TABLE_KERANJANG + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_ID_BUTTON + " INTEGER, "
            + KEY_ID_BARANG + " INTEGER, "
            + "FOREIGN KEY (" + KEY_ID_BARANG + ") REFERENCES " + TABLE_BARANG + "(" + KEY_ID + ")"
            + ")";

    private static final String CREATE_PERTANYAAN_TABLE = "CREATE TABLE " + TABLE_PERTANYAAN + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_ID_BARANG + " INTEGER, "
            + KEY_PERTANYAAN + " TEXT, "
            + "FOREIGN KEY (" + KEY_ID_BARANG + ") REFERENCES " + TABLE_BARANG + "(" + KEY_ID + ")"
            + ")";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_KELOMPOK_ARISAN_TABLE);
        db.execSQL(CREATE_ANGGOTA_ARISAN_TABLE);
        db.execSQL(CREATE_BARANG_TABLE);
        db.execSQL(CREATE_KERANJANG_TABLE);
        db.execSQL(CREATE_PERTANYAAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KELOMPOK_ARISAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANGGOTA_ARISAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KERANJANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERTANYAAN);

//        if(newVersion > oldVersion){
//            db.execSQL("ALTER TABLE " + TABLE_BARANG + " ADD COLUMN " + KEY_STOK + " INTEGER DEFAULT 0");
//        }

        // Create tables again
        onCreate(db);
    }
    
    public void addKelompokArisan(KelompokArisan kelompokArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String date = sdf.format(kelompokArisan.getTanggalMulai());

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, kelompokArisan.getNama());
        values.put(KEY_TIPE, kelompokArisan.getTipe());
        values.put(KEY_TANGGAL_MULAI, date);
        values.put(KEY_SETORAN, kelompokArisan.getSetoran());
        values.put(KEY_STATUS, kelompokArisan.getStatus());
        values.put(KEY_BONUS, kelompokArisan.getBonus());

        db.insert(TABLE_KELOMPOK_ARISAN, null, values);
        db.close();
    }

    public KelompokArisan getKelompokArisan(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_KELOMPOK_ARISAN,
                new String[]{
                        KEY_ID,
                        KEY_NAMA,
                        KEY_TIPE,
                        KEY_TANGGAL_MULAI,
                        KEY_SETORAN,
                        KEY_STATUS,
                        KEY_BONUS},
                KEY_ID + " = ?",
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
        values.put(KEY_NAMA, kelompokArisan.getNama());
        values.put(KEY_TIPE, kelompokArisan.getTipe());
        values.put(KEY_TANGGAL_MULAI, date);
        values.put(KEY_SETORAN, kelompokArisan.getSetoran());
        values.put(KEY_STATUS, kelompokArisan.getStatus());
        values.put(KEY_BONUS, kelompokArisan.getBonus());

        return db.update(
                TABLE_KELOMPOK_ARISAN,
                values,
                KEY_ID + " ?",
                new String[] {String.valueOf(kelompokArisan.getId())}
        );
    }

    public void deleteKelompokArisan(KelompokArisan kelompokArisan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_KELOMPOK_ARISAN,
                KEY_ID + " = ?",
                new String[] {String.valueOf(kelompokArisan.getId())}
        );
        db.close();
    }

    public void deleteAllKelompokArisan(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KELOMPOK_ARISAN, null, null);
        db.close();
    }

    public void addAnggotaArisan(AnggotaArisan anggotaArisan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, anggotaArisan.getNama());
        values.put(KEY_NO_HP, anggotaArisan.getNoHp());
        values.put(KEY_ALAMAT, anggotaArisan.getAlamat());
        values.put(KEY_ID_KELOMPOK_ARISAN, anggotaArisan.getKelompokArisanId());

        db.insert(TABLE_ANGGOTA_ARISAN, null, values);
        db.close();
    }

    public AnggotaArisan getAnggotaArisan(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_ANGGOTA_ARISAN,
                new String[]{
                        KEY_ID,
                        KEY_NAMA,
                        KEY_NO_HP,
                        KEY_ALAMAT,
                        KEY_ID_KELOMPOK_ARISAN},
                KEY_ID + " = ?",
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
        values.put(KEY_NAMA, anggotaArisan.getNama());
        values.put(KEY_NO_HP, anggotaArisan.getNoHp());
        values.put(KEY_ALAMAT, anggotaArisan.getAlamat());
        values.put(KEY_ID_KELOMPOK_ARISAN, anggotaArisan.getKelompokArisanId());

        return db.update(
                TABLE_ANGGOTA_ARISAN,
                values,
                KEY_ID + " ?",
                new String[] {String.valueOf(anggotaArisan.getId())}
        );
    }

    public void deleteAnggotaArisan(AnggotaArisan anggotaArisan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_ANGGOTA_ARISAN,
                KEY_ID + " = ?",
                new String[] {String.valueOf(anggotaArisan.getId())}
        );
        db.close();
    }

    public void deleteAllAnggotaArisan(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ANGGOTA_ARISAN, null, null);
        db.close();
    }

    public long addBarang(Barang barang){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, barang.getNama());
        values.put(KEY_HARGA, barang.getHarga());
        values.put(KEY_KODE_PRODUK, barang.getKodeProduk());
        values.put(KEY_STOK, barang.getStok());
        values.put(KEY_DESKRIPSI, barang.getDeskripsi());
        values.put(KEY_SPESIFIKASI, barang.getSpesifikasi());

        long id = db.insert(TABLE_BARANG, null, values);
        db.close();

        return id;
    }

    public Barang getBarang(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BARANG + " WHERE "
                + KEY_ID + " = " + id;

        Log.d("query", selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        Barang barang = null;

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            barang = new Barang(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        cursor.close();

        return barang;
    }

    public List<Barang> getAllBarang() throws ParseException {
        List<Barang> barangList = new ArrayList<Barang>();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_BARANG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Barang barang = new Barang();
                barang.setId(Integer.parseInt(cursor.getString(0)));
                barang.setNama(cursor.getString(1));
                barang.setHarga(Integer.parseInt(cursor.getString(2)));
                barang.setKodeProduk(cursor.getString(3));
                barang.setStok(Integer.parseInt(cursor.getString(4)));
                barang.setDeskripsi(cursor.getString(5));
                barang.setSpesifikasi(cursor.getString(6));

                barangList.add(barang);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return barangList;
    }

    public List<Barang> getAllBarang(String tipe) throws ParseException {
        List<Barang> barangList = new ArrayList<Barang>();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_BARANG + " WHERE KODE_PRODUK LIKE '%" + tipe +"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Barang barang = new Barang();
                barang.setId(Integer.parseInt(cursor.getString(0)));
                barang.setNama(cursor.getString(1));
                barang.setHarga(Integer.parseInt(cursor.getString(2)));
                barang.setKodeProduk(cursor.getString(3));
                barang.setStok(Integer.parseInt(cursor.getString(4)));
                barang.setDeskripsi(cursor.getString(5));
                barang.setSpesifikasi(cursor.getString(6));

                barangList.add(barang);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return barangList;
    }

    public int getBarangCount(){
        String countQuery = "SELECT * FROM " + TABLE_BARANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateBarang(Barang barang){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, barang.getNama());
        values.put(KEY_HARGA, barang.getHarga());
        values.put(KEY_KODE_PRODUK, barang.getKodeProduk());
        values.put(KEY_STOK, barang.getStok());
        values.put(KEY_DESKRIPSI, barang.getDeskripsi());
        values.put(KEY_SPESIFIKASI, barang.getSpesifikasi());

        return db.update(
                TABLE_BARANG,
                values,
                KEY_ID + " ?",
                new String[] {String.valueOf(barang.getId())}
        );
    }

    public void deleteBarang(Barang barang){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_BARANG,
                KEY_ID + " = ?",
                new String[] {String.valueOf(barang.getId())}
        );
        db.close();
    }

    public void deleteAllBarang(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BARANG, null, null);
        db.close();
    }

    public void addBarangInstance(Context context){
        DatabaseHandler db = new DatabaseHandler(context);

        Barang barang1 = new Barang("Shinta Lemari Plastik Cokelat 4 Susun", 259500, "FURNITURE-01", 10, "Lemari laci berbahan plastik warna cokelat ini terdiri dari 4 susun laci yang dapa memuat beragam keperluan di rumah.", "Bahan: Plastik\nDimensi: 48x43x89cm");
        Barang barang2 = new Barang("Shinta Sauce Pan", 89500, "PERALATAN MASAK-01", 10, "Panci yang disertai tutup ini sangat cocok bagi Anda yang gemar memasak rebusan dalam jumlah sedikit", "Ukuran: Diameter 18cm\nBahan: Alumunium");
        Barang barang3 = new Barang("Fidela Set Pisau Dapur", 155000, "PERALATAN DAPUR-01", 10, "Fidela Pisau Set memiliki warna yang ceria membuat hari-hari anda dalam memotong bahan makanan apapun menjadi lebih semangat.", "Bahan: Stainless Steel\nKelengkapan: 1 pcs Pisau Chef 12cm, 1 pcs Pisau Roti 12.5cm, 1 pcs Pisau Daging 10cm, 1 pcs Pisau Serbagugna 9.5cm, 1 pcs Pisau Paring 7.5cm, 1 pcs Gunting 8.5cm\nDimensi: 35x12x9cm\nBerat: 2kg\nWarna sesuai stock yang tersedia");
        Barang barang4 = new Barang("Nicer Dicer Pemotong Serbaguna", 159500, "PERALATAN DAPUR-02", 10, "Memotong aneka sayuran bahan makanan dengan mudah tanpa harus membuang banyak waktu dan tenaga. Berbagai jenis potongan dan bentuk makanan dapat dihasilkan hanya dengan 1 alat.", "Bahan: Plastik ABS\n11 macam variasi potongan");
        Barang barang5 = new Barang("Quantum Kompor Gas 1 Tungku", 209500, "PERALATAN DAPUR-03", 10, "Quantum Kompor Gas 1 Tungku mempunyai desain elegan dengan warna putih yang cantik. Praktis dan cocok untuk ditempatkan di pantry, kontrakan, kos, apartemen, dan lainnya. Kompor mini yang bisa memberikan kualitas dan kualifikasi yang baik. Satu tungku dengan empat penyangga membuat setiap perabotan masak berdiri dengan seimbang. Pemantik mekanik mempermudah setiap menyalakan maupun mematikan kompor.", "Dimensi: 30x40x13cm\nMaterial: Burner Kuningan\nBerat: 2.78 kg");
        Barang barang6 = new Barang("Cosmos Rice Box", 299900, "PERALATAN DAPUR-04", 0, "Tempat ini dapat menampung beras hingga 12kg dan dilengkapi dengan laci takar sehingga Anda dapat memasak nasi sesuai dengan takaran yang diinginkan.", "Bahan: Plastik\nKapasitis: 12 kg");

        db.addBarang(barang1);
        db.addBarang(barang2);
        db.addBarang(barang3);
        db.addBarang(barang4);
        db.addBarang(barang5);
        db.addBarang(barang6);
    }

    public long addKeranjang(int buttonId, int barangId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BUTTON, buttonId);
        values.put(KEY_ID_BARANG, barangId);

        long id = db.insert(TABLE_KERANJANG, null, values);
        db.close();

        return id;
    }

    public int getKeranjang(int buttonId){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_KERANJANG + " WHERE " + KEY_ID_BUTTON + " = " + buttonId;
        Log.d("get barang query",selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        // TODO: Cek kenapa dia masuk ke sini :(
        int barangId = -999;
        if (cursor.getCount() != 0){
            Log.d("cek","kenapa kamu masuk sini "+cursor.getCount());
            cursor.moveToFirst();
            barangId = Integer.parseInt(cursor.getString(2));
            Log.d("haha","haha "+barangId);
        }

        cursor.close();

        return barangId;
    }

    public HashMap getAllKeranjang() throws ParseException {
        HashMap hashMap = new HashMap();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_KERANJANG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                int idButton = Integer.parseInt(cursor.getString(1));
                int idBarang = getKeranjang(idButton);
                Log.d("testing",idButton+" "+idBarang);
                Barang barang = getBarang(idBarang);

                hashMap.put(cursor.getString(1), barang);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return hashMap;
    }

    public HashMap getAllKeranjang(int idButton) throws ParseException {
        HashMap hashMap = new HashMap();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_KERANJANG + " WHERE " + KEY_ID_BUTTON + " = "+idButton;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                int idBarang = getKeranjang(Integer.parseInt(cursor.getString(1)));
                Log.d("testing",""+idBarang);
                Barang barang = getBarang(idBarang);

                hashMap.put(cursor.getString(1), barang);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return hashMap;
    }

    public int getKeranjangCount(){
        String countQuery = "SELECT * FROM " + TABLE_KERANJANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateKeranjang(int id, Barang barang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BARANG, barang.getId());

        return db.update(
                TABLE_KERANJANG,
                values,
                KEY_ID + " ?",
                new String[]{String.valueOf(id)}
        );
    }

    public void deleteKeranjang(int idButton){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_KERANJANG,
                KEY_ID_BUTTON + " = ?",
                new String[] {String.valueOf(idButton)}
        );
        db.close();
    }

    public void deleteAllKeranjang(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KERANJANG, null, null);
        db.close();
    }

    public long addPertanyaan(Pertanyaan pertanyaan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BARANG, pertanyaan.getIdBarang());
        values.put(KEY_PERTANYAAN, pertanyaan.getPertanyaan());

        long id = db.insert(TABLE_PERTANYAAN, null, values);
        db.close();

        return id;
    }

    public Pertanyaan getPertanyaan(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PERTANYAAN + " WHERE "
                + KEY_ID + " = " + id;

        Log.d("query", selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        Pertanyaan pertanyaan= null;

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            pertanyaan = new Pertanyaan(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(3)
            );
        }
        cursor.close();

        return pertanyaan;
    }

    public List<Pertanyaan> getAllPertanyaan() throws ParseException {
        List<Pertanyaan> pertanyaanList = new ArrayList<>();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_BARANG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Pertanyaan pertanyaan = new Pertanyaan();
                pertanyaan.setId(Integer.parseInt(cursor.getString(0)));
                pertanyaan.setIdBarang(Integer.parseInt(cursor.getString(1)));
                pertanyaan.setPertanyaan(cursor.getString(2));

                pertanyaanList.add(pertanyaan);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return pertanyaanList;
    }

    public List<Pertanyaan> getAllPertanyaan(int idBarang) throws ParseException {
        List<Pertanyaan> pertanyaanList = new ArrayList<>();

        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_PERTANYAAN + " WHERE "
                + KEY_ID_BARANG + " = " + idBarang;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Pertanyaan pertanyaan = new Pertanyaan();
                pertanyaan.setId(Integer.parseInt(cursor.getString(0)));
                pertanyaan.setIdBarang(Integer.parseInt(cursor.getString(1)));
                pertanyaan.setPertanyaan(cursor.getString(2));

                pertanyaanList.add(pertanyaan);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return pertanyaanList;
    }

    public int getPertanyaanCount(){
        String countQuery = "SELECT * FROM " + TABLE_PERTANYAAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getPertanyaanCount(int idBarang){
        String countQuery = "SELECT * FROM " + TABLE_PERTANYAAN + " WHERE "
                + KEY_ID_BARANG + " = " + idBarang;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updatePertanyaan(Pertanyaan pertanyaan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BARANG, pertanyaan.getIdBarang());
        values.put(KEY_HARGA, pertanyaan.getPertanyaan());

        return db.update(
                TABLE_PERTANYAAN,
                values,
                KEY_ID + " ?",
                new String[] {String.valueOf(pertanyaan.getId())}
        );
    }

    public void deletePertanyaan(Pertanyaan pertanyaan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_PERTANYAAN,
                KEY_ID + " = ?",
                new String[] {String.valueOf(pertanyaan.getId())}
        );
        db.close();
    }

    public void deleteAllPertanyaan(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERTANYAAN, null, null);
        db.close();
    }

    public void deleteAllTables(){
        deleteAllKelompokArisan();
        deleteAllAnggotaArisan();
        deleteAllBarang();
        deleteAllKeranjang();
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
