package es.ies.ejercicios.u6.ej65.lsp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Refactor LSP:
 * Separamos la funcionalidad en interfaces por "capacidades" reales.
 */
interface BuscadorPersonas {
    fun buscar(nombre: String): Persona?
}

interface GuardadorPersonas {
    fun guardar(persona: Persona)
}

/**
 * Un repositorio normal sabe hacer las dos cosas (buscar y guardar) porque hereda de ambas interfaces.
 */
open class RepositorioPersonasNormal : BuscadorPersonas, GuardadorPersonas {
    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun buscar(nombre: String): Persona? = map[nombre]
}

/**
 * Un repositorio de "solo lectura" NO engaña al cliente fingiendo saber "guardar".
 * Únicamente firma el contrato de que sabe que puede "buscar".
 * Internamente puede leer datos de cualquier sitio preexistente.
 */
class RepositorioSoloLectura(private val datosPreexistentes: Map<String, Persona>) : BuscadorPersonas {
    override fun buscar(nombre: String): Persona? = datosPreexistentes[nombre]
}


fun cliente(repoEscritura: GuardadorPersonas, repoLectura: BuscadorPersonas) {
    repoEscritura.guardar(Persona("Ana", 20))
    println("Buscar Ana -> ${repoLectura.buscar("Ana")?.resumen()}")
}

fun main() {
    println("[LSP] Usando un Repositorio Normal...")
    val repoFull = RepositorioPersonasNormal()
    cliente(repoFull, repoFull)

    println("\n[LSP] Usando un Repositorio Solo Lectura...")
    val datosBBDD = mapOf("Ana" to Persona("Ana", 20))
    val repoReadOnly = RepositorioSoloLectura(datosBBDD)
    
    println("Buscar Ana de solo-lectura -> ${repoReadOnly.buscar("Ana")?.resumen()}")
}

