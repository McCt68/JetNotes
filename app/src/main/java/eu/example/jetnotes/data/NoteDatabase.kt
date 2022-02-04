package eu.example.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eu.example.jetnotes.model.Note
import eu.example.jetnotes.util.DateConverter
import eu.example.jetnotes.util.UUIDConverter

// we can not create instances of this abstract class
// abstract class which inherits from RoomDatabase ( extend )
@Database(entities = [Note::class], version = 1, exportSchema = false) // using the Note table ( entity )
@TypeConverters(DateConverter::class, UUIDConverter::class) // passing typeConverters
abstract class NoteDatabase: RoomDatabase() {

	abstract fun noteDao(): NoteDatabaseDao
}