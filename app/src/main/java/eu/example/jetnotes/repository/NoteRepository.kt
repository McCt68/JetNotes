package eu.example.jetnotes.repository

import eu.example.jetnotes.data.NoteDatabaseDao
import eu.example.jetnotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// This is like a midle man / waiter in restaurant
// I think this handles communications with the Dao to get access to room database operations
// The viewModel should access this class, and use methods from it when it needs to interact with the database

// This class must have access to NoteDatabaseDao -
// we do that with @Inject
// we are using hilt to inject the dependency in the constructor
// All this do is mirroring the Dao
class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao){

	suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note = note)

	suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)

	suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteOneNote(note)

	suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()

	fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)
		.conflate()
}