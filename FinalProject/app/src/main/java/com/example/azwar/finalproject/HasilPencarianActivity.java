package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.ParseException;
import java.util.List;

public class HasilPencarianActivity extends AppCompatActivity {
    private String message;
    private String pageID;
    private String idButton;
    private DatabaseHandler db;
    private RecyclerView mRecyclerView;
    private KategoriAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pencarian);

        Intent intent = getIntent();
        if (intent != null){
            message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            pageID = intent.getStringExtra(KatalogActivity.PAGE_ID);
            idButton = intent.getStringExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE);
        }

        db = new DatabaseHandler(this);

        List<Barang> barangList = null;
        try {
            barangList = db.getAllBarang();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.hasilPencarianRecyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new KategoriAdapter(barangList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void detailBarang(View view){
        Intent intent = new Intent(this, DetailBarangActivity.class);
        intent.putExtra(KatalogActivity.PAGE_ID, pageID);
        String idBarang = view.getTag().toString();
        intent.putExtra(KategoriActivity.BARANG_ID, idBarang);
        intent.putExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }
}
