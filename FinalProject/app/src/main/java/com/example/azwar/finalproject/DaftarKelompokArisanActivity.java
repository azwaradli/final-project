package com.example.azwar.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class DaftarKelompokArisanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public final static String EXTRA_MESSAGE = "com.example.finalproject.MESSAGE";
    private Spinner spinner;

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

        LinearLayout anggotaLayout = (LinearLayout) findViewById(R.id.anggota_section);
        anggotaLayout.removeAllViews();
        for(int i = 0; i < num; i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = convertDPtoPixels(20,this);

            TextView text = new TextView(this);
            text.setText("Anggota "+(i+1));
            text.setTextColor(ContextCompat.getColor(this, R.color.primaryText));
            layout.addView(text, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            EditText nama = new EditText(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = convertDPtoPixels(16,this);
            nama.setLayoutParams(params);
            nama.setPadding(convertDPtoPixels(8, this), convertDPtoPixels(0, this), convertDPtoPixels(0, this), convertDPtoPixels(12, this));
            nama.setHint("Nama");
            nama.setHintTextColor(ContextCompat.getColor(this, R.color.hintText));
            nama.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            layout.addView(nama, params);

            EditText noHP = new EditText(this);
            noHP.setLayoutParams(params);
            noHP.setPadding(convertDPtoPixels(8, this), convertDPtoPixels(0, this), convertDPtoPixels(0, this), convertDPtoPixels(12, this));
            noHP.setHint("No. HP");
            noHP.setHintTextColor(ContextCompat.getColor(this, R.color.hintText));
            noHP.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            layout.addView(noHP, params);

            Button button = new Button(this);
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.topMargin = convertDPtoPixels(12,this);
            button.setText("pilih barang");
            button.setTextColor(Color.WHITE);
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.orangeripple));
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DaftarKelompokArisanActivity.this, KatalogActivity.class);
                    startActivity(intent);
                }
            });
            layout.addView(button, buttonParams);

            anggotaLayout.addView(layout,layoutParams);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
