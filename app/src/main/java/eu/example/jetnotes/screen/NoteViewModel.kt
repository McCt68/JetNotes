package eu.example.jetnotes.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note
import eu.example.jetnotes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

// @Inject the repository into the constructor of this class

// We can call this class, when we want to get the list of notes
// or add or remove a note.
// inherits from class ViewModel.
// Notice the noteList is private, so it can only be used inside this class -
// But we can access the noteList via using methods from this class
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

	// Not using this anymore. Since mutableState don't work well with room
	// private var noteList = mutableStateListOf<Note>()

	private val _notesList = MutableStateFlow<List<Note>>(emptyList())
	val noteList = _notesList.asStateFlow()

	// add all notes from our dummy source to the list of notes ( notesList )
	init {
		// noteList.addAll(NotesDataSource().loadNotes())

		viewModelScope.launch(Dispatchers.IO) {
			repository.getAllNotes().distinctUntilChanged()
				.collect { listOfNotes ->
					if (listOfNotes.isNullOrEmpty()) {
						Log.d("Empty", ": Empty list ")
					} else {
						_notesList.value = listOfNotes
					}
				}
		}
	}

	// this is my own way od returning instead of using = ( video 142)
	fun addNote(note: Note): Job {
		return viewModelScope.launch { repository.addNote(note = note) }
	}

	// here return made with =
	fun removeNote(note: Note) = viewModelScope.launch{repository.deleteNote(note)}

	fun updateNote(note: Note) = viewModelScope.launch{repository.updateNote(note)}

}