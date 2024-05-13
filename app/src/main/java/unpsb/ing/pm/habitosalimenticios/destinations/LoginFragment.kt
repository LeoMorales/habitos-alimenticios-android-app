package unpsb.ing.pm.habitosalimenticios.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import unpsb.ing.pm.habitosalimenticios.R

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val btnComenzar = root.findViewById<Button>(R.id.button_comenzar)
        btnComenzar.setOnClickListener {
            findNavController().navigate(R.id.action_start_survey)
        }
        return root
    }

}