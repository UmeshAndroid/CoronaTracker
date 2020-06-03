package com.example.coronatracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coronatracker.data.HomeRepository
import com.example.coronatracker.data.db.WorldStat
import com.example.coronatracker.utils.APIException
import com.example.coronatracker.utils.NoInternetException
import com.example.coronatracker.utils.State
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<State<WorldStat>>()
    val worldStat: LiveData<State<WorldStat>>
        get() = _mutableLiveData

    fun getWorldStat() {
        _mutableLiveData.postValue(State.loading())
        viewModelScope.launch {
            try {
                val data = homeRepository.getWorldStat()
                _mutableLiveData.postValue(State.success(data))
            } catch (e: APIException) {
                _mutableLiveData.postValue(State.error(e.message!!))
            } catch (e: NoInternetException) {
                _mutableLiveData.postValue(State.error(e.message!!))
            }
        }
    }
}