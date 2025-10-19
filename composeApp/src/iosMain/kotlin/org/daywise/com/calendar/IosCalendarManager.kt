package org.daywise.com.calendar

import platform.EventKit.EKEvent
import platform.EventKit.EKEventStore
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

class IosCalendarManager : CalendarManager {

    private val eventStore = EKEventStore()

    override suspend fun getUpcomingEvents(): List<CalendarEvent> {
        val calendar = NSCalendar.currentCalendar
        val now = NSDate()
        val oneWeek = calendar.dateByAddingUnit(NSCalendarUnitDay, 7, now, 0)!!

        val predicate = eventStore.predicateForEventsWithStartDate(
            startDate = now,
            endDate = oneWeek,
            calendars = eventStore.calendarsForEntityType(EKEntityTypeEvent)
        )

        val events = eventStore.eventsMatchingPredicate(predicate) as? List<EKEvent> ?: emptyList()
        return events.map {
            CalendarEvent(
                id = it.eventIdentifier ?: "",
                title = it.title ?: "(Fără titlu)",
                startTime = it.startDate?.timeIntervalSince1970()?.toLong()?.times(1000) ?: 0,
                endTime = it.endDate?.timeIntervalSince1970()?.toLong()?.times(1000) ?: 0,
                location = it.location
            )
        }
    }
}
