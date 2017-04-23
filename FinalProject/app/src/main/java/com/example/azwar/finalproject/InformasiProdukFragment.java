package com.example.azwar.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.ParseException;

public class InformasiProdukFragment extends Fragment {
    DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informasi_produk, container, false);

        DetailBarangActivity activity = (DetailBarangActivity) getActivity();
        String message = activity.getMessage();
        if(message.equals("katalogActivity")){
            Button pilihBarangButton = (Button) view.findViewById(R.id.pilihBarangButton);
            pilihBarangButton.setVisibility(View.INVISIBLE);
        }

        int idBarang = activity.getIdBarang();
        Log.d("id barang",""+idBarang);
        db = new DatabaseHandler(getContext());
        Barang barang = null;
        try {
            barang = db.getBarang(idBarang);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (barang != null)
            Log.d("hasil get","berhasil");
        else
            Log.d("hasil get","gagal");

        TextView namaBarang = (TextView) view.findViewById(R.id.namaBarang);
        namaBarang.setText(barang.getNama());

        TextView setoranBarang = (TextView) view.findViewById(R.id.setoranBarang);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        int cicilan = barang.getHarga()/5;
        String setoran = formatter.format(cicilan);
        setoran = setoran.replace(',', '.');
        setoranBarang.setText("Rp. "+setoran+"/bulan (5 anggota)");

        TextView hargaBarang = (TextView) view.findViewById(R.id.hargaBarang);
        String harga = formatter.format(barang.getHarga());
        harga = harga.replace(',', '.');
        hargaBarang.setText("Harga total Rp. "+harga);

        TextView deskripsiBarang = (TextView) view.findViewById(R.id.deskripsiBarang);
        deskripsiBarang.setText(barang.getDeskripsi());

        TextView spesifikasiBarang = (TextView) view.findViewById(R.id.spesifikasiBarang);
        spesifikasiBarang.setText(barang.getSpesifikasi());

        return view;
    }
}
