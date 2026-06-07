package com.example.praktam_2417051015

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051015.ui.screen.DaftarMakananScreen
import com.example.praktam_2417051015.ui.screen.DetailScreen
import com.example.praktam_2417051015.ui.screen.FavoriteScreen
import com.example.praktam_2417051015.ui.screen.HistoryScreen
import com.example.praktam_2417051015.ui.theme.PrakTAM_2417051015Theme
import com.example.praktam_2417051015.ui.viewmodel.FoodViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrakTAM_2417051015Theme {
                val navController = rememberNavController()
                // Instantiate ViewModel at the highest level so it survives screen navigation
                val foodViewModel: FoodViewModel = viewModel()
                
                AppNavigation(navController = navController, viewModel = foodViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: FoodViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")

    val bottomBarRoutes = listOf("home", "favorite", "history")
    val shouldShowBottomBar = bottomBarRoutes.contains(currentRoute)

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == "home",
                        onClick = {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorit") },
                        label = { Text("Favorit") },
                        selected = currentRoute == "favorite",
                        onClick = {
                            navController.navigate("favorite") {
                                popUpTo("home") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.DateRange, contentDescription = "Riwayat") },
                        label = { Text("Riwayat") },
                        selected = currentRoute == "history",
                        onClick = {
                            navController.navigate("history") {
                                popUpTo("home") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        // We use Modifier.padding(paddingValues) to push the NavHost content up
        // above the BottomNavigationBar (so it doesn't overlap).
        // Since DaftarMakananScreen and HistoryScreen have their own Scaffolds, 
        // this nested padding handles the Bottom Nav spacing.
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                DaftarMakananScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable("favorite") {
                FavoriteScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable("history") {
                HistoryScreen(
                    viewModel = viewModel
                )
            }

            composable("detail/{nama}") { backStackEntry ->
                val nama = backStackEntry.arguments?.getString("nama")
                val food = viewModel.foods.find { it.nama == nama }
                if (food != null) {
                    DetailScreen(
                        food = food,
                        navController = navController,
                        viewModel = viewModel,
                        isFullScreen = true
                    )
                }
            }
        }
    }
}