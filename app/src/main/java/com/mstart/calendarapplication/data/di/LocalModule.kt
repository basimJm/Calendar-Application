package com.mstart.calendarapplication.data.di

import android.content.Context
import androidx.room.Room
import com.mstart.calendarapplication.R
import com.mstart.calendarapplication.data.localdatasource.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(
            context, EventDatabase::class.java,
            context.getString(R.string.event_database)
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(database: EventDatabase) = database.eventDao()


}