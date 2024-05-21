package unpsb.ing.pm.habitosalimenticios.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey

@Dao
interface SurveyDao {

    @Query("SELECT * FROM survey_table ORDER BY food ASC")
    fun getAlphabetizedSurveys(): Flow<List<Survey>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(survey: Survey)

    @Query("DELETE FROM survey_table")
    suspend fun deleteAll()
}