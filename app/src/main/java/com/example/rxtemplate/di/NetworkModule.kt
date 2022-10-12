package com.example.rxtemplate.di

import com.example.rxtemplate.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class NetworkModule {
    @Module
    @InstallIn(SingletonComponent::class)
    class NetworkModule {



        @Singleton
        @Provides
        fun provideRetrofit() = createRetrofit(GsonConverterFactory.create())


        private fun createRetrofit(converter: Converter.Factory): Retrofit {
            val interceptor = HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            val authInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder().apply {
                    addHeader("Content-Type", "application/json")
                }.build()

                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(authInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

    }
}