package com.victorizzie.android.apps.jsonplaceholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor

@ObsoleteCoroutinesApi
abstract class BaseViewModel : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackBar = MutableLiveData<Int>()
    val snackBar: LiveData<Int> = _snackBar

    fun setDataLoading(isDataLoading: Boolean) {
        _dataLoading.value = isDataLoading
    }

    fun showSnackBar(id: Int) {
        _snackBar.value = id
    }

    fun actor(element: Int, action: suspend (Int) -> Unit) {
        val event = viewModelScope.actor<Int> {
            for (event in channel) {
                action(event)
            }
        }
        event.offer(element)
    }
}