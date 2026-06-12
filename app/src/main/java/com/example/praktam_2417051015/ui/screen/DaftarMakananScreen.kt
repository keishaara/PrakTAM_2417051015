package com.example.praktam_2417051015.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051015.ui.components.FoodItem
import com.example.praktam_2417051015.ui.components.FoodRowItem
import com.example.praktam_2417051015.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarMakananScreen(
    navController: NavController,
    viewModel: FoodViewModel
) {
    val foods = viewModel.foods
    val isLoading = viewModel.isLoading
    val isError = viewModel.isError

    val snackbarHostState = remember { SnackbarHostState() }
    
    var showGachaDialog by remember { mutableStateOf(false) }
    var gachaText by remember { mutableStateOf("...") }

    var selectedMaxPrice by remember { mutableStateOf<Int?>(null) }
    
    val filteredFoods = if (selectedMaxPrice == null) {
        foods
    } else {
        foods.filter { it.harga <= selectedMaxPrice!! }
    }

    if (showGachaDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    text = "🎲 Mengacak Menu...",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = gachaText,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {},
            shape = RoundedCornerShape(24.dp)
        )

        LaunchedEffect(Unit) {
            val iterations = 20
            val delayMs = 100L
            for (i in 0 until iterations) {
                gachaText = filteredFoods.random().nama
                delay(delayMs)
            }
            
            val finalWinner = filteredFoods.random()
            gachaText = finalWinner.nama
            
            delay(1000)
            
            showGachaDialog = false
            navController.navigate("detail/${finalWinner.nama}")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (filteredFoods.isNotEmpty()) {
                                    showGachaDialog = true
                                } else {
                                }
                            }
                            .padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "🍽️ Acak Menu", 
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "Klik di sini untuk mengacak!",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Memuat menu pilihan...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else if (isError || foods.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "⚠️ Gagal Memuat Data",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Pastikan koneksi internet Anda menyala\ndan coba lagi nanti.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            item {
                                FilterChip(
                                    selected = selectedMaxPrice == null,
                                    onClick = { selectedMaxPrice = null },
                                    label = { Text("Semua Harga") }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = selectedMaxPrice == 10000,
                                    onClick = { selectedMaxPrice = 10000 },
                                    label = { Text("< Rp 10.000") }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = selectedMaxPrice == 15000,
                                    onClick = { selectedMaxPrice = 15000 },
                                    label = { Text("< Rp 15.000") }
                                )
                            }
                            item {
                                FilterChip(
                                    selected = selectedMaxPrice == 25000,
                                    onClick = { selectedMaxPrice = 25000 },
                                    label = { Text("< Rp 25.000") }
                                )
                            }
                        }

                        if (filteredFoods.isNotEmpty()) {
                            Text(
                                text = "🔥 Rekomendasi Populer",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 12.dp)
                            )
    
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                modifier = Modifier.padding(bottom = 24.dp)
                            ) {
                                items(filteredFoods.take(5)) { food ->
                                    FoodRowItem(
                                        food = food,
                                        navController = navController
                                    )
                                }
                            }
    
                            Text(
                                text = "📋 Daftar Menu Pilihan",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 12.dp)
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Tidak ada makanan di budget ini 😭",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    items(filteredFoods) { food ->
                        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                            FoodItem(
                                food = food,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
