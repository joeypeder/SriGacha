package com.example.srirgacha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.srirgacha.service.MathService
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.round

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

        etChance = findViewById(R.id.et_chance)
        etCost = findViewById(R.id.et_cost)
        btCalculate = findViewById(R.id.bt_calculate)
        tvOutput = findViewById(R.id.tv_output)
        btBackup = findViewById(R.id.bt_backup)
        tvBackup = findViewById(R.id.tv_backup)
        spDefaults = findViewById(R.id.sp_defaults)

        btCalculate.setOnClickListener(){
            tvOutput.text = CalculateOdds(etChance.text.toString().toDouble(), etCost.text.toString().toDouble());
        }
        btBackup.setOnClickListener(){
            tvBackup.text = tvOutput.text.toString()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.default_gachas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spDefaults.adapter = adapter
        }

    }

    //TODO("move math to MathService")
    fun CalculateOdds(calc: Double, cost: Double): String {
        var compliment = (100 - calc) / 100
        return "50%: \n$" + round(log(0.5, compliment) * cost) + "\n90%: \n$" + round(log(0.1, compliment) * cost) + "\n99%: \n$" + round(
            log(0.01, compliment) * cost)
    }

}