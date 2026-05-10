package com.example.praktam_2417051015.data.repository
import com.example.praktam_2417051015.data.api.RetrofitClient
import com.example.praktam_2417051015.data.model.Food

class FoodRepository {
    suspend fun getFoods(): List<Food> {
        return try {
            RetrofitClient.instance.getFoods()
        } catch (e: Exception) {
            emptyList()
        }
    }
}