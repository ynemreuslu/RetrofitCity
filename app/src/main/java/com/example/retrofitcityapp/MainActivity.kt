package com.example.retrofitcityapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitcityapp.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var imageChangeTimer: Timer? = null

    val imageResources = arrayOf(
        R.drawable.ic_istanbul, R.drawable.ic_ankara, R.drawable.ic_izmir, R.drawable.ic_balikesir
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    override fun onStop() {
        super.onStop()
        stopImageChange()
    }

    override fun onRestart() {
        super.onRestart()
        startImageChange()
    }

    private fun initializeUI() {
        createViewBindingInstance()
        setupCitySelectButton()
        startImageChange()
    }

    private fun createViewBindingInstance() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupCitySelectButton() {
        binding.mainActivityCitySelect.setOnClickListener {
            navigateToCitySelectActivity()
        }
    }

    private fun navigateToCitySelectActivity() {
        val intent = Intent(this, CitySelectActivity::class.java)
        startActivity(intent)
    }

    private fun startImageChange() {
        imageChangeTimer = Timer()
        var index = 0
        val timerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    binding.mainActvityImageView.setImageResource(imageResources[index])
                    index++

                    if (index >= imageResources.size) index = 0
                }
            }
        }
        imageChangeTimer?.scheduleAtFixedRate(timerTask, 0, 10000)
    }

    private fun stopImageChange() {
        imageChangeTimer?.cancel()
        imageChangeTimer = null
    }
}