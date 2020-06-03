package com.example.coronatracker.di

import com.example.coronatracker.ui.globalstats.GlobalStatsFragment
import com.example.coronatracker.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributGlobalStatsfragment(): GlobalStatsFragment
}