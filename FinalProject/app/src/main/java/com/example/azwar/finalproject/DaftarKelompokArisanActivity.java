package com.example.azwar.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

public class DaftarKelompokArisanActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelompok_arisan);

        spinner = (Spinner) findViewById(R.id.anggota_spinner);

        RadioButton radioBulanan = (RadioButton) findViewById(R.id.radio_bulanan);
        radioBulanan.setChecked(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anggota_bulanan_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void pilihBarang(View view){
        Intent intent = new Intent(this, KatalogActivity.class);
        startActivity(intent);
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
}
