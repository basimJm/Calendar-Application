package com.mstart.calendarapplication.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mstart.calendarapplication.domin.model.room.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

}

