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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note
import eu.example.jetnotes.screen.NoteScreen
import eu.example.jetnotes.screen.NoteViewModel
import eu.example.jetnotes.ui.theme.JetNotesTheme

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			JetNotesTheme {
				// A surface container using the 'background' color from the theme
				Surface(color = MaterialTheme.colors.background) {

					// Instantiating our viewModel
					val noteViewModel: NoteViewModel by viewModels()

					// Another way to instantiate our viewModel
					// val noteViewModel = viewModel<NoteViewModel>()

					// calling notesApp and giving our newly created noteViewModel object as argument
					NotesApp(noteViewModel = noteViewModel)
				}
			}
		}
	}
}

@ExperimentalComposeUiApi
@Composable
// The UI only requires a viewModel -
// The viewModel has access to repository, which has access to DAO
// function takes a viewModel of type NoteViewModel as parameter
fun NotesApp(noteViewModel: NoteViewModel) {

	// Using .noteList method on our newly noteViewModel object -
	// to get all notes from NoteViewModel as a flow
	val notesList = noteViewModel.noteList.collectAsState().value

	// Calling NoteScreen supplying 3 arguments -
	// A list of notes - a lambda function to remove note - a lambda function to add a note
	NoteScreen(
		notes = notesList,

		// call .removeNote method from our NoteViewModel, on our noteViewModel object
		onRemoveNote = {
			noteViewModel.removeNote(it)
		},

		// call .addNote method from our NoteViewModel, on our noteViewModel object
		onAddNote = { noteViewModel.addNote(it) })


}

