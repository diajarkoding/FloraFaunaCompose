package com.diajarkoding.florafauna.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.diajarkoding.florafauna.data.DummyData
import com.diajarkoding.florafauna.data.Species

class SpeciesViewModel : ViewModel(){

    private val _speciesList = DummyData.speciesList.toMutableStateList()
    val speciesList: List<Species> get() = _speciesList

    private val _favorites = mutableStateListOf<Species>()
    val favorites: List<Species> get() = _favorites

    fun getSpeciesById(id: String): Species? = _speciesList.find { it.id == id }

    fun isFavorite(id: String): Boolean = _favorites.any { it.id == id }

    fun toggleFavorite(id: String) {
        val species = getSpeciesById(id)
        species?.let {
            if (isFavorite(id)) {
                _favorites.removeAll { it.id == id }
            } else {
                _favorites.add(it)
            }
        }
    }
}