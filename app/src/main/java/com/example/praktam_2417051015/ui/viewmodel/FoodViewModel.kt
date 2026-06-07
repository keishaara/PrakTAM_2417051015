package com.example.praktam_2417051015.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051015.data.model.Food
import com.example.praktam_2417051015.data.model.MealLog
import com.example.praktam_2417051015.data.repository.FoodRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FoodViewModel : ViewModel() {
    private val repository = FoodRepository()

    var foods by mutableStateOf<List<Food>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var isError by mutableStateOf(false)
        private set

    var favorites by mutableStateOf<Set<String>>(emptySet())
        private set

    init {
        fetchFoods()
    }

    private fun fetchFoods() {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = repository.getFoods()
                foods = result
                isError = result.isEmpty()
            } catch (e: Exception) {
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    fun toggleFavorite(nama: String) {
        val currentFavorites = favorites.toMutableSet()
        if (currentFavorites.contains(nama)) {
            currentFavorites.remove(nama)
        } else {
            currentFavorites.add(nama)
        }
        favorites = currentFavorites
    }

    fun isFavorite(nama: String): Boolean {
        return favorites.contains(nama)
    }

    var mealHistory by mutableStateOf<List<MealLog>>(emptyList())
        private set

    fun confirmMeal(foodName: String) {
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
        val currentTime = sdf.format(Date())
        
        val newLog = MealLog(
            foodName = foodName,
            timeFormatted = currentTime
        )
        mealHistory = listOf(newLog) + mealHistory
    }

    fun clearHistory() {
        mealHistory = emptyList()
    }
}
