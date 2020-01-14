package com.reyhan.smaco.Model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BrandModelResponse {

    @SerializedName("result")
    public ArrayList<BrandModel> data;

    public static class BrandModel {

        @SerializedName("id_merek")
        public String idMerek;

        @SerializedName("merek")
        public String merek;

        @SerializedName("jml_tipe")
        public String jmlTipe;

    }
}
