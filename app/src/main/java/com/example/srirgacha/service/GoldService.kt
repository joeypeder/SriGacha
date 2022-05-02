package com.example.srirgacha.service

import com.example.srirgacha.network.RetrofitApiFactory
import com.example.srirgacha.service.dto.Metals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class GoldService {

    val api = RetrofitApiFactory().getGoldApi()

    fun getGold(
        successCallback: (Metals) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ){
        api.getGold().enqueue(object : Callback<Metals> {

            override fun onResponse(call: Call<Metals>, response: Response<Metals>) {
                if (response.isSuccessful) {
                    response.body()?.let{
                        successCallback(it)
                    }?: run{
                        failureCallback("No metals found")
                    }
                } else {
                    failureCallback("Unable to retrieve metals")
                }
            }

            override fun onFailure(call: Call<Metals>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }
}