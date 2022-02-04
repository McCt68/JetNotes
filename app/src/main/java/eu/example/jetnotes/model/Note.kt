package eu.example.jetnotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

// Used to create objects of each Note, each one will have a unique id
// I think Entity is just a smart word for a table in a database ??
@Entity(tableName = "notes_tbl") // creating a notes table with help from room library
data class Note(

	@PrimaryKey
	val id: UUID = UUID.randomUUID(), // creates a random unique ID

	@ColumnInfo(name = "note_title")
	val title: String,

	@ColumnInfo(name = "note_description")
	val description: String,

	@ColumnInfo(name = "note_entry_data")
	val entryDate: Date = Date.from(Instant.now())
)
