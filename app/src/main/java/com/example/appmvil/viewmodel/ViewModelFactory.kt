package com.example.appmvil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appmvil.model.CountryRepository

class ViewModelFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(repository) as T
    }
}