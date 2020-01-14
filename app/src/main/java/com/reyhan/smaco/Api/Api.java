package com.reyhan.smaco.Api;

import com.reyhan.smaco.Model.BrandModelResponse;
import com.reyhan.smaco.Model.TypeModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("show_brand.php")
    Call<BrandModelResponse> getBrand();

    @GET("show_all_type.php")
    Call<TypeModelResponse> getType();

    @POST("show_filter_type.php")
    @FormUrlEncoded
    Call<TypeModelResponse> postTypeFilter(
            @Field("query") String query
    );

    @POST("add_brand.php")
    @FormUrlEncoded
    Call<BrandModelResponse> postBrand(
            @Field("merek") String merek
    );

    @POST("add_type.php")
    @FormUrlEncoded
    Call<TypeModelResponse> postType(
            @Field("id_merek") String merek,
            @Field("tipe") String tipe,
            @Field("harga") String harga,
            @Field("kamera") String kamera,
            @Field("baterai") String baterai,
            @Field("ram") String ram,
            @Field("penyimpanan") String penyimpanan,
            @Field("prosesor") String prosesor
    );
}
