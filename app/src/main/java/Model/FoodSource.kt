package model

import com.example.praktam_2417051015.R

object FoodSource {
    val dummyFood = listOf(
        Food(nama = "Sate", deskripsi = "Protein tinggi, tenaga tetap prima!", harga = 10000, imageRes = R.drawable.sate),
        Food(nama = "Nasi Goreng", deskripsi = "Menu favorit, tersedia di mana saja.", harga = 12000, imageRes = R.drawable.nasgor),
        Food(nama = "Bakso", deskripsi = "Kuah kaldu asli, bakso kenyal. Hangatkan harimu!", harga = 12000, imageRes = R.drawable.bakso),
        Food(nama = "Mie Ayam", deskripsi = "Cepat saji, praktis. Hemat waktu!", harga = 15000, imageRes = R.drawable.mie_ayam)
    )
}