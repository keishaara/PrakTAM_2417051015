package model

import com.example.praktam_2417051015.R

object FoodSource {
    val dummyFood = listOf(
        Food("Sate", deskripsi = "Protein tinggi, tenaga tetap prima!", harga = 10000, imageRes = R.drawable.sate),
        Food("Nasi Goreng", deskripsi = "Menu favorit, tersedia di mana saja.", harga = 12000, imageRes = R.drawable.nasgor),
        Food("Bakso", deskripsi = "Kuah kaldu asli, bakso kenyal. Hangatkan harimu!", harga = 12000, imageRes = R.drawable.bakso),
        Food("Mie Ayam", deskripsi = "Cepat saji, praktis. Hemat waktu!", harga = 15000, imageRes = R.drawable.mie_ayam),
        Food("Ayam Bakar", deskripsi = "Ayam bakar dengan bumbu khas yang meresap dan aroma menggoda.", harga = 15000, imageRes = R.drawable.ayam_bakar),
        Food("Ayam Geprek", deskripsi = "Ayam crispy digeprek dengan sambal pedas yang menggugah selera.", harga = 10000, imageRes = R.drawable.ayam_geprek),
        Food("Ayam Lalapan", deskripsi = "Ayam goreng disajikan dengan lalapan segar dan sambal terasi.", harga = 15000, imageRes = R.drawable.ayam_lalapan),
        Food("Coto Makassar", deskripsi = "Sup daging khas Makassar dengan kuah gurih dan rempah pilihan.", harga = 20000, imageRes = R.drawable.coto_makassar),
        Food("Rendang", deskripsi = "Daging sapi dimasak lama dengan santan dan rempah khas Minangkabau.", harga = 25000, imageRes = R.drawable.rendang),
        Food("Soto Ayam", deskripsi = "Sup ayam dengan kuah kuning gurih dilengkapi telur dan soun.", harga = 15000, imageRes = R.drawable.soto_ayam)
    )
}