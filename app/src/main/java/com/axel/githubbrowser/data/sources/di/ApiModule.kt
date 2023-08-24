package com.axel.githubbrowser.data.sources.di

import com.axel.githubbrowser.BuildConfig
import com.axel.githubbrowser.data.sources.users.UsersWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Provides
  @Singleton
  fun provideUserServices(retrofit: Retrofit): UsersWebServices {
    return retrofit.create(UsersWebServices::class.java)
  }
}