package com.example.praktam_2417051015

import Model.FoodSource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.praktam_2417051015.ui.theme.PrakTAM_2417051015Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051015Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val Food = FoodSource.dummyFood[0]

    Column(modifier = Modifier.fillMaxSize().padding(all = 30.dp)) {
        Image(
            painter = painterResource(id = Food.ImageRes),
            contentDescription = Food.nama,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
    Text(text = "Nama : ${Food.nama}")
    Text(text = "Deskripsi : ${Food.deskripsi}")
    Text(text = "Harga : ${Food.harga}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM_2417051015Theme {
        Greeting()
    }
}