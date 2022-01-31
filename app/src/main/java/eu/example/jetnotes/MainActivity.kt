package eu.example.jetnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note
import eu.example.jetnotes.screen.NoteScreen
import eu.example.jetnotes.ui.theme.JetNotesTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			JetNotesTheme {
				// A surface container using the 'background' color from the theme
				Surface(color = MaterialTheme.colors.background) {

					val notes = remember {
						mutableStateListOf<Note>()
					}

					// Calling NoteScreen
					NoteScreen(

						notes = notes,

						//
						onRemoveNote = {
									   notes.remove(it)
						},

						// create a new Note object, and add it to the mutableStateList of notes
						onAddNote = {
									notes.add(it)
						})
				}
			}
		}
	}
}

