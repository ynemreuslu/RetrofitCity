package com.example.retrofitcityapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitcityapp.databinding.ActivityDetailsBinding
import com.example.retrofitcityapp.retrofit.GeonameApi
import com.example.retrofitcityapp.retrofit.RetrofitInstance
import com.example.retrofitcityapp.retrofit.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    private lateinit var geonameService: GeonameApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        createViewBindingInstance()
        retrieveAndDisplayCityInformation()
    }

    private fun createViewBindingInstance() {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun fetchCityInfoFromIntent() {
        val incomingIntent = intent
        if (incomingIntent.hasExtra("selectedData")) {
            val selectedData = incomingIntent.getStringExtra("selectedData")
            binding.detailsActivityCityName.text = selectedData.toString()
        }
    }

    private fun retrieveAndDisplayCityInformation() {
        fetchCityInfoFromIntent()
        val geo = RetrofitInstance.retrofit
        geonameService = geo.create(GeonameApi::class.java)
        val call = geonameService.findWikipedia(
            "csystem", binding.detailsActivityCityName.text.toString(), true
        )

        call.enqueue(object : Callback<Root> {

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                val geoDate = response.body()
                if (geoDate != null) {
                    val date = geoDate.geonames?.map { it.summary }
                    binding.detailsActivityCityInformation.text = date.toString()

                } else {
                    Toast.makeText(
                        this@DetailsActivity, "City Information Did Not Arrive", Toast.LENGTH_LONG
                    ).show()
                }
            }


            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, "Try Again", Toast.LENGTH_LONG).show()
                call.cancel()
            }

        })
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this@DetailsActivity, message, Toast.LENGTH_LONG).show()
    }
}
