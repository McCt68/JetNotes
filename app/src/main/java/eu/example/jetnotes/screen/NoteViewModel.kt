package eu.example.jetnotes.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note

// We can call this class, when we want to get the list of notes
// or add or remove a note.
// inherits from class ViewModel.
// Notice the noteList is private, so it can only be used inside this class -
// But we can access the noteList via using methods from this class
class NoteViewModel: ViewModel() {

	private var noteList = mutableStateListOf<Note>()

	// add all notes from our dummy source to the list of notes ( notesList )
	init {
		noteList.addAll(NotesDataSource().loadNotes())
	}

	fun addNote(note: Note) {
		noteList.add(note)
	}

	fun removeNote(note: Note){
		noteList.remove(note)
	}

	fun getAllNotes(): List<Note>{
		return noteList
	}
}