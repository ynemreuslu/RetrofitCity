package com.example.retrofitcityapp.retrofit

data class Geoname(
    var summary: String? = null,
    var elevation: Int = 0,
    var lng: Double = 0.0,
    var rank: Int = 0,
    var lang: String? = null,
    var title: String? = null,
    var lat: Double = 0.0
)
