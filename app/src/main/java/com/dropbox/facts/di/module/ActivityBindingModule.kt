package com.dropbox.facts.di.module

import com.dropbox.facts.screen.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity

}