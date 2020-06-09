package com.example.coronatracker.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coronatracker.R
import com.example.coronatracker.data.db.WorldStat
import com.example.coronatracker.databinding.FragmentHomeBinding
import com.example.coronatracker.utils.State
import com.example.coronatracker.utils.hide
import com.example.coronatracker.utils.show
import com.example.coronatracker.utils.snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_total_cases.view.*
import kotlinx.android.synthetic.main.layout_total_deaths.view.*
import kotlinx.android.synthetic.main.layout_total_recovered.view.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentHomeBinding
    val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this
        binding.mainHomeViewModel = homeViewModel

        homeViewModel.getWorldStat()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        homeViewModel.worldStat.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressBar.show()
                }

                is State.Success -> {
                    binding.progressBar.hide()
                    bindView(state.data)
                }

                is State.Error -> {
                    binding.progressBar.hide()
                    binding.root.snackbar(state.message)
                }
            }
        })
    }

    private fun bindView(data: WorldStat) {
        binding.apply {
            cardviewTotalCases.show()
            cardviewTotalDeath.show()
            cardviewTotalRecovered.show()
        }
        data.apply {
            binding.cardviewTotalCases.tvTotalCases.text = totalCases
            binding.cardviewTotalDeath.tvTotalDeaths.text = totalDeaths
            binding.cardviewTotalRecovered.tvTotalRecovered.text = totalRecovered
        }
    }
}
