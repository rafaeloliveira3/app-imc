package br.senai.sp.jandira.imc20


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBinding
import br.senai.sp.jandira.imc20.model.User
import br.senai.sp.jandira.imc20.utils.getBmi
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class CalculateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        loadProfile()

        binding.buttonCalculate.setOnClickListener {
            updateUser()
            bmiCalculate()
        }
    }

    private fun updateUser() {
        val user = User()
        val dados = getSharedPreferences("dados", MODE_PRIVATE)
        val editor = dados.edit()

        if(binding.editTextWeight.text.isNotEmpty()){
            user.weight = binding.editTextWeight.text.toString().toInt()
            editor.putInt("weight", user.weight)
            if (editor.commit()) {
                Toast.makeText(this, "Usuário Alterado com Sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "Ocorreu um Erro na Alteração", Toast.LENGTH_SHORT).show()
            }
        }
        if (binding.editTextHeight.text.isNotEmpty()) {
            user.height = binding.editTextHeight.text.toString().toDouble()
            editor.putFloat("height", user.height.toFloat())
            if (editor.commit()) {
                Toast.makeText(this, "Usuário Alterado com Sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "Ocorreu um Erro na Alteração", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bmiCalculate() {
        val dados = getSharedPreferences("dados", MODE_PRIVATE)
        val bmi = getBmi(dados.getInt("weight", 0), dados.getFloat("height", 0.0f).toDouble())
        val bmiText = getStatusBmi(bmi, this)


        val openResult = Intent(this, ResultActivity::class.java)
        openResult.putExtra("bmi", bmi)
        openResult.putExtra("bmiText", bmiText)

        startActivity(openResult)
    }

    private fun loadProfile() {
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        binding.textViewUsername.text = dados.getString("name", "")
        binding.textViewEmail.text = dados.getString("email", "")
        binding.textViewUserHeight.text = "${resources.getString(R.string.user_height)}: ${dados.getFloat("height", 0.0f)}"
        binding.textViewUserWeight.text = "${resources.getString(R.string.user_weight)}: ${dados.getInt("weight", 0)} Kg"

    }
}