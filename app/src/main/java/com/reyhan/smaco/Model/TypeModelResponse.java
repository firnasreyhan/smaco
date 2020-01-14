package com.reyhan.smaco.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeModelResponse {

    @SerializedName("result")
    public ArrayList<TypeModel> data;

    public static class TypeModel implements Serializable {

        @SerializedName("id_tipe")
        public String idTipe;

        @SerializedName("merek")
        public String merek;

        @SerializedName("tipe")
        public String tipe;

        @SerializedName("harga")
        public double harga;

        @SerializedName("kamera")
        public double kamera;

        @SerializedName("baterai")
        public double baterai;

        @SerializedName("ram")
        public double ram;

        @SerializedName("penyimpanan")
        public double penyimpanan;

        @SerializedName("prosesor")
        public double prosesor;

        public double skor;

        public TypeModel() {
        }

        public TypeModel(String idTipe, String merek, String tipe, double harga, double kamera, double baterai, double ram, double penyimpanan, double prosesor, double skor) {
            this.idTipe = idTipe;
            this.merek = merek;
            this.tipe = tipe;
            this.harga = harga;
            this.kamera = kamera;
            this.baterai = baterai;
            this.ram = ram;
            this.penyimpanan = penyimpanan;
            this.prosesor = prosesor;
            this.skor = skor;
        }
    }
}
