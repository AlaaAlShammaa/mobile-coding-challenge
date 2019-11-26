package com.example.matictest.di.module

import android.arch.lifecycle.ViewModelProvider
import com.example.matictest.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}