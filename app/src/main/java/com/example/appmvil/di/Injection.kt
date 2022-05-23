package com.example.appmvil.di

import com.example.appmvil.data.ApiClient
import com.example.appmvil.data.CountryRemoteDataSource
import com.example.appmvil.model.CountryDataSource
import com.example.appmvil.model.CountryRepository
import com.example.appmvil.viewmodel.ViewModelFactory


object Injection {
    private var countryDataSource: CountryDataSource? = null
    private var countryRepository: CountryRepository? = null
    private var countryViewModelFactory: ViewModelFactory? = null

    private fun createCountryDataSource(): CountryDataSource {
        val dataSource = CountryRemoteDataSource(ApiClient)
        countryDataSource = dataSource
        return dataSource
    }

    private fun createCountryRepository(): CountryRepository {
        val repository = CountryRepository(provideDataSource())
        countryRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        countryViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = countryDataSource ?: createCountryDataSource()
    private fun providerRepository() = countryRepository ?: createCountryRepository()

    fun provideViewModelFactory() = countryViewModelFactory ?: createFactory()

    fun destroy() {
        countryDataSource = null
        countryRepository = null
        countryViewModelFactory = null
    }
}