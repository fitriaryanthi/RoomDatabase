package com.example.tugas2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tmobil")
public class Mobil implements Serializable{

    @PrimaryKey(autoGenerate = true)
    public int mobilId;

    @ColumnInfo(name = "nama_mobil")
    public String namaMobil;

    @ColumnInfo(name = "merk_mobil")
    public String merkMobil;

    @ColumnInfo(name = "harga_mobil")
    public String hargaMobil;

    public int getMobilId() {
        return mobilId;
    }

    public void setMobilId(int mobilId) {
        this.mobilId = mobilId;
    }

    public String getNamaMobil() {
        return namaMobil;
    }

    public String getMerkMobil() {
        return merkMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public void setMerkMobil(String merkMobil) {
        this.merkMobil = merkMobil;
    }

    public String getHargaMobil() {
        return hargaMobil;
    }

    public void setHargaMobil(String hargaMobil) {
        this.hargaMobil = hargaMobil;
    }
}
