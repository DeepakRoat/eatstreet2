package com.example.eatstreet2.network;

import com.example.eatstreet2.models.Vendor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/vendors")
    Call<Void> addVendor(@Header("Authorization") String authToken, @Body Vendor vendor);

    @GET("/api/vendors")
    Call<List<Vendor>> getVendors(
            @Query("lat") double latitude,
            @Query("lng") double longitude,
            @Query("radius") int radius,
            @Query("category") String category,
            @Query("is_veg") Boolean isVeg,
            @Query("page") int page,
            @Query("limit") int limit
    );
}
