package com.example.matictest.di.module


import com.example.matictest.ui.activity.GithubListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGithubListActivity(): GithubListActivity
}