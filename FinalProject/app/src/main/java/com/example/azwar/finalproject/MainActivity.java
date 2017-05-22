package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private String pageID;
    private String idButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null){
            pageID = intent.getStringExtra(KatalogActivity.PAGE_ID);
            idButton = intent.getStringExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE);
        }

        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteAllPertanyaan();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                if (tabId == R.id.tab_katalog) {
                    fragment = new KatalogFragment();
                }
                else if (tabId == R.id.tab_arisan) {
                    fragment = new ArisanFragment();
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.commit();
            }
        });
    }

    public void kategori(View view){
        Intent intent = new Intent(this, KategoriActivity.class);
        String message = view.getTag().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        if(pageID == null){
            pageID = "katalogActivity";
        }
        intent.putExtra(KatalogActivity.PAGE_ID, pageID);
        startActivity(intent);
    }

    public void cari(View view){
        Intent intent = new Intent(this, HasilPencarianActivity.class);
        if(pageID == null){
            pageID = "katalogActivity";
        }
        intent.putExtra(KatalogActivity.PAGE_ID, pageID);
        intent.putExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }

    public void buatKelompokArisan(View view){
        Intent intent = new Intent(this, DaftarKelompokArisanActivity.class);
        startActivity(intent);
    }
}
