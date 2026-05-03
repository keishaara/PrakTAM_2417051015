package com.example.praktam_2417051015.network

import model.Food
import retrofit2.http.GET

interface ApiService {
    @GET("menu_makanan.json") // Endpoint API Anda
    suspend fun getFoods(): List<Food>
}