package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;



public interface MusicServices {

    @GET("information")
    Call<CantanteResponse> getListaHabitantes();

    /*@GET("7182468")
    Call<MHResponse> getListaJudy();*/

}