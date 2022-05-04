package com.example.srirgacha

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.srirgacha.service.GoldService
import com.example.srirgacha.service.MathService

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

        setSpinnerSelect()

        //fill spinner with strings.xml data
        ArrayAdapter.createFromResource(
            this,
            R.array.default_gachas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spDefaults.adapter = adapter
        }

        val metalService = GoldService()

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
            val namesPref = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
            with(namesPref.edit()){
                putString("odds", etChance.text.toString())
                putString("cost", etCost.text.toString())
                commit()
            }
            tvBackup.text = tvOutput.text.toString()

        }
    }

    private fun setSpinnerSelect(){
        spDefaults.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("asdf", "Nothing selected on spinner")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0){
                    val namesPref = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
                    etChance.setText(namesPref.getString("odds", "0"))
                    etCost.setText(namesPref.getString("cost", "0"))
                }
            }
        }
    }


}