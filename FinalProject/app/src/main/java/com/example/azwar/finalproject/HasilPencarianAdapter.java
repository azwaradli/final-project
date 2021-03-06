package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Azwar on 5/9/2017.
 */

public class HasilPencarianAdapter extends RecyclerView.Adapter<KategoriItemView> {
    private List<Barang> barangList;
    private Context context;

    public HasilPencarianAdapter(List<Barang> barangList) {
        this.barangList = barangList;
    }

    @Override
    public KategoriItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.kategori_item_view, parent, false);
        KategoriItemView viewHolder = new KategoriItemView(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KategoriItemView holder, int position) {
        if (barangList != null) {
            Log.d("hasil","berhasil");
            LinearLayout kategoriLayout = holder.kategoriLayout;
            Log.d("barang id",""+barangList.get(position).getId());
            kategoriLayout.setTag(barangList.get(position).getId());

            TextView kategoriNama = holder.kategoriNama;
            kategoriNama.setText(barangList.get(position).getNama());

            if (barangList.get(position).getStok() == 0){
                TextView kategoriStatus = holder.kategoriStatus;
                kategoriStatus.setText("Barang Tidak Tersedia");
                kategoriStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
            }

            TextView kategoriHarga = holder.kategoriHarga;
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String harga = formatter.format(barangList.get(position).getHarga());
            harga = harga.replace(',', '.');
            kategoriHarga.setText("Rp "+harga);

            TextView kategoriCicilan = holder.kategoriCicilan;
            int cicilan = barangList.get(position).getHarga()/5;
            String setoran = formatter.format(cicilan);
            setoran = setoran.replace(',', '.');
            kategoriCicilan.setText("Cicilan Rp "+setoran+"/bulan");
        }
        else {
            Log.d("hasil","gagal");
        }
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }
}
