package com.example.azwar.finalproject;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Azwar on 4/22/2017.
 */

public class KategoriItemView extends RecyclerView.ViewHolder {
    public LinearLayout kategoriLayout;
    public TextView kategoriNama;
    public TextView kategoriHarga;
    public TextView kategoriCicilan;

    public KategoriItemView(View itemView){
        super(itemView);
        kategoriLayout = (LinearLayout) itemView.findViewById(R.id.kategoriLayout);
        kategoriNama = (TextView) itemView.findViewById(R.id.kategoriNama);
        kategoriHarga = (TextView) itemView.findViewById(R.id.kategoriHarga);
        kategoriCicilan = (TextView) itemView.findViewById(R.id.kategoriCicilan);
    }
}
