package com.example.azwar.finalproject;

import android.widget.Button;

/**
 * Created by Azwar on 4/22/2017.
 */

public class Barang {
    private int id;
    private String nama;
    private int harga;
    private String kodeProduk;
    private String deskripsi;
    private String spesifikasi;

    public Barang(){

    }

    public Barang(int id, String nama, int harga, String kodeProduk, String deskripsi, String spesifikasi){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kodeProduk = kodeProduk;
        this.deskripsi = deskripsi;
        this.spesifikasi = spesifikasi;
    }

    public Barang(String nama, int harga, String kodeProduk, String deskripsi, String spesifikasi){
        this.nama = nama;
        this.harga = harga;
        this.kodeProduk = kodeProduk;
        this.deskripsi = deskripsi;
        this.spesifikasi = spesifikasi;
    }

    public int getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public int getHarga(){
        return harga;
    }

    public String getKodeProduk(){
        return kodeProduk;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public String getSpesifikasi(){
        return spesifikasi;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setHarga(int harga){
        this.harga = harga;
    }

    public void setKodeProduk(String kodeProduk){
        this.kodeProduk = kodeProduk;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public void setSpesifikasi(String spesifikasi){
        this.spesifikasi = spesifikasi;
    }
}
