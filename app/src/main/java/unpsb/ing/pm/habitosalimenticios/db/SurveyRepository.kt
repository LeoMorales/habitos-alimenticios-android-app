package unpsb.ing.pm.habitosalimenticios.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import unpsb.ing.pm.habitosalimenticios.db.dao.SurveyDao
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SurveyRepository(private val surveyDao: SurveyDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allSurveys: Flow<List<Survey>> = surveyDao.getAlphabetizedSurveys()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(survey: Survey) {
        surveyDao.insert(survey)
    }
}