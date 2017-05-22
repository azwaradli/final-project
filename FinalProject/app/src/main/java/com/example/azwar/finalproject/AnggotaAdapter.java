package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azwar.finalproject.AnggotaItemView;
import com.example.azwar.finalproject.Barang;
import com.example.azwar.finalproject.DaftarKelompokArisanActivity;
import com.example.azwar.finalproject.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Azwar on 4/10/2017.
 */

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaItemView> implements Validator.ValidationListener{
    private int mCount;
    private Context context;
    private HashMap keranjangHashMap;
    private Button lanjutButton;
    private ArrayList<EditText> namaArrayList;
    private ArrayList<EditText> noHpArrayList;

    @NotEmpty(message = "Harus diisi")
    private TextView namaAnggota;

    @NotEmpty(message = "Harus diisi")
    @Length(min = 12, message = "Minimum 12 angka")
    private TextView noHpAnggota;

    public AnggotaAdapter(final Context context, HashMap keranjangHashMap, Button lanjutButton){
        this.context = context;
        this.keranjangHashMap = keranjangHashMap;

        this.lanjutButton = lanjutButton;
        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        namaArrayList = new ArrayList<>();
        noHpArrayList = new ArrayList<>();

        this.lanjutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                validator.validate();
                ((DaftarKelompokArisanActivity) context).finish();
            }
        });
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

        namaAnggota = holder.namaAnggota;
        noHpAnggota = holder.noHpAnggota;

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

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error : errors){
            View view = error.getView();

            String message = error.getCollatedErrorMessage(context);

            //display the error message
            if(view instanceof EditText){
                ((EditText) view).setError(message);
                view.requestFocus();
            }
            else{
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
