package com.example.appmvil.data

import com.example.appmvil.model.Country
import com.example.appmvil.model.CountryDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRemoteDataSource(apiClient: ApiClient) : CountryDataSource {
    private var call: Call<CountryResponse>? = null
    private val service = apiClient.build()

    override fun retrieveCountrys(callback: OperationCallback<Country>) {

         val credential = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJlcml4LTAyQGhvdG1haWwuY29tIiwiYXBpX3Rva2VuIjoieEcyVzJFM0FMSFZTME9RMTRDQ3dIQ1ozcmtSQXlxci1VekMwWGVxSmVnMWstN2hGYjU5Wl9zWnZTYmU4TEI0bW9wNCJ9LCJleHAiOjE2NTMzNjExMDR9.tVR9fW5qE9DAVv_B1F_uz32MqsCiKBGyk-d2RZLYaSI"

        call = service?.countrys(credential)
        call?.enqueue(object : Callback<CountryResponse> {
            override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<CountryResponse>,
                response: Response<CountryResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful && (it.isSuccess())) {
                        callback.onSuccess(it.data)
                    } else {
                        callback.onError(it.data.toString())
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