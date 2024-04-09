package unpsb.ing.pm.habitosalimenticios

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import unpsb.ing.pm.habitosalimenticios.databinding.ActivityConfirmationBinding
import unpsb.ing.pm.habitosalimenticios.databinding.ActivitySurveyBinding


class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_confirmation)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //get the current intent
        val intent = intent
        val portion = intent.getStringExtra("SELECTED_PORTION")

        binding.textViewMainText.text = "Se ingiere ${portion} de algo..."


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}