package com.argentina.programa.view

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.argentina.programa.R
import com.argentina.programa.model.Comparar

import java.util.Date


class MainViewModel : ViewModel() {
    private val Texto1 = MutableLiveData<String>().apply { value = "" }
    private val Texto2 = MutableLiveData<String>().apply { value = "" }
    private val Comparar = MutableLiveData<Comparar>()


    val compararResultado: LiveData<Comparar> get() = Comparar
    val texto1: LiveData<String> = Texto1
    val texto2: LiveData<String> = Texto2
    val mensaje = MutableLiveData<String>()

    fun setTexto1(texto: String) {
        Texto1.value = texto.trim()
    }

    fun setTexto2(texto: String) {
        Texto2.value = texto.trim()
    }

    val imageViewResultadoVisibility = MutableLiveData<Int>().apply { value = View.INVISIBLE }
    val imageViewResultadoResource = MutableLiveData<Int>()

    fun comparar() {
        val Texto1Valor = Texto1.value
        val Texto2valor = Texto2.value

        if (Texto1Valor.isNullOrEmpty() || Texto2valor.isNullOrEmpty()){
            mensaje.value = "Por favor ingresá textos para compararlos"
        } else {

            val resultado = Texto1Valor == Texto2valor
            val modeloComparar = Comparar(resultado, Date(), mensaje = mensaje.value.toString())
            Comparar.value = modeloComparar
            if (resultado) {
                mensaje.value = "Los textos son iguales!"
                imageViewResultadoVisibility.value = View.VISIBLE
                imageViewResultadoResource.value = R.drawable.ic_success
            } else {
                mensaje.value= "Los textos son distintos!"
                imageViewResultadoVisibility.value = View.VISIBLE
                imageViewResultadoResource.value = R.drawable.ic_error
            }

            Texto1.value = " "
            Texto2.value = " "
        }
        }


}
