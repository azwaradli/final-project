package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class KatalogActivity extends AppCompatActivity {
    public final static String PAGE_ID = "pageid";
    private String pageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

        Intent intent = getIntent();
        if(intent != null){
            pageID = intent.getStringExtra(PAGE_ID);
        }
    }

    public void kategori(View view){
        Intent intent = new Intent(this, KategoriActivity.class);
        String message = view.getTag().toString();
        intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
        if(pageID == null){
            pageID = "katalogActivity";
        }
        intent.putExtra(PAGE_ID, pageID);
        startActivity(intent);
    }

    public void cari(View view){
        Intent intent = new Intent(this, HasilPencarianActivity.class);
        startActivity(intent);
    }
}
