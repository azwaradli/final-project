package com.example.azwar.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Azwar on 5/11/2017.
 */

public class TanyaItemView extends RecyclerView.ViewHolder {
    public TextView isiPertanyaan;

    public TanyaItemView(View itemView){
        super(itemView);
        isiPertanyaan = (TextView) itemView.findViewById(R.id.isiPertanyaan);
    }
}
