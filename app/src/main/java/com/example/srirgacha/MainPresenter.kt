package com.example.srirgacha

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.srirgacha.network.GoldApi
import com.example.srirgacha.service.GoldService

class MainPresenter(
    val view: MainView,
    val goldService: GoldService,
    val sharedPreferences: SharedPreferences
){

    fun start(){
        getGold()
    }

    private fun getGold() {
        goldService.getGold(
            successCallback = { metals ->
                view.bindGold(metals)
            },
            failureCallback = { errorMessage ->
                view.showError(errorMessage)

            }
        )
    }

}