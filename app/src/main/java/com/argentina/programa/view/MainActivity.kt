package com.argentina.programa.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.argentina.programa.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonComparar.setOnClickListener{
            val texto1 = binding.EditText1.text.toString()
            val texto2 = binding.EditText2.text.toString()
            mainViewModel.setTexto1(texto1)
            mainViewModel.setTexto2(texto2)
            mainViewModel.comparar()
        }

        mainViewModel.compararResultado.observe(this) {
            modeloComparar ->
            val compararTextos = if (modeloComparar.resultado) "Los textos son iguales!"
            else "Los textos son distintos!"
            binding.textComparar.text = compararTextos

        }

        mainViewModel.imageViewResultadoVisibility.observe(this) { visibility ->
            binding.imageViewResultado.visibility = visibility
        }

        mainViewModel.imageViewResultadoResource.observe(this) { resource ->
            binding.imageViewResultado.setImageResource(resource)
        }
    }
}