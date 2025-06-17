package com.diajarkoding.florafauna.viewmodel

import SpeciesPreferences
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diajarkoding.florafauna.data.DummyData
import com.diajarkoding.florafauna.data.Species
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SpeciesViewModel(
    private val speciesPreferences: SpeciesPreferences
) : ViewModel() {

    private val _speciesList = DummyData.speciesList.toMutableStateList()
    val speciesList: List<Species> get() = _speciesList

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private var favoriteIds = mutableStateListOf<String>()

    val favorites: List<Species>
        get() = _speciesList.filter { favoriteIds.contains(it.id) }

    init {
        viewModelScope.launch {
            speciesPreferences.favoriteIds.collectLatest { ids ->
                favoriteIds.clear()
                favoriteIds.addAll(ids)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    val filteredSpecies: List<Species>
        get() = if (_searchQuery.value.isBlank()) {
            speciesList
        } else {
            speciesList.filter {
                it.name.contains(_searchQuery.value, ignoreCase = true)
            }
        }

    val isSearchActive: Boolean
        get() = _searchQuery.value.isNotBlank()

    fun getSpeciesById(id: String): Species? = _speciesList.find { it.id == id }

    fun isFavorite(id: String): Boolean = favoriteIds.contains(id)

    fun toggleFavorite(id: String) {
        viewModelScope.launch {
            speciesPreferences.toggleFavorite(id)
        }
    }
}
