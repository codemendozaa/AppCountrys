package com.example.appmvil.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmvil.data.OperationCallback
import com.example.appmvil.model.Country
import com.example.appmvil.model.CountryRepository

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _countrys = MutableLiveData<List<Country>>().apply { value = emptyList() }
    val countrys: LiveData<List<Country>> = _countrys

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadCountrys()" on constructor. Also, if you rotate the screen, the service will not be called.

    init {
        //loadCountrys()
    }
     */

    fun loadCountrys() {
        _isViewLoading.value = true
        repository.fetchCountrys(object : OperationCallback<Country> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error!!
            }

            override fun onSuccess(data: List<Country>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true

                } else {
                    _countrys.value = data
                }
            }
        })
    }
}