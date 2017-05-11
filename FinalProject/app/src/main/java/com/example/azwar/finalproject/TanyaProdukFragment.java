package com.example.azwar.finalproject;

import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TanyaProdukFragment extends Fragment {
    public final static String EXTRA_MESSAGE = "idBarang";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tanya_produk, container, false);

        DetailBarangActivity activity = (DetailBarangActivity) getActivity();
        final int idBarang = activity.getIdBarang();

        Button tanyaButton = (Button) view.findViewById(R.id.tanyaButton);
        tanyaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), TambahPertanyaanActivity.class);
                intent.putExtra(EXTRA_MESSAGE, idBarang);
                startActivity(intent);
            }
        });

        return view;
    }
}
