package com.example.srirgacha.service

import android.content.SharedPreferences
import com.example.srirgacha.MainPresenter
import com.example.srirgacha.MainView
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class MathServiceTest {
    val goldService: GoldService = mockk(relaxed = true)
    val view: MainView = mockk(relaxed = true)
    val sharedPreferences: SharedPreferences = mockk(relaxed = true)
    val presenter = MainPresenter(view, goldService, sharedPreferences)


    @Test
    fun `on start call gold api`(){
        presenter.start()
        verify{goldService.getGold(any(), any())}
    }
}