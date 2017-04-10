package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Azwar on 4/10/2017.
 */

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaItemView> {
    private int mCount;

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
        title.setText("Anggota " + (position+1));
    }

    public void setContentCount(int count) {
        this.mCount = count;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
