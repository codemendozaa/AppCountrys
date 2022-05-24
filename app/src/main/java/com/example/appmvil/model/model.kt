package com.example.appmvil.model

import java.io.Serializable

data class Country(val country_name: String,
                   val country_short_name: String,
                   val country_phone_code: Int) : Serializable