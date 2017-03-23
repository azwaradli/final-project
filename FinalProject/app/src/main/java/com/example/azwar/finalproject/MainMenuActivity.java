package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

//        ActionBar toolbar = getSupportActionBar();
//        toolbar.setTitle("Arisan Mapan");

    }

    public void lihatKatalog(View view){
        Intent intent = new Intent(this, KatalogActivity.class);
        startActivity(intent);
    }

    public void daftarKelompokArisan(View view){
        Intent intent = new Intent(this, DaftarKetuaArisanActivity.class);
        startActivity(intent);
    }

    public void menuKetuaArisan(View view){
        Intent intent = new Intent(this, MenuKetuaArisanActivity.class);
        startActivity(intent);
    }
}
