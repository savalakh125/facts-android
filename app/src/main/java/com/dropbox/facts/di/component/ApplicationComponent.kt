package com.dropbox.facts.di.component

import com.dropbox.facts.base.DApplication
import com.dropbox.facts.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                        ActivityBindingModule::class,
                        DataModule::class,
                        DomainModule::class,
                        AppModule::class,
                        ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<DApplication> {

    override fun inject(application: DApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DApplication): Builder

        fun build(): ApplicationComponent
    }
}