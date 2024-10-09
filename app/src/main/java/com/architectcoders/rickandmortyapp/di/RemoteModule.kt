package com.architectcoders.rickandmortyapp.di

import com.architectcoders.data.datasource.RemoteDataSource
import com.architectcoders.rickandmortyapp.data.remote.RickAndMortyService
import com.architectcoders.rickandmortyapp.data.remote.datasource.RemoteDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://rickandmortyapi.com/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    @Named("OkHTTPClient")
    fun provideOkHttpClient() = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("OkHTTPClient") okHttpClient: OkHttpClient,
        @ApiUrl apiUrl: String,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(apiUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyService(retrofit: Retrofit): RickAndMortyService =
        retrofit.create(RickAndMortyService::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRemoteDataModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDatasource: RemoteDataSourceImpl): RemoteDataSource
}