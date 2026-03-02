package es.ies.ejercicios.u6.ej65.dip

import es.ies.ejercicios.u6.ej64.PlantillaInforme
import es.ies.ejercicios.u6.ej64.InformeCsv
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Refactor DIP:
 * El módulo de alto nivel (Controlador) ya NO hace un "new" a una implementacion como [InformeCsv].
 * Para cumplir la "Inversion de dependencias", depende de una ABSTRACCIÓN [PlantillaInforme]
 * que se pasa desde el exterior a través el constructor.
 */
class ControladorInformes(private val abstraccionGenerador: PlantillaInforme) {

    fun imprimirListado(items: List<Resumible>) {
        val salida = abstraccionGenerador.generar("Listado DIP Correcto", items)
        println(salida)
    }
}

fun main() {
    val listaBasica = listOf(
        Persona("Ana", 20),
        Persona("Luis", 19)
    )
    
    val controladorMarkdown = ControladorInformes(InformeMarkdown())
    controladorMarkdown.imprimirListado(listaBasica)

    val controladorCsv = ControladorInformes(InformeCsv())
    controladorCsv.imprimirListado(listaBasica)
}
