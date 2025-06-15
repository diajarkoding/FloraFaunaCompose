package com.diajarkoding.florafauna.ui

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
import com.diajarkoding.florafauna.data.DummyData

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val speciesList = DummyData.speciesList // Data dummy diambil

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.Home.screenRoute,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.screenRoute) {
                HomeScreen(
                    speciesList = speciesList,
                    onItemClick = { species ->
                      
                    }
                )
            }
            composable(BottomNavItem.Search.screenRoute) {
                SearchScreen(navController)
            }
            composable(BottomNavItem.Favorite.screenRoute) {
                FavoriteScreen(navController)
            }
            composable(BottomNavItem.Profile.screenRoute) {
                ProfileScreen()
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