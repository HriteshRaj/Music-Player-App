package com.example.mymusicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: c0a842d4ddmshf2e87c225d6e937p1c2d1djsn43ddd4760e49", "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com")
        @GET("search")
    fun getData(@Query("q")query:String): Call<MyDataClass>



}