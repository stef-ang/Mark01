package com.stef_ang.mark01.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeLoadingVM: ViewModel() {

    private var data1IsLoading = false
    private var data2IsLoading = false
    private var data3IsLoading = false

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() =  _isLoading

    fun setData1(isLoading: Boolean) {
        data1IsLoading = isLoading
        updateLoading()
    }

    fun setData2(isLoading: Boolean) {
        data2IsLoading = isLoading
        updateLoading()
    }

    fun setData3(isLoading: Boolean) {
        data3IsLoading = isLoading
        updateLoading()
    }

    private fun updateLoading() {
        _isLoading.value = data1IsLoading && data2IsLoading && data3IsLoading
    }
}