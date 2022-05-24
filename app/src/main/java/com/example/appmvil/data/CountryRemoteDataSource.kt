package com.example.appmvil.data

import com.example.appmvil.model.Country
import com.example.appmvil.model.CountryDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRemoteDataSource(apiClient: ApiClient) : CountryDataSource {
    private var call: Call <List<Country>>? = null
    private val service = apiClient.build()

    override fun retrieveCountrys(callback: OperationCallback<Country>) {

         val credential = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJlcml4LTAyQGhvdG1haWwuY29tIiwiYXBpX3Rva2VuIjoieEcyVzJFM0FMSFZTME9RMTRDQ3dIQ1ozcmtSQXlxci1VekMwWGVxSmVnMWstN2hGYjU5Wl9zWnZTYmU4TEI0bW9wNCJ9LCJleHAiOjE2NTM0NDc4NjR9.TgSCteCr_S482cvbag49WsSyXU8PrreSce3MrVCBvaY"

        call = service?.countrys(credential)
        call?.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call <List<Country>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<List<Country>>,
                response: Response<List<Country>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError(it.toString())
                    }
                }
            }

        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}


