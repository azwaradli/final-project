package com.example.azwar.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by Azwar on 5/2/2017.
 */

public class SharedPreference {
    public static final String PREFS_NAME = "AOP_PREFS" ;
    public static final String KEY_PERIODE = "periode";
    public static final String KEY_ANGGOTA = "anggota";

    public SharedPreference(){
        super();
    }

    public void savePeriode(Context context, String periode){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(KEY_PERIODE, periode);
        editor.commit();
    }

    public String getPeriodeValue(Context context){
        SharedPreferences settings;
        String periode;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        periode = settings.getString(KEY_PERIODE, null);

        return periode;
    }

    public void removePeriodeValue(Context context){
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(KEY_PERIODE);
        editor.commit();
    }

    public void saveJumlahAnggotaIdx(Context context, int anggota){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putInt(KEY_ANGGOTA, anggota);
        editor.commit();
    }

    public int getJumlahAnggotaIdx(Context context){
        SharedPreferences settings;
        int jumlahAnggota;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        jumlahAnggota = settings.getInt(KEY_ANGGOTA, -999);

        return jumlahAnggota;
    }

    public void removeJumlahAnggotaIdx(Context context){
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(KEY_ANGGOTA);
        editor.commit();
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }
}
