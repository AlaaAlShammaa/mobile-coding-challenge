package com.example.matictest

import android.app.Activity
import android.app.Application

import com.example.matictest.di.component.DaggerAppComponent

import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid

class AppController : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}

