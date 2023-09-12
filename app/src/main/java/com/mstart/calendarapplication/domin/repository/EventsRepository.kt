package com.mstart.calendarapplication.domin.repository

import com.mstart.calendarapplication.data.localdatasource.EventDao
import com.mstart.calendarapplication.domin.model.room.EventEntity
import javax.inject.Inject

class EventsRepository @Inject constructor(private val eventDao: EventDao) {

    suspend fun fetchAllEvents(): List<EventEntity> {
        return eventDao.getAllEvents()
    }

    suspend fun insert(event: EventEntity) {
        eventDao.insert(event)
    }

    suspend fun deleteEvent(event: EventEntity) {
        eventDao.delete(event)
    }

    suspend fun updateEvent(event: EventEntity) {
        eventDao.updateEvent(event)
    }

}