package com.example.azwar.finalproject;

import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TanyaProdukFragment extends Fragment {
    public final static String EXTRA_MESSAGE = "idBarang";
    private DatabaseHandler db;
    private RecyclerView mRecyclerView;
    private TanyaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tanya_produk, container, false);

        DetailBarangActivity activity = (DetailBarangActivity) getActivity();
        final int idBarang = activity.getIdBarang();

        db = new DatabaseHandler(activity);
        List<Pertanyaan> pertanyaanList = null;
        try {
            pertanyaanList = db.getAllPertanyaan(idBarang);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button tanyaButton = (Button) view.findViewById(R.id.tanyaButton);
        tanyaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), TambahPertanyaanActivity.class);
                intent.putExtra(EXTRA_MESSAGE, idBarang);
                startActivityForResult(intent, 1);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.tanyaRecyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TanyaAdapter(pertanyaanList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }
}
