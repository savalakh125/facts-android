package com.dropbox.facts.di.module

import android.content.Context
import com.dropbox.facts.base.DApplication
import com.dropbox.facts.resources.DStringResources
import com.dropbox.facts.resources.StringResources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: DApplication): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideStringResources(context: Context): StringResources {
        return DStringResources(context.resources)
    }

}