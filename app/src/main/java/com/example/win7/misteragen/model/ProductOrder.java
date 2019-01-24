package com.example.win7.misteragen.model;

import com.example.win7.misteragen.R;

public class ProductOrder {

    public String id;
    public String namaPembeli;
    public String namaBarang;
    public String alamatPembeli;
    public String jumlahOrderPembeli;
    public String noHpPembeli;
    public int image;

    public ProductOrder() {
    }

//    public ProductOrder(String id, String namaPembeli, String namaBarang, String alamatPembeli, String jumlahOrderPembeli, String noHpPembeli, int image) {
//        this.id = id;
//        this.namaPembeli = namaPembeli;
//        this.namaBarang = namaBarang;
//        this.alamatPembeli = alamatPembeli;
//        this.jumlahOrderPembeli = jumlahOrderPembeli;
//        this.noHpPembeli = noHpPembeli;
//        this.image = image;
//    }

//    public static ProductOrder createGas3Kg() {
//        return new ProductOrder("1", "Ida Bagus", "Gas 3KG", "Jl. Citayem no 14C", "1", "085212345678", R.mipmap.ic_gas_3_kg);
//    }
//
//    public static ProductOrder createAquaDus() {
//        return new ProductOrder("2", "Michael Young", "Aqua Dus", "Jl. Syahdan no 14C", "10", "089333345678", R.mipmap.ic_aqua_dus);
//    }
//
//    public static ProductOrder createAquaGalon() {
//        return new ProductOrder("3", "Ida Bagus", "Aqua Galon", "Jl. Citayem no 14C", "1", "085212345678", R.mipmap.ic_gas_12_kg);
//    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getAlamatPembeli() {
        return alamatPembeli;
    }

    public void setAlamatPembeli(String alamatPembeli) {
        this.alamatPembeli = alamatPembeli;
    }

    public String getJumlahOrderPembeli() {
        return jumlahOrderPembeli;
    }

    public void setJumlahOrderPembeli(String jumlahOrderPembeli) {
        this.jumlahOrderPembeli = jumlahOrderPembeli;
    }

    public String getNoHpPembeli() {
        return noHpPembeli;
    }

    public void setNoHpPembeli(String noHpPembeli) {
        this.noHpPembeli = noHpPembeli;
    }
}
