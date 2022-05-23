package com.example.appmvil.model

import com.example.appmvil.data.OperationCallback

class CountryRepository (private val countryDataSource: CountryDataSource) {

    fun fetchCountrys(callback: OperationCallback<Country>) {
        countryDataSource.retrieveCountrys(callback)
    }

    fun cancel() {
        countryDataSource.cancel()
    }
}