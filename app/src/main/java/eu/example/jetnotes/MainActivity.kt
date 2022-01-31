package eu.example.jetnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note
import eu.example.jetnotes.screen.NoteScreen
import eu.example.jetnotes.screen.NoteViewModel
import eu.example.jetnotes.ui.theme.JetNotesTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			JetNotesTheme {
				// A surface container using the 'background' color from the theme
				Surface(color = MaterialTheme.colors.background) {

					val noteViewModel: NoteViewModel by viewModels()
					
					NotesApp(noteViewModel = noteViewModel)
				}
			}
		}
	}
}

@ExperimentalComposeUiApi
@Composable
fun NotesApp(
	noteViewModel: NoteViewModel = viewModel()) {

	// get all notes from NoteViewModel
	val notesList = noteViewModel.getAllNotes()

	// Calling NoteScreen
	NoteScreen(

		notes = notesList,

		// call .removeNote method from our NoteViewModel, on our noteViewModel object
		onRemoveNote = {
			noteViewModel.removeNote(it)
		},

		// call .addNote method from our NoteViewModel, on our noteViewModel object
		onAddNote = { noteViewModel.addNote(it) })


}

