package eu.example.jetnotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// Extends Application class, which is the top level android class, to we are extended the entire eco system
// with @HiltAndroidApp we give hilt access to the entire application -
// In other words Hilt can now supply dependencies to the any part of the App
// we must also register in the manifest file to give it access
// and finally use @AndroidEntryPoint in the MainFunction to have an entry point
@HiltAndroidApp
class NoteApplication : Application() {
}