package com.example.azwar.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ArisanSayaFragment extends Fragment {
    LinearLayout arisanHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arisan_saya, container, false);

        arisanHolder = (LinearLayout) view.findViewById(R.id.arisanHolder);

        arisanHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailArisanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
