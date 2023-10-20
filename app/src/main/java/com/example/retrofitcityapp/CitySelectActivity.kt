package com.example.retrofitcityapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitcityapp.databinding.ActivityCitySelectBinding

class CitySelectActivity : AppCompatActivity() {

    lateinit var binding: ActivityCitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    private fun createViewBindingInstance() {
        binding = ActivityCitySelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initializeSpinner() {
        val adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, turkishCities)
        binding.spinner.adapter = adapter
    }

    private fun navigateToDetailsActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("selectedData", binding.spinner.selectedItem.toString())
        startActivity(intent)
    }

    private fun setCitySelectClick() {
        binding.citySelectButton.setOnClickListener {
            navigateToDetailsActivity()
        }
    }

    private fun initializeUI() {
        createViewBindingInstance()
        initializeSpinner()
        setCitySelectClick()
    }
}