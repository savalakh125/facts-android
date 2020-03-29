package com.dropbox.facts.di.module

import com.dropbox.facts.data.APIRemoteRepository
import com.dropbox.facts.domain.repository.RemoteRepository
import com.dropbox.facts.domain.thread.ObserverThread
import com.dropbox.facts.thread.UIThread
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    internal abstract fun bindObserverThread(uiThread: UIThread): ObserverThread

    @Binds
    @Singleton
    internal abstract fun bindRemoteRepository(remoteRepository: APIRemoteRepository) : RemoteRepository

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        internal fun provideExecutor(): Executor {
            return Executors.newFixedThreadPool(5)
        }
    }

}