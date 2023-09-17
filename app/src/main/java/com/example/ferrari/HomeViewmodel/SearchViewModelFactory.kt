package com.example.ferrari.HomeViewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ferrari.SongRepository.ShazamRepository

class SearchViewModelFactory(private val shazamRepository: ShazamRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(shazamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}