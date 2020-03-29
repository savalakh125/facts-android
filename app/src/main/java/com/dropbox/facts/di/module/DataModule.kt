package com.dropbox.facts.di.module

import com.dropbox.facts.data.client.DropboxAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Module to satisfy all dependencies in data layer
 */
@Module
class DataModule {

        @Provides
        @Singleton
        internal fun provideAPI(): DropboxAPI {
            /*
                The base url is hardcoded here but in case of multiple
                environments, it would be provided based on the
                respective build variant.
             */
            
            val clientBuilder = OkHttpClient.Builder()

            //Logging
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://dl.dropboxusercontent.com")
                    .client(clientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(DropboxAPI::class.java)
        }

}