package com.example.azwar.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Azwar on 4/10/2017.
 */

public class AnggotaItemView extends RecyclerView.ViewHolder {
    public TextView mTitle;
    public Button pilihBarangButton;

    public AnggotaItemView(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title_anggota);
        pilihBarangButton = (Button) itemView.findViewById(R.id.pilihBarangButton);
    }
}
