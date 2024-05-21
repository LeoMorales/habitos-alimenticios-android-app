package unpsb.ing.pm.habitosalimenticios.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import unpsb.ing.pm.habitosalimenticios.db.dao.SurveyDao
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    entities = arrayOf(Survey::class),
    version = SurveyRoomDatabase.LATEST_VERSION,
    autoMigrations = [
        AutoMigration (
            from = 1,
            to = 2,
            spec = SurveyRoomDatabase.MyExampleAutoMigration::class
        )
    ],
    exportSchema = true
)
abstract class SurveyRoomDatabase : RoomDatabase() {

    abstract fun surveyDao(): SurveyDao

    private class SurveyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var surveyDao = database.surveyDao()

                    // Delete all content here.
                    surveyDao.deleteAll()

                }
            }
        }
    }
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
                )
                    .addCallback(SurveyDatabaseCallback(scope))
                    .build();
                INSTANCE = instance
                // return instance
                instance
            }
        }

        const val LATEST_VERSION = 2
    }

    @RenameTable(fromTableName = "survey_table", toTableName = "Survey")
    class MyExampleAutoMigration : AutoMigrationSpec {
        @Override
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            // Invoked once auto migration is done
        }
    }
}