package com.example.azwar.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.HashMap;

public class DaftarKelompokArisanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public final static String EXTRA_MESSAGE = "com.example.finalproject.MESSAGE";
    private Spinner spinner;
    private RecyclerView mRecyclerView;
    private AnggotaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelompok_arisan);

        spinner = (Spinner) findViewById(R.id.anggota_spinner);
        spinner.setOnItemSelectedListener(this);

        RadioButton radioBulanan = (RadioButton) findViewById(R.id.radio_bulanan);
        radioBulanan.setChecked(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anggota_bulanan_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        db = new DatabaseHandler(this);
        HashMap keranjangHashMap = null;
        try {
            keranjangHashMap = db.getAllKeranjang();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), ""+db.getKeranjangCount(), Toast.LENGTH_LONG).show();

        mRecyclerView = (RecyclerView) findViewById(R.id.anggotaRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AnggotaAdapter(this, keranjangHashMap);
        mAdapter.setContentCount(10);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radio_mingguan:
                if(checked){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.angota_mingguan_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
                break;
            case R.id.radio_bulanan:
                if(checked){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anggota_bulanan_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
                break;
        }
    }

    public static int convertDPtoPixels(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float _px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, metrics);
        return (int) _px;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String content = parent.getItemAtPosition(pos).toString();
        int num = Integer.parseInt(content);

        mAdapter.setContentCount(num);
        mAdapter.notifyDataSetChanged();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void pilihBarang(View view){
        Intent intent = new Intent(DaftarKelompokArisanActivity.this, KatalogActivity.class);
        intent.putExtra(KatalogActivity.PAGE_ID, "daftarKelompokArisanActivity");
        String idButton = view.getTag().toString();
        intent.putExtra(EXTRA_MESSAGE, idButton);
        startActivity(intent);
    }

    public void hapusBarang(View view){
        final String idButton = view.getTag().toString();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Anda yakin menghapus barang ini?");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Ya "+idButton, Toast.LENGTH_LONG).show();
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Tidak "+idButton, Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
