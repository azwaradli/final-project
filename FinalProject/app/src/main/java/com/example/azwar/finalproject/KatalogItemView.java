package com.example.azwar.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by Azwar on 4/10/2017.
 */

public class KatalogItemView extends RecyclerView.ViewHolder {
    public Button mButton;

    public KatalogItemView(View itemView) {
        super(itemView);
        mButton = (Button) itemView.findViewById(R.id.katalog_button);
    }
}
