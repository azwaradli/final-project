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

public class InformasiProdukFragment extends Fragment {

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

        return view;
    }
}
