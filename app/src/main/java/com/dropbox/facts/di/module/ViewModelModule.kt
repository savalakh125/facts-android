package com.dropbox.facts.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dropbox.facts.di.DViewModelFactory
import com.dropbox.facts.screen.home.HomeViewModel
import com.marvel.comics.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindMarvelCharactersMasterViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DViewModelFactory): ViewModelProvider.Factory


}