package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Azwar on 4/10/2017.
 */

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaItemView> {
    private int mCount;
    private Context context;
    private HashMap keranjangHashMap;

    public AnggotaAdapter(Context context, HashMap keranjangHashMap){
        this.context = context;
        this.keranjangHashMap = keranjangHashMap;
    }

    // untuk menghubungkan adapter dengan itemview
    @Override
    public AnggotaItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.anggota_item_view, parent, false);
        AnggotaItemView viewHolder = new AnggotaItemView(view);
        return viewHolder;
    }

    // yang ingin diubah isi layoutnya ketika bind dengan itemview
    // biasanya ada database
    @Override
    public void onBindViewHolder(AnggotaItemView holder, int position) {
        TextView title = holder.mTitle;
        Button pilihBarangButton = holder.pilihBarangButton;
        LinearLayout layoutKeranjang = holder.layoutKeranjang;
        TextView namaBarang = holder.namaBarang;
        TextView hargaBarang = holder.hargaBarang;
        TextView cicilanBarang = holder.cicilanBarang;
        Button gantiButton = holder.gantiButton;
        Button hapusButton = holder.hapusButton;

        Barang barang = null;
        if(keranjangHashMap != null){
            barang = (Barang) keranjangHashMap.get(""+position);
        }
        if(barang != null){
            layoutKeranjang.setVisibility(View.VISIBLE);
            pilihBarangButton.setVisibility(View.GONE);
            namaBarang.setText(barang.getNama());

            DecimalFormat formatter = new DecimalFormat("#,###,###");
            int hargaRaw = barang.getHarga();
            String harga = formatter.format(hargaRaw);
            harga = harga.replace(',', '.');
            hargaBarang.setText("Rp "+harga+",-");

            int hargaCicil = hargaRaw/5;
            String cicilan = formatter.format(hargaCicil);
            cicilan = cicilan.replace(',', '.');
            cicilanBarang.setText("Cicilan Rp "+cicilan+"/bulan");

            gantiButton.setTag(position);
            hapusButton.setTag(position);
        }
        else{
            pilihBarangButton.setVisibility(View.VISIBLE);
            pilihBarangButton.setTag(position);
        }

        title.setText("Anggota " + (position+1));

        Log.d("cek",""+position);
    }

    public void setContentCount(int count) {
        this.mCount = count;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
