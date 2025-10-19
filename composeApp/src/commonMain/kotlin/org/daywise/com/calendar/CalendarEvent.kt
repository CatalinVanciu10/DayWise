package org.daywise.com.calendar

data class CalendarEvent(
    val id: String,
    val title: String,
    val startTime: Long,
    val endTime: Long,
    val location: String?
)

interface CalendarManager {
    suspend fun getUpcomingEvents(): List<CalendarEvent>
}
