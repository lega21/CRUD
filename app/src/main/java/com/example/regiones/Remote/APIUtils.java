package com.example.regiones.Remote;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.137.96:8000/";

    public static RegionesService getRegionService(){
        return RetrofitClient.getClient(API_URL).create(RegionesService.class);
    }

}
