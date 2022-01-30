package eu.example.jetnotes.data

import eu.example.jetnotes.model.Note

// We Instantiate this dataclass when we call NotesScreen in MainActivity
// Kinda simulation we are loading the list of notes from here
class NotesDataSource {

	// returns a list of our Notes
	fun loadNotes(): List<Note> {
		return listOf(
			Note(title = "A good day", description = "We went on a vacation by the lake"),
			Note(title = "Android Compose", description = "Working on Android Compose course today"),
			Note(title = "Keep at it...", description = "Sometimes things just happen"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family today"),
			Note(title = "A movie day", description = "Watching a movie with family")
		)
	}
}