package com.example.matictest.di.component

import android.app.Application

import com.example.matictest.AppController
import com.example.matictest.di.module.ActivityModule
import com.example.matictest.di.module.ApiModule
import com.example.matictest.di.module.ViewModelModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [ApiModule::class,
    ViewModelModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    fun inject(appController: AppController)
}
