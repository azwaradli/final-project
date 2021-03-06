package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Azwar on 4/22/2017.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriItemView> {
    private List<Barang> barangList;
    private Context context;

    private int[] gambarDetail = {
            R.drawable.shinta_lemari_plastik_4_susun,
            R.drawable.shinta_sauce_pan,
            R.drawable.fidela_set_pisau,
            R.drawable.nicer_dicer_pemotong_serbaguna,
            R.drawable.quantum_kompor_gas_1_tungku,
            R.drawable.cosmos_rice_box,
            R.drawable.kemeja_wanita_swiss_biru,
            R.drawable.kemeja_wanita_swiss_biru,
            R.drawable.dandang_alcor_24cm_bronzo,
            R.drawable.orchid_panci_serbaguna,
            R.drawable.fidela_set_pisau,
            R.drawable.fidela_set_pisau,
            R.drawable.fidela_set_pisau,
            R.drawable.fidela_set_pisau,
            R.drawable.fidela_set_pisau
    };

    public KategoriAdapter(List<Barang> barangList) {
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
            LinearLayout kategoriLayout = holder.kategoriLayout;
            kategoriLayout.setTag(barangList.get(position).getId());

            ImageView kategoriGambar = holder.kategoriGambar;
            kategoriGambar.setImageResource(gambarDetail[barangList.get(position).getId()-1]);

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
            kategoriHarga.setText("harga total Rp "+harga);

            TextView kategoriCicilan = holder.kategoriCicilan;
            int cicilan = barangList.get(position).getHarga()/5;
            String setoran = formatter.format(cicilan);
            setoran = setoran.replace(',', '.');
            kategoriCicilan.setText("Setoran Rp "+setoran+"/bulan");
        }
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }
}
