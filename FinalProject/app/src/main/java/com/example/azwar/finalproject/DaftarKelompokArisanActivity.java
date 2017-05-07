package com.example.azwar.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static final String MyPREFERENCES = "MyPrefs" ;
    private Spinner spinner;
    private RecyclerView mRecyclerView;
    private AnggotaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHandler db;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelompok_arisan);

        sharedPreference = new SharedPreference();

        String periode = sharedPreference.getPeriodeValue(this);
        Log.d("huhu",periode);
        if(periode != null){
            if(periode.equals("bulanan")){
                RadioButton radioBulanan = (RadioButton) findViewById(R.id.radio_bulanan);
                radioBulanan.setChecked(true);
            }
            else{
                RadioButton radioMingguan = (RadioButton) findViewById(R.id.radio_mingguan);
                radioMingguan.setChecked(true);
            }
        }
        else {
            RadioButton radioBulanan = (RadioButton) findViewById(R.id.radio_bulanan);
            radioBulanan.setChecked(true);
        }

        spinner = (Spinner) findViewById(R.id.anggota_spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anggota_bulanan_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        int jumlahAnggotaIdx = sharedPreference.getJumlahAnggotaIdx(this);
        Log.d("huhu",""+jumlahAnggotaIdx);
        if(jumlahAnggotaIdx != -999){
            spinner.setSelection(jumlahAnggotaIdx);
        }

        db = new DatabaseHandler(this);
        HashMap keranjangHashMap = null;
        try {
            keranjangHashMap = db.getAllKeranjang();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button lanjutButton = (Button) findViewById(R.id.lanjutButton);

        mRecyclerView = (RecyclerView) findViewById(R.id.anggotaRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AnggotaAdapter(this, keranjangHashMap, lanjutButton);
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

                    sharedPreference.savePeriode(this, "mingguan");

                    int jumlahAnggotaIdx = sharedPreference.getJumlahAnggotaIdx(this);
                    Log.d("huhu",""+jumlahAnggotaIdx);
                    if(jumlahAnggotaIdx != -999){
                        spinner.setSelection(jumlahAnggotaIdx);
                    }
                }
                break;
            case R.id.radio_bulanan:
                if(checked){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anggota_bulanan_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    sharedPreference.savePeriode(this, "bulanan");

                    int jumlahAnggotaIdx = sharedPreference.getJumlahAnggotaIdx(this);
                    Log.d("huhu",""+jumlahAnggotaIdx);
                    if(jumlahAnggotaIdx != -999){
                        spinner.setSelection(jumlahAnggotaIdx);
                    }
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

        sharedPreference.saveJumlahAnggotaIdx(this, pos);
        int jumlahAnggotaIdx = sharedPreference.getJumlahAnggotaIdx(this);
        Log.d("huhu",""+jumlahAnggotaIdx);

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
        final int idButton = Integer.parseInt(view.getTag().toString());
        final DatabaseHandler db = new DatabaseHandler(this);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Anda yakin menghapus barang ini?");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteKeranjang(idButton);
                        finish();
                        startActivity(getIntent());
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
