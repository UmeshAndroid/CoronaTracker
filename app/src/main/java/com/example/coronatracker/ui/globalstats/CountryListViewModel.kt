package com.example.coronatracker.ui.globalstats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coronatracker.data.CountryListRepository
import com.example.coronatracker.data.model.CountryListStats
import com.example.coronatracker.utils.APIException
import com.example.coronatracker.utils.NoInternetException
import com.example.coronatracker.utils.State
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor( private val repository: CountryListRepository) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<State<CountryListStats>>()
    val countryListStat : LiveData<State<CountryListStats>>
    get() = _mutableLiveData

    fun getCountryWiseStat(){
        _mutableLiveData.postValue(State.loading())
        viewModelScope.launch {
            try {
                _mutableLiveData.postValue(State.success(repository.getCountryWiseStats()))
            }catch (e:APIException){
                _mutableLiveData.postValue(State.error(e.message?:""))
            }catch (e:NoInternetException){
                _mutableLiveData.postValue(State.error(e.message?:""))
            }
        }
    }
}