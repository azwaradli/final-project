package com.example.azwar.finalproject;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DetailBarangActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private String message;
    private int idBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        Intent intent = getIntent();
        if(intent != null){
            message = intent.getStringExtra(KatalogActivity.PAGE_ID);
            idBarang = Integer.parseInt(intent.getStringExtra(KategoriActivity.BARANG_ID));
        }


        tabLayout = (TabLayout) findViewById(R.id.detail_barang_tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.addTab(tabLayout.newTab().setText("Informasi"));
        tabLayout.addTab(tabLayout.newTab().setText("Ulasan"));
        tabLayout.addTab(tabLayout.newTab().setText("Tanya"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.detail_barang_pager);
        final DetailBarangPagerAdapter adapter = new DetailBarangPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void pilihBarang(View view){
        Intent intent = new Intent(this, DaftarKelompokArisanIsiActivity.class);
        startActivity(intent);
    }

    public String getMessage(){
        return message;
    }

    public int getIdBarang(){
        return idBarang;
    }
}
