package com.example.srirgacha.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiFactory {
    fun getGoldApi(): GoldApi {
        return Retrofit.Builder()
            .baseUrl("https://api.metals.live/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoldApi::class.java)
    }
}