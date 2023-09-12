package com.mstart.calendarapplication.domin.usecase

import com.mstart.calendarapplication.domin.model.response.DateResponseRemoteModel
import com.mstart.calendarapplication.domin.repository.CalendarRepository
import javax.inject.Inject

class CalendarUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke(gregorianDate: String): DateResponseRemoteModel? {
        return calendarRepository.getData(gregorianDate)
    }
}