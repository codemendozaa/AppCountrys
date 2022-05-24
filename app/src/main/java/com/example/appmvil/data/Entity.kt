package com.example.appmvil.data

import com.example.appmvil.model.Country



data class CountryResponse(val data: List<Country>?){
    fun isSuccess(): Boolean = (data?.isNotEmpty() == true)
}
