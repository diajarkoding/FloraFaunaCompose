package com.diajarkoding.florafauna.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val screenRoute: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object  Search : BottomNavItem("Search", Icons.Default.Search, "search")
    object  Favorite : BottomNavItem("Favorite", Icons.Default.Favorite, "favorite")
    object  Profile : BottomNavItem("Profile", Icons.Default.Person, "profile")
}