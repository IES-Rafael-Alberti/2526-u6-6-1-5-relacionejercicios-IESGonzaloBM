package es.ies.ejercicios.u6.ej65.isp

import es.ies.ejercicios.u6.ej64.Persona

/**
 * Refactor ISP: Las interfaces ya no son una sola interfaz obesa. Se han segregado
 * por responsabilidades exactas para que el programador solo requiera lo que de verdad usa.
 */
interface IEscritura {
    fun guardar(persona: Persona)
    fun borrarTodo()
}

interface ILectura {
    fun buscar(nombre: String): Persona?
}

interface IExportacion {
    fun exportarCsv(): String
}

class RepositorioSegregado : IEscritura, ILectura, IExportacion {
    private val map = mutableMapOf<String, Persona>()

    override fun guardar(persona: Persona) {
        map[persona.nombre] = persona
    }

    override fun borrarTodo() {
        map.clear()
    }

    override fun buscar(nombre: String): Persona? = map[nombre]

    override fun exportarCsv(): String =
        buildString {
            appendLine("nombre,edad")
            for (p in map.values) appendLine("${p.nombre},${p.edad}")
        }
}

/**
 * ¡Un cliente que solo busca, ahora recibe CERRADAMENTE una interfaz de Lectura!
 * Nunca tendrá a su disposición autocompletar el método ".borrarTodo()".
 */
class BuscadorPersonas(private val repoLectura: ILectura) {
    fun buscar(nombre: String): Persona? = repoLectura.buscar(nombre)
}

fun main() {
    val repoBBDD = RepositorioSegregado()
    repoBBDD.guardar(Persona("Alejandro", 22))

    val buscador = BuscadorPersonas(repoBBDD)
    println("Buscar Alejandro por ISP -> ${buscador.buscar("Alejandro")?.resumen()}")
}
