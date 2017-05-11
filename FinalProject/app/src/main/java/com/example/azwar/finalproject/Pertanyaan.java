package com.example.azwar.finalproject;

/**
 * Created by Azwar on 5/11/2017.
 */

public class Pertanyaan {
    private int id;
    private int idBarang;
    private String pertanyaan;

    public Pertanyaan(){

    }

    public Pertanyaan(int id, int idBarang, String pertanyaan){
        this.id = id;
        this.idBarang = idBarang;
        this.pertanyaan = pertanyaan;
    }

    public Pertanyaan(int idBarang, String pertanyaan){
        this.idBarang = idBarang;
        this.pertanyaan = pertanyaan;
    }
    
    public int getId(){
        return id;
    }
    
    public int getIdBarang(){
        return idBarang;
    }

    public String getPertanyaan(){
        return pertanyaan;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setIdBarang(int idBarang){
        this.idBarang = id;
    }

    public void setPertanyaan(String pertanyaan){
        this.pertanyaan = pertanyaan;
    }
}
