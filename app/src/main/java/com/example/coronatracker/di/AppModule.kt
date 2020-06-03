package com.example.coronatracker.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {

    @Provides
    fun provideContext(application: Application) = application.applicationContext

}