package com.example.srirgacha.service

import com.example.srirgacha.service.dto.Metals
import retrofit2.Call
import retrofit2.http.GET

interface GoldService {

    @GET("spot")
    fun getMetal(): Call<Metals>

}