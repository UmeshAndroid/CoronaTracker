package com.example.coronatracker.ui.globalstats

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronatracker.R
import com.example.coronatracker.databinding.FragmentGlobalStatsBinding
import com.example.coronatracker.databinding.FragmentHomeBinding
import com.example.coronatracker.utils.State
import com.example.coronatracker.utils.hide
import com.example.coronatracker.utils.show
import com.example.coronatracker.utils.snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class GlobalStatsFragment : Fragment() {
    private lateinit var binding : FragmentGlobalStatsBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: CountryListAdapter


    val countryListViewModel: CountryListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CountryListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_global_stats,container,false)
        binding.lifecycleOwner = this
        countryListViewModel.getCountryWiseStat()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpList()
        subscribeUI()
    }

    private fun setUpList() {
        binding.rvCountries.layoutManager = LinearLayoutManager(context)
        binding.rvCountries.itemAnimator = DefaultItemAnimator()
        adapter = CountryListAdapter()
        binding.rvCountries.adapter = adapter
    }

    private fun subscribeUI() {
        countryListViewModel.countryListStat.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressBar.show()
                }

                is State.Success -> {
                    binding.progressBar.hide()
                    Log.v("DATA" , state.data.toString())
                    adapter.setCountryList(state.data.countryStats.filter { countryStats -> countryStats.country.isNullOrEmpty().not() })
                }

                is State.Error -> {
                    binding.progressBar.hide()
                    binding.root.snackbar(state.message)
                }
            }
        })
    }

}