package com.argentina.programa

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.argentina.programa.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {

    private lateinit var viewModel:MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_ValorInicial() =  runTest{
        val Texto1Value = viewModel.texto1.value
        val Texto2Value = viewModel.texto2.value

        assertEquals("",Texto1Value)
        assertEquals("",Texto2Value)
    }

    @Test
    fun mainViewModel_CompararTextos() = runTest{
        //Probar textos iguales
        viewModel.setTexto1("Ejemplo1")
        viewModel.setTexto2("Ejemplo1")
        viewModel.comparar()

        val resultadoIgual = viewModel.compararResultado.value
        assertNotNull(resultadoIgual)
        if (resultadoIgual != null) {
            assertTrue(resultadoIgual.resultado)
        }
        if (resultadoIgual != null) {
            assertEquals("Mensaje aquí", resultadoIgual.mensaje)
        }

        // Probar textos ditintos
        viewModel.setTexto1("Distintos1")
        viewModel.setTexto2("Distintos2")
        viewModel.comparar()

        val resultadoDistinto = viewModel.compararResultado.value
        assertNotNull(resultadoDistinto)
        if (resultadoDistinto != null) {
            assertFalse(resultadoDistinto.resultado)
        }
        if (resultadoDistinto != null) {
            assertEquals("Mensaje aquí", resultadoDistinto.mensaje)
        }
    }
}