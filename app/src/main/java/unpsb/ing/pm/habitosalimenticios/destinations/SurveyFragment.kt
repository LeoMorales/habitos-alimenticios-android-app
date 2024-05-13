package unpsb.ing.pm.habitosalimenticios.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import unpsb.ing.pm.habitosalimenticios.R
import unpsb.ing.pm.habitosalimenticios.databinding.FragmentSurveyBinding

class SurveyFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedPortion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        val spinnerPortion = binding.spinnerPorcionValue
        spinnerPortion.onItemSelectedListener = this

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.portions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerPortion.adapter = adapter
        }

        val spinner_frequency = binding.spinnerFrecuenciaValue

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.portions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerPortion.adapter = adapter
        }

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.freq_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner_frequency.adapter = adapter
        }


        return view

    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).

        selectedPortion = parent.adapter.getItem(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}