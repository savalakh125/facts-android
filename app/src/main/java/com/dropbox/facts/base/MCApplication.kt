package com.dropbox.facts.base

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MCApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

//        DaggerApplicationComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }
}