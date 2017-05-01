package com.example.azwar.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class DetailBarangActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private String message;
    private int idBarang;
    private int idButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        Intent intent = getIntent();
        if(intent != null){
            message = intent.getStringExtra(KatalogActivity.PAGE_ID);
            idBarang = Integer.parseInt(intent.getStringExtra(KategoriActivity.BARANG_ID));
            String buttonExtra = intent.getStringExtra(DaftarKelompokArisanActivity.EXTRA_MESSAGE);
            if(buttonExtra != null){
                idButton = Integer.parseInt(buttonExtra);
                Toast.makeText(getApplicationContext(), "Button "+idButton, Toast.LENGTH_LONG).show();
            }
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

    public void pilihBarang(View view) throws ParseException {
        final Intent intent = new Intent(this, DaftarKelompokArisanActivity.class);
        final DatabaseHandler db = new DatabaseHandler(this);

        int idBarangTemp = db.getKeranjang(idButton);
        if(idBarangTemp != -999){
            Barang barang = db.getBarang(idBarangTemp);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Apakah Anda ingin mengganti "+barang.getNama()+" dengan barang ini?");
            alertDialogBuilder.setPositiveButton("Ya",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Ya", Toast.LENGTH_LONG).show();
                            db.deleteKeranjang(idButton);
                            db.addKeranjang(idButton,idBarang);
                            startActivity(intent);
                        }
                    });
            alertDialogBuilder.setNegativeButton("Tidak",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Tidak", Toast.LENGTH_LONG).show();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else{
            startActivity(intent);
            db.addKeranjang(idButton, idBarang);
        }

        db.closeDB();
    }

    public String getMessage(){
        return message;
    }

    public int getIdBarang(){
        return idBarang;
    }
}
