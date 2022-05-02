package com.example.srirgacha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.srirgacha.service.GoldService
import com.example.srirgacha.service.MathService
import com.example.srirgacha.service.dto.Metals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log
import kotlin.math.round

//https://api.metals.live/

class MainActivity : AppCompatActivity() {
    lateinit var etChance: EditText
    lateinit var etCost: EditText
    lateinit var btCalculate: Button
    lateinit var tvOutput: TextView
    lateinit var btBackup: Button
    lateinit var tvBackup: TextView
    lateinit var spDefaults: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        setOnClicks()

        //fill spinner with strings.xml data
        ArrayAdapter.createFromResource(
            this,
            R.array.default_gachas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spDefaults.adapter = adapter
        }

        val mathService = buildService()
        mathService.getMetal().enqueue(object : Callback<Metals> {
            override fun onResponse(call: Call<Metals>, response: Response<Metals>) {
                Log.i("asdf", "onResponse()")

            }
            override fun onFailure(call: Call<Metals>, t: Throwable){
                Log.i("asdf", "GoldAPI call failed")
            }
        })

    }

    private fun bindViews(){
        etChance = findViewById(R.id.et_chance)
        etCost = findViewById(R.id.et_cost)
        btCalculate = findViewById(R.id.bt_calculate)
        tvOutput = findViewById(R.id.tv_output)
        btBackup = findViewById(R.id.bt_backup)
        tvBackup = findViewById(R.id.tv_backup)
        spDefaults = findViewById(R.id.sp_defaults)
    }

    private fun setOnClicks(){
        btCalculate.setOnClickListener(){
            tvOutput.text = MathService.CalculateOdds(etChance.text.toString().toDouble(), etCost.text.toString().toDouble());
        }
        btBackup.setOnClickListener(){
            tvBackup.text = tvOutput.text.toString()
        }
    }

    private fun buildService(): GoldService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.metals.live/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GoldService::class.java)
    }


}