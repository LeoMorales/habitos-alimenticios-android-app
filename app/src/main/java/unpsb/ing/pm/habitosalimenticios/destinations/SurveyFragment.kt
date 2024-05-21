package unpsb.ing.pm.habitosalimenticios.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import unpsb.ing.pm.habitosalimenticios.R
import unpsb.ing.pm.habitosalimenticios.SurveysApplication
import unpsb.ing.pm.habitosalimenticios.data.SurveyViewModel
import unpsb.ing.pm.habitosalimenticios.data.SurveyViewModelFactory
import unpsb.ing.pm.habitosalimenticios.databinding.FragmentSurveyBinding
import unpsb.ing.pm.habitosalimenticios.db.entities.Survey

class SurveyFragment : Fragment() {

    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SurveyViewModel by viewModels {
        SurveyViewModelFactory((activity?.application as SurveysApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_survey, container, false)

        val root = binding.root

        binding.appViewModel = viewModel
        // Specify the fragment as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        setupClickListeners(binding, viewModel)
        setupSelectors(binding)

        return root

    }

    private fun setupClickListeners(binding: FragmentSurveyBinding, viewModel: SurveyViewModel) {

        binding.textViewTitle.setOnClickListener{
            viewModel.getColor()
        }

        binding.buttonSave.setOnClickListener {
            viewModel.insert(
                Survey(
                    food = "Yogur",
                    portion = viewModel.currentPortion.value.toString(),
                    frequency = viewModel.currentFrequency.value.toString()))

            Toast.makeText(context, "Nueva encuesta creada", Toast.LENGTH_SHORT ).show()
            findNavController().navigate(R.id.action_back_to_start)
        }
    }

    private fun setupSelectors(binding: FragmentSurveyBinding) {

        val spinnerPortionSelector = binding.spinnerPorcionValue

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.portions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerPortionSelector.adapter = adapter
        }

        spinnerPortionSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Toma el ítem seleccionado
                //val selectedPortion = parent.adapter.getItem(position).toString()
                //viewModel.changePortionValue(selectedPortion)
                when (position) {
                    0 -> viewModel.changePortionValue("200ml")
                    1 -> viewModel.changeFrequencyValue("250ml")
                    else -> {
                        print("no válido!")
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Otra acción cuando no se selecciona nada, si es necesario
            }
        }

        val spinner_frequency = binding.spinnerFrecuenciaValue

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

        spinner_frequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Toma el ítem seleccionado
                //val selectedFrequency = parent.adapter.getItem(position).toString()
                when (position) {
                    0 -> viewModel.changeFrequencyValue("dia")
                    1 -> viewModel.changeFrequencyValue("semana")
                    2 -> viewModel.changeFrequencyValue("mes")
                    3 -> viewModel.changeFrequencyValue("año")
                    else -> {
                        print("no válido!")
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Otra acción cuando no se selecciona nada, si es necesario
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}