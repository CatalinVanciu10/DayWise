package org.daywise.com.calendar

import kotlinx.coroutines.withContext

import android.content.ContentResolver
import android.content.Context
import android.provider.CalendarContract
import kotlinx.coroutines.Dispatchers

class AndroidCalendarManager(private val context: Context) : CalendarManager {
    override suspend fun getUpcomingEvents(): List<CalendarEvent> = withContext(Dispatchers.IO) {
        val events = mutableListOf<CalendarEvent>()

        val projection = arrayOf(
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND,
            CalendarContract.Events.EVENT_LOCATION
        )

        val now = System.currentTimeMillis()
        val selection = "${CalendarContract.Events.DTSTART} >= ?"
        val selectionArgs = arrayOf(now.toString())

        val resolver: ContentResolver = context.contentResolver
        val cursor = resolver.query(
            CalendarContract.Events.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            "${CalendarContract.Events.DTSTART} ASC"
        )

        cursor?.use {
            val idIdx = it.getColumnIndex(CalendarContract.Events._ID)
            val titleIdx = it.getColumnIndex(CalendarContract.Events.TITLE)
            val startIdx = it.getColumnIndex(CalendarContract.Events.DTSTART)
            val endIdx = it.getColumnIndex(CalendarContract.Events.DTEND)
            val locIdx = it.getColumnIndex(CalendarContract.Events.EVENT_LOCATION)

            while (it.moveToNext()) {
                events.add(
                    CalendarEvent(
                        id = it.getString(idIdx),
                        title = it.getString(titleIdx),
                        startTime = it.getLong(startIdx),
                        endTime = it.getLong(endIdx),
                        location = it.getString(locIdx)
                    )
                )
            }
        }
        events
    }
}
