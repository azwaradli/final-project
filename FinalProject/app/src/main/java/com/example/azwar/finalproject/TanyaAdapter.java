package com.example.azwar.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Azwar on 5/11/2017.
 */

public class TanyaAdapter extends RecyclerView.Adapter<TanyaItemView> {
    List<Pertanyaan> pertanyaanList;

    public TanyaAdapter(List<Pertanyaan> pertanyaanList){
        this.pertanyaanList = pertanyaanList;
    }

    @Override
    public TanyaItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.tanya_item_view, parent, false);
        TanyaItemView viewHolder = new TanyaItemView(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TanyaItemView holder, int position) {
        if (pertanyaanList != null){
            TextView isiPertanyaan = holder.isiPertanyaan;
            isiPertanyaan.setText(pertanyaanList.get(position).getPertanyaan());
        }
    }

    @Override
    public int getItemCount() {
        return pertanyaanList.size();
    }
}
