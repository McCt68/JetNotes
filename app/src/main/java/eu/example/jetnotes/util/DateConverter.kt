package eu.example.jetnotes.util

import androidx.room.TypeConverter
import java.util.*

// Used to convert Date from Note class to a format we can use with Room

class DateConverter {

	@TypeConverter
	fun timeStampFromDate(date: Date): Long {
		return date.time
	}

	@TypeConverter
	fun dateTimestamp(timestamp: Long): Date? {
		return Date(timestamp)
	}
}