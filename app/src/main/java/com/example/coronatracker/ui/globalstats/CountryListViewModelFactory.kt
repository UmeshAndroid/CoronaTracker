package com.example.coronatracker.ui.globalstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronatracker.data.CountryListRepository

@Suppress("UNCHECKED_CAST")
class CountryListViewModelFactory( private val repository: CountryListRepository
) : ViewModelProvider.NewInstanceFactory()  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryListViewModel(repository) as T
    }
}