package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class KatalogActivity extends AppCompatActivity {
    public final static String PAGE_ID = "pageId";
    private String pageID;
    private RecyclerView mRecyclerView;
    private KatalogAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String idButton;
    private boolean clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

        Intent intent = getIntent();
        if(intent != null){
            pageID = intent.getStringExtra(PAGE_ID);
            idButton = intent.getStringExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.katalogRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new KatalogAdapter();
//        mAdapter.setContentCount(10);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void kategori(View view){
        Intent intent = new Intent(this, KategoriActivity.class);
        String message = view.getTag().toString();
        intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        if(pageID == null){
            pageID = "katalogActivity";
        }
        intent.putExtra(PAGE_ID, pageID);
        intent.putExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }

    public void cari(View view){
        Intent intent = new Intent(this, HasilPencarianActivity.class);
        if(pageID == null){
            pageID = "katalogActivity";
        }
        intent.putExtra(PAGE_ID, pageID);
        intent.putExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }

    public interface KatalogActivityListener{
        void closeActivity();
    }
}
