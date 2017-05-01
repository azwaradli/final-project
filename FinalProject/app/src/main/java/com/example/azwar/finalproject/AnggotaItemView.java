package com.example.azwar.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Azwar on 4/10/2017.
 */

public class AnggotaItemView extends RecyclerView.ViewHolder {
    public TextView mTitle;
    public Button pilihBarangButton;
    public LinearLayout layoutKeranjang;
    public TextView namaBarang;
    public TextView hargaBarang;
    public TextView cicilanBarang;
    public Button gantiButton;
    public Button hapusButton;

    public AnggotaItemView(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title_anggota);
        pilihBarangButton = (Button) itemView.findViewById(R.id.pilihBarangButton);
        layoutKeranjang = (LinearLayout) itemView.findViewById(R.id.layoutKeranjang);
        namaBarang = (TextView) itemView.findViewById(R.id.namaBarang);
        hargaBarang = (TextView) itemView.findViewById(R.id.hargaBarang);
        cicilanBarang = (TextView) itemView.findViewById(R.id.cicilanBarang);
        gantiButton = (Button) itemView.findViewById(R.id.gantiButton);
        hapusButton = (Button) itemView.findViewById(R.id.hapusButton);
    }
}
