package com.example.topa_dictionary.feature_dictionary.di

import com.example.topa_dictionary.feature_dictionary.data.remote.DictionaryApi
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun logingInterceptror(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(logingInterceptror())
            .build()

    }

    @Provides
    @Singleton
    fun provideDictionaryApi(retrofit: Retrofit):DictionaryApi{
        return retrofit.create()
    }

}