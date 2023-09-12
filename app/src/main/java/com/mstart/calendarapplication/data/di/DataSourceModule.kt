package com.mstart.calendarapplication.data.di

import com.mstart.calendarapplication.data.datasource.ApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(retrofit: Retrofit): ApiDataSource {
        return retrofit.create()
    }
}