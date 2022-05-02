package com.example.srirgacha

import com.example.srirgacha.service.dto.Metals

interface MainView {
    fun bindGold(metals: Metals)
    fun showError(errorMessage: String)
}