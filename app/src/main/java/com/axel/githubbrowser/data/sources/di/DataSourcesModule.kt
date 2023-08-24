package com.axel.githubbrowser.data.sources.di

import com.axel.githubbrowser.BuildConfig
import com.axel.githubbrowser.data.sources.users.UsersDataSource
import com.axel.githubbrowser.data.sources.users.UsersDataSourceImpl
import dagger.Binds
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
abstract class DataSourcesModule {
  @Binds
  abstract fun bindUserRepository(impl: UsersDataSourceImpl): UsersDataSource
}