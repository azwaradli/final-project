package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.text.ParseException;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {
    public static final String BARANG_ID = "barangId";
    String message;
    String pageID;
    String idButton;
    DatabaseHandler db;
    private RecyclerView mRecyclerView;
    private KategoriAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        Intent intent = getIntent();
        if (intent != null){
            message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            pageID = intent.getStringExtra(KatalogActivity.PAGE_ID);
            idButton = intent.getStringExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE);
        }

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(message);

        db = new DatabaseHandler(this);
//        db.deleteAllTables();

//        db.addBarangInstance(this);

        List<Barang> barangList = null;
        try {
            barangList = db.getAllBarang(message);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("barang", message);
        Log.d("jumlah barang", ""+barangList.size());
        for (Barang barang: barangList){
            String log = "id = "+barang.getId()+", nama = "+barang.getNama()+", harga = "+barang.getHarga()+", deskripsi= "+barang.getDeskripsi()+", spesifikasi= "+barang.getSpesifikasi();
//            Log.d("barang log", log);
        }

//        Log.d("add id",""+id);

//        Barang barang = null;
//        try {
//            barang = db.getBarang((int) id);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Log.d("cek barang", barang.getNama());

        mRecyclerView = (RecyclerView) findViewById(R.id.kategoriRecyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new KategoriAdapter(barangList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        db.closeDB();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    public void detailBarang(View view){
        Intent intent = new Intent(this, DetailBarangActivity.class);
        intent.putExtra(KatalogActivity.PAGE_ID, pageID);
        String idBarang = view.getTag().toString();
        intent.putExtra(BARANG_ID, idBarang);
        intent.putExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }
}
