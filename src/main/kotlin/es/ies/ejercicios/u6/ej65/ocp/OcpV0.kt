package es.ies.ejercicios.u6.ej65.ocp

import es.ies.ejercicios.u6.ej64.PlantillaInforme
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.InformeCsv
import es.ies.ejercicios.u6.ej64.Resumible

/**
 * Refactor OCP:
 * El generador OCP no cambia nunca (está "Closed" para modificación).
 * Para añadir nuevos formatos, se extiende de PlantillaInforme (está "Open" para extensión).
 * No necesitamos ningún "enum" ni ningún "when".
 */
class GeneradorInformeOCP {
    fun generar(generadorEspecifico: PlantillaInforme, titulo: String, items: List<Resumible>): String {
        return generadorEspecifico.generar(titulo, items)
    }
}

class InformeHtml : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "<h1>$titulo</h1>\n<ul>"
    override fun formatearItem(item: Resumible): String = "  <li>${item.resumen()}</li>"
    override fun pie(): String = "</ul>"
}


fun main() {
    val items = listOf<Resumible>(
        object : Resumible {
            override fun resumen(): String = "Elemento A"
        },
        object : Resumible {
            override fun resumen(): String = "Elemento B"
        }
    )

    val generadorCentral = GeneradorInformeOCP()
    
    println("--- OCP CSV ---")
    println(generadorCentral.generar(InformeCsv(), "Listado Comas", items))
    
    println("\n--- OCP MARKDOWN ---")
    println(generadorCentral.generar(InformeMarkdown(), "Listado Guiones", items))
    
    println("\n--- OCP NUEVO FORMATO HTML ---")
    println(generadorCentral.generar(InformeHtml(), "Listado Web", items))
}
