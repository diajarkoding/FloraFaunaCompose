package com.diajarkoding.florafauna.ui

import SpeciesPreferences
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diajarkoding.florafauna.ui.navigation.BottomNavItem
import com.diajarkoding.florafauna.ui.screen.FavoriteScreen
import com.diajarkoding.florafauna.ui.screen.HomeScreen
import com.diajarkoding.florafauna.ui.screen.ProfileScreen
import com.diajarkoding.florafauna.ui.screen.SearchScreen
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diajarkoding.florafauna.data.DummyData
import com.diajarkoding.florafauna.ui.screen.DetailScreen
import com.diajarkoding.florafauna.viewmodel.SpeciesViewModel
import com.diajarkoding.florafauna.viewmodel.SpeciesViewModelFactory

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    speciesPreferences: SpeciesPreferences
) {
    val viewModel: SpeciesViewModel = viewModel(
        factory = SpeciesViewModelFactory(speciesPreferences)
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val showBottomBar = currentDestination in listOf(
        BottomNavItem.Home.screenRoute,
        BottomNavItem.Search.screenRoute,
        BottomNavItem.Favorite.screenRoute,
        BottomNavItem.Profile.screenRoute
    )

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = showBottomBar) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.screenRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.screenRoute) {
                HomeScreen(
                    speciesList = viewModel.speciesList,
                    onItemClick = { species ->
                        navController.navigate("detail/${species.id}")
                    }
                )
            }
            composable(BottomNavItem.Search.screenRoute) {
                SearchScreen(
                    viewModel = viewModel,
                    onItemClick = { species ->
                        navController.navigate("detail/${species.id}")
                    }
                )
            }
            composable(BottomNavItem.Favorite.screenRoute) {
                FavoriteScreen(
                    viewModel = viewModel,
                    onItemClick = { navController.navigate("detail/$it") }
                )
            }
            composable(BottomNavItem.Profile.screenRoute) {
                ProfileScreen()
            }
            composable("detail/{speciesId}") {
                val speciesId = it.arguments?.getString("speciesId") ?: ""
                DetailScreen(
                    speciesId = speciesId,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}



@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorite,
        BottomNavItem.Profile,
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.screenRoute,
                onClick =  {
                    if (currentRoute != item.screenRoute) {
                        navController.navigate(item.screenRoute){
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }

    }
}