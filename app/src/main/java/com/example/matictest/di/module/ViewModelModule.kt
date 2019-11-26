package com.example.matictest.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.example.matictest.di.ViewModelKey
import com.example.matictest.factory.ViewModelFactory
import com.example.matictest.ui.viewmodel.GithubListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubListViewModel::class)
    protected abstract fun githubListViewModel(githubListViewModel: GithubListViewModel): ViewModel
}