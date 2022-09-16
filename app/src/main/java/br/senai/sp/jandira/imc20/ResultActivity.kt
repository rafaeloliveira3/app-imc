package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val bmi = intent.getDoubleExtra("bmi", 0.0)
        val bmiText = intent.getStringExtra("bmiText")


        binding.textViewResult.text = String.format("%.2f", bmi)
        binding.textViewBmiResult.text = bmiText
    }
}