package com.example.win7.misteragen.model;

import com.example.win7.misteragen.R;

public class Product {

    public String id;
    public String nama;
    public String stok;
    public String harga;
    public int image;

    public Product(String id, String nama, String stok, String harga, int image) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.image = image;
    }

    public static Product createGas3Kg() {
        return new Product("1", "Gas 3KG", "40", "Rp.17.000", R.mipmap.ic_gas_3_kg);
    }

    public static Product createGas12Kg() {
        return new Product("2", "Gas 12KG", "40", "Rp.139.000", R.mipmap.ic_gas_12_kg);
    }

    public static Product createAquaGalon() {
        return new Product("3", "Aqua Galon", "20", "Rp.17.000", R.mipmap.ic_aqua_galon);
    }

    public static Product createAquaDus() {
        return new Product("4", "Aqua Dus", "40", "Rp.26.000", R.mipmap.ic_aqua_dus);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
