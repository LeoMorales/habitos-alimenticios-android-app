package unpsb.ing.pm.habitosalimenticios.data

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class AppViewModel: ViewModel() {
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

// Logic of application

    init {
        getColor()
        Log.d("viewModel", "in-it")
    }

    // getcolor() function sets the _currentColor value to a random color.
    fun getColor() {
        _currentColor.value = colorList[getRandomValue(0, colorList.size)]
    }

    // This function returns a random number between 0 to size of list.
    private fun getRandomValue(start : Int, end: Int): Int {
        return Random.nextInt(start, end);
    }

}