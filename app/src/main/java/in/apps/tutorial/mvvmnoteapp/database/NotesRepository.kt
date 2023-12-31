package `in`.apps.tutorial.mvvmnoteapp.database

import androidx.lifecycle.LiveData
import `in`.apps.tutorial.mvvmnoteapp.models.Notes

class NotesRepository (private val notesDAO: NotesDAO){
    val allNotes :LiveData<List<Notes>> = notesDAO.getAllNotes()

    suspend fun insert(notes: Notes){
        notesDAO.insert(notes)
    }

    suspend fun delete(notes: Notes){
        notesDAO.delete(notes)
    }

    suspend fun update(notes: Notes){
        notesDAO.update(notes.id,notes.title,notes.note)
    }
}