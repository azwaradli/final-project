package com.example.azwar.finalproject;

import java.util.Date;

/**
 * Created by Azwar on 4/20/2017.
 */

public class KelompokArisan {
    // private attribute
    private int id;
    private String nama;
    private String tipe;
    private Date tanggalMulai;
    private int setoran;
    private String status;
    private int bonus;

    public KelompokArisan(){

    }

    public KelompokArisan(int id, String nama, String tipe, Date tanggalMulai, int setoran, String status, int bonus) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.tanggalMulai = tanggalMulai;
        this.setoran = setoran;
        this.status = status;
        this.bonus = bonus;
    }

    public KelompokArisan(String nama, String tipe, Date tanggalMulai, int setoran, String status, int bonus) {
        this.nama = nama;
        this.tipe = tipe;
        this.tanggalMulai = tanggalMulai;
        this.setoran = setoran;
        this.status = status;
        this.bonus = bonus;
    }

    public int getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public String getTipe(){
        return tipe;
    }

    public Date getTanggalMulai(){
        return tanggalMulai;
    }

    public int getSetoran(){
        return setoran;
    }

    public String getStatus(){
        return status;
    }

    public int getBonus(){
        return bonus;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setTipe(String tipe){
        this.tipe = tipe;
    }

    public void setTanggalMulai(Date tanggalMulai){
        this.tanggalMulai = tanggalMulai;
    }

    public void setSetoran(int setoran){
        this.setoran = setoran;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setBonus(int bonus){
        this.bonus = bonus;
    }
}
