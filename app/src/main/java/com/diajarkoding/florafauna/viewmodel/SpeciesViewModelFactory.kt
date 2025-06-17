package com.diajarkoding.florafauna.viewmodel

import SpeciesPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SpeciesViewModelFactory(
    private val preferences: SpeciesPreferences
) : ViewModelProvider.Factory {
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  SpeciesViewModel(preferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}