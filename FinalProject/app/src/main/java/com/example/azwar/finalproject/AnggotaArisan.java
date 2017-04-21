package com.example.azwar.finalproject;

/**
 * Created by Azwar on 4/21/2017.
 */

public class AnggotaArisan {
    private int id;
    private String nama;
    private int noHp;
    private String alamat;
    private int kelompokArisanId;

    public AnggotaArisan(){

    }

    public AnggotaArisan(int id, String nama, int noHp, String alamat, int kelompokArisanId){
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.alamat = alamat;
        this.kelompokArisanId = kelompokArisanId;
    }

    public AnggotaArisan(String nama, int noHp, String alamat, int kelompokArisanId){
        this.nama = nama;
        this.noHp = noHp;
        this.alamat = alamat;
        this.kelompokArisanId = kelompokArisanId;
    }

    public int getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public int getNoHp(){
        return noHp;
    }

    public String getAlamat(){
        return alamat;
    }

    public int getKelompokArisanId(){
        return kelompokArisanId;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setNoHp(int noHp){
        this.noHp = noHp;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public void setKelompokArisanId(int kelompokArisanId){
        this.kelompokArisanId = kelompokArisanId;
    }
}
