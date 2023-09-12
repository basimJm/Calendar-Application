package com.mstart.calendarapplication.data.localdatasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mstart.calendarapplication.domin.model.room.EventEntity

@Dao
interface EventDao {
    @Insert
    suspend fun insert(event: EventEntity)

    @Delete
    suspend fun delete(event: EventEntity)

    @Update
    suspend fun updateEvent(event: EventEntity)

    @Query("SELECT * FROM event_table")
    suspend fun getAllEvents(): List<EventEntity>

}
