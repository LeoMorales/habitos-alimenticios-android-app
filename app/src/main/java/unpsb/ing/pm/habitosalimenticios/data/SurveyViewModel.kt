package unpsb.ing.pm.habitosalimenticios.data

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import unpsb.ing.pm.habitosalimenticios.db.SurveyRepository
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey
import kotlin.random.Random



class SurveyViewModel(private val repository: SurveyRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Survey>> = repository.allSurveys.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(survey: Survey) = viewModelScope.launch {
        repository.insert(survey)
    }

    private val _currentPorcion = MutableLiveData<String>()
    val currentPorcion : LiveData<String>
        get() = _currentPorcion

    fun cambiarPorcion(newPorcion: String){
        _currentPorcion.value = newPorcion
    }

    // List of colors
    private val colorList = listOf(
        Color.rgb(255, 0, 0),
        Color.rgb(0, 0, 255),
        Color.rgb(0, 255, 0),
        Color.rgb(255, 255, 0),
        Color.MAGENTA,
        Color.CYAN
    )

    // Declare private mutable LiveData object that can only be modified
    // within the class it is declared.
    private val _currentColor = MutableLiveData<Int>()

    // Declare another public immutable LiveData and override its getter method.
    // Return the private property's value in the getter method.
    // So, It can be accessed outside the current class.
    val currentColor : LiveData<Int>
        get() = _currentColor
    fun getColor() {
        _currentColor.value = colorList[getRandomValue(0, colorList.size)]
    }
    private fun getRandomValue(start : Int, end: Int): Int {
        return Random.nextInt(start, end);
    }

}

class SurveyViewModelFactory(private val repository: SurveyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SurveyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}