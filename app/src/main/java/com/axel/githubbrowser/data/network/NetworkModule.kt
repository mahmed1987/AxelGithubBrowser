package com.axel.githubbrowser.data.network

import com.axel.githubbrowser.BuildConfig
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
object NetworkModule {
  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    val httpClient = OkHttpClient.Builder()
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    val logger =
      httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      }
    httpClient.addInterceptor(logger)
    val retroBuilder =
      Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
    retroBuilder.client(
      httpClient.readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS).build()
    )
    return retroBuilder.build()
  }

}