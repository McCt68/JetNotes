package eu.example.jetnotes.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.example.jetnotes.R
import eu.example.jetnotes.components.NoteButton
import eu.example.jetnotes.components.NoteInputText
import eu.example.jetnotes.data.NotesDataSource
import eu.example.jetnotes.model.Note
import java.time.format.DateTimeFormatter

// Using this function in MainActivity
// Parameters
@Composable
fun NoteScreen(
	notes: List<Note>,
	onAddNote: (Note) -> Unit,
	onRemoveNote: (Note) -> Unit){

	var title  by remember {
		mutableStateOf("")
	}

	var description  by remember {
		mutableStateOf("")
	}

	Column(modifier = Modifier
		.padding(6.dp)) {
		TopAppBar(
			backgroundColor = Color(0xFFDADFE3),
			// Here we get the title string from res/values/strings
			title = {
				Text(text = stringResource(id = R.string.app_name))
					}, actions = {
				Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
					})

		// Content
		Column(modifier = Modifier
			.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally) {

			NoteInputText(modifier = Modifier
				.padding(top = 9.dp, bottom = 8.dp),
				text = title,
				label = "Title",
				onTextChange = {
					if (it.all { char ->
							char.isLetter() || char.isWhitespace()
						}) title = it
				})

			NoteInputText(modifier = Modifier
				.padding(top = 9.dp, bottom = 8.dp),
				text = description,
				label = "Add note",
				onTextChange = {
					if (it.all { char ->
							char.isLetter() || char.isWhitespace()
						}) title = it
				})
			
			NoteButton(text = "save",
				// Saving when press button
				onClick = {
					if (title.isNotEmpty() && description.isNotEmpty()) {
						//save/add to the list
						title = ""
						description = ""
					}
				})

		}
		Divider(modifier = Modifier.padding(10.dp))

		// scrollable list of notes, from the parameter we called the NoteScreen function with
		// It will show each note from the list
		LazyColumn {
			items(notes){note ->
				NoteRow(note = note, onNoteClicked = {})
			}
		}		
	}
}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit) {
	Surface(
		modifier
			.padding(4.dp)
			.clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
			.fillMaxWidth(),
		color = Color(0xFFDFE6EB),
		elevation = 6.dp) {
		Column(
			modifier
				.clickable { }
				.padding(horizontal = 14.dp, vertical = 6.dp), 
			horizontalAlignment = Alignment.Start) {

			Text(
				text = note.title,
				style = MaterialTheme.typography.subtitle2)

			Text(
				text = note.description,
				style = MaterialTheme.typography.subtitle1)

			Text(
				text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
				style = MaterialTheme.typography.caption)
			
		}
		
	}
	
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
	NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}