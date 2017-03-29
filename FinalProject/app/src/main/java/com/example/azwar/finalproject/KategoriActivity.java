package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class KategoriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        Intent intent = getIntent();
        String message = intent.getStringExtra(KatalogActivity.EXTRA_MESSAGE);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(message);
    }
}
