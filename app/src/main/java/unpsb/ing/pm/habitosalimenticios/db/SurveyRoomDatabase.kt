package unpsb.ing.pm.habitosalimenticios.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import unpsb.ing.pm.habitosalimenticios.db.dao.SurveyDao
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Survey::class), version = 1, exportSchema = false)
public abstract class SurveyRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): SurveyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SurveyRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SurveyRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SurveyRoomDatabase::class.java,
                    "survey_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}