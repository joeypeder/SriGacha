package com.example.srirgacha.network

import com.example.srirgacha.service.dto.Metals
import retrofit2.Call
import retrofit2.http.GET

interface GoldApi {

    @GET("spot")
    fun getGold(): Call<Metals>

}