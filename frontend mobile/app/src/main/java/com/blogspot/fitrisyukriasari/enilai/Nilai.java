package com.blogspot.fitrisyukriasari.enilai;

/**
 * Created by Fitri Syukriasari on 24/06/2017.
 */

public class Nilai {
    private String id;
    private String nama;
    private String nilai;
    private String matkul;

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
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

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

}
