package com.example.appmvil.model

import com.example.appmvil.data.OperationCallback

interface CountryDataSource {
    fun retrieveCountrys(callback: OperationCallback<Country>)
    fun cancel()
}