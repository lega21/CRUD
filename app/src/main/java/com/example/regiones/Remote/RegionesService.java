package com.example.regiones.Remote;



import com.example.regiones.Model.RegionesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RegionesService {

    @GET("region/")
    Call<List<RegionesModel>> getRegion();

    @POST("region/")
    Call<RegionesModel> addRegion(@Body RegionesModel regionesModel);

    @PUT("region/{id}")
    Call<RegionesModel> updateRegion(@Path("id") int id, @Body RegionesModel regionesModel);

    @DELETE("region/{id}")
    Call<RegionesModel> deleteRegion(@Path("id") int id);
}

