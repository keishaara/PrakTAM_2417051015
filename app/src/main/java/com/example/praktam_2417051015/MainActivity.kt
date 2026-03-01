package com.example.praktam_2417051015

import model.FoodSource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2417051015.ui.theme.PrakTAM_2417051015Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrakTAM_2417051015Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FoodList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun FoodList(modifier: Modifier = Modifier) {
    val foods = FoodSource.dummyFood

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Rekomendasi Menu Makanan",
            fontSize = 32.sp
        )
        Text(
            text = "Untuk Mahasiswa",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        foods.forEach {
            food -> FoodItem(food = food)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun FoodItem(food: model.Food) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp).padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = food.imageRes),
            contentDescription = food.nama,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Nama : ${food.nama}", fontSize = 18.sp)
        Text(text = "Deskripsi : ${food.deskripsi}", fontSize = 18.sp)
        Text(text = "Harga : Rp${food.harga}", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM_2417051015Theme {
        FoodList()
    }
}