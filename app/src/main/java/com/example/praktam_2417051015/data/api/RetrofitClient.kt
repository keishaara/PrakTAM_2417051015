package com.example.praktam_2417051015.data.api

import com.example.praktam_2417051015.data.model.Food
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gist.githubusercontent.com/keishaara/6bb5152ae099c0f4b268d370337efa4a/raw/29fd8976ac52a106fb696c0b46176a6a2c3849f7/"

    var cachedFoods: List<Food> = emptyList()
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}