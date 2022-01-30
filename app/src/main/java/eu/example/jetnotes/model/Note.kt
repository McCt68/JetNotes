package eu.example.jetnotes.model

import java.time.LocalDateTime
import java.util.*

// Used to create objects of each Note, each one will have a unique id
data class Note(
	val id: UUID = UUID.randomUUID(), // creates a random unique ID
	val title: String,
	val description: String,
	val entryDate: LocalDateTime = LocalDateTime.now()
)
