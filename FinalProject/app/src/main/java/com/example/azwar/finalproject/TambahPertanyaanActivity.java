package com.example.azwar.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TambahPertanyaanActivity extends AppCompatActivity {
    private int idBarang;
    private DatabaseHandler db;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pertanyaan);

        db = new DatabaseHandler(this);
        alertDialogBuilder = new AlertDialog.Builder(this);

        Intent intent = getIntent();
        if (intent != null){
            idBarang = intent.getIntExtra(TanyaProdukFragment.EXTRA_MESSAGE, -999);
        }

        final EditText formPertanyaan = (EditText) findViewById(R.id.formPertanyaan);
        Button tanyaButton = (Button) findViewById(R.id.tanyaButton);
        tanyaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String isiPertanyaan = formPertanyaan.getText().toString();
                final Pertanyaan pertanyaan = new Pertanyaan(idBarang, isiPertanyaan);

                alertDialogBuilder.setMessage("Apakah Anda yakin ingin menanyakan pertanyaan ini?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.addPertanyaan(pertanyaan);
                                int count = db.getPertanyaanCount(idBarang);
//                                Toast.makeText(v.getContext(), ""+count, Toast.LENGTH_SHORT).show();
                                finish();
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
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
