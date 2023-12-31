package `in`.apps.tutorial.mvvmnoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import `in`.apps.tutorial.mvvmnoteapp.models.Notes
import `in`.apps.tutorial.mvvmnoteapp.util.DATABASE_NAME

@Database(entities = arrayOf(Notes::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao() : NotesDAO

    companion object {
        @Volatile

        private var INSTANCE : NoteDatabase? = null


        fun getDatabase(context: Context) : NoteDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}