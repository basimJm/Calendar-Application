package com.mstart.calendarapplication.domin.usecase

import com.mstart.calendarapplication.domin.model.room.EventEntity
import com.mstart.calendarapplication.domin.repository.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventsRepository: EventsRepository) {

    suspend operator fun invoke(): List<EventEntity> {
        return eventsRepository.fetchAllEvents().sortedBy { it.dateHijri }
    }
}