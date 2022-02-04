package eu.example.jetnotes.data

import androidx.room.*
import eu.example.jetnotes.model.Note
import kotlinx.coroutines.flow.Flow

// In here we are also Using coroutines
// with the suspend keyword, we allow the function to run inside a coroutine


// The DAO is connected directly to our SQLite DataBase
// So who ever wants to do something in the database, read, write, delete or so on -
// would have to have a DAO instance (Instantiate a DAO object) in order to do any operations -
// with the database.
@Dao
interface NoteDatabaseDao {

	// Select all from out notes_tbl entity, and return a list of it when we call getNotes()
	@Query("SELECT * from notes_tbl")
	fun getNotes():
			Flow<List<Note>>

	@Query("SELECT * from notes_tbl where id =:id")
	suspend fun getNoteById(id: String): Note

	// fun had another name in video
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertNote(note: Note)

	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateNote(note: Note)

	@Query("DELETE from notes_tbl")
	suspend fun deleteAllNotes()

	@Delete
	suspend fun deleteOneNote(note: Note)
}
