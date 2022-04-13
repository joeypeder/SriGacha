package com.example.srirgacha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    lateinit var etChance: EditText
    lateinit var etCost: EditText
    lateinit var btCalculate: Button
    lateinit var tvOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etChance = findViewById(R.id.et_chance)
        etCost = findViewById(R.id.et_cost)
        btCalculate = findViewById(R.id.bt_calculate)
        tvOutput = findViewById(R.id.tv_output)

        btCalculate.setOnClickListener(){
            tvOutput.text = CalculateOdds(etChance.text.toString().toDouble(), etCost.text.toString().toDouble());
        }
    }

    private fun CalculateOdds(calc: Double, cost: Double): String {
        var compliment = (100 - calc) / 100
        return "50%: \n$" + round(log(0.5, compliment) * cost) + "\n90%: \n$" + round(log(0.1, compliment) * cost) + "\n99%: \n$" + round(log(0.01, compliment) * cost)
    }
}