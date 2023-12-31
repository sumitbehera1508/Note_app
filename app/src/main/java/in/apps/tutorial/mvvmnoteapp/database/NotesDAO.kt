package `in`.apps.tutorial.mvvmnoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.apps.tutorial.mvvmnoteapp.models.Notes

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("SELECT * FROM notes_table ORDER BY date DESC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("UPDATE notes_table SET title = :title, note = :note WHERE id = :id")
    suspend fun update(id: Int?,title : String? , note : String?)
}