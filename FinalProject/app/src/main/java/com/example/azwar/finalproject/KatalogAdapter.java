package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Azwar on 4/10/2017.
 */

public class KatalogAdapter extends RecyclerView.Adapter<KatalogItemView> {
    private String[] katalogNames = new String[] {
        "Produk Terbaru",
        "Furniture",
        "Peralatan Masak",
        "Peralatan Dapur",
        "Pakaian Pria",
        "Pakaian Wanita",
        "Sepatu Pria",
        "Sepatu Wanita",
        "Elektronik Rumah Tangga",
        "Handphone dan Tablet"
    };

    private int[] katalogButtonIcon = {
        R.drawable.ic_gift,
        R.drawable.ic_furniture,
        R.drawable.ic_alatmasak,
        R.drawable.ic_dapur,
        R.drawable.ic_shirt,
        R.drawable.ic_dress,
        R.drawable.ic_sepatupria,
        R.drawable.ic_sepatuwanita,
        R.drawable.ic_elektronik,
        R.drawable.ic_phone
    };

    @Override
    public KatalogItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.katalog_item_view, parent, false);
        KatalogItemView viewHolder = new KatalogItemView(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KatalogItemView holder, int position) {
        Button button = holder.mButton;
        button.setText(katalogNames[position]);
        button.setCompoundDrawablesWithIntrinsicBounds(katalogButtonIcon[position], 0, 0, 0);
        button.setTag(katalogNames[position]);
    }

    @Override
    public int getItemCount() {
        return katalogNames.length;
    }
}
