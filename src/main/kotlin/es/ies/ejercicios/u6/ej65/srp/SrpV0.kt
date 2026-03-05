package es.ies.ejercicios.u6.ej65.srp

import es.ies.ejercicios.u6.ej64.Alumno
import es.ies.ejercicios.u6.ej64.InformeMarkdown
import es.ies.ejercicios.u6.ej64.Persona
import es.ies.ejercicios.u6.ej64.RegistroPersonas
import es.ies.ejercicios.u6.ej64.Resumible

class RepositorioDatos {
    fun obtenerDatos(): List<Resumible> {
        return listOf(
            Persona(" Ana ", 20),
            Alumno("Luis", 19, "1DAM"),
            Persona("Marta", 18)
        )
    }
}

class ServicioRegistro {
    val registro = RegistroPersonas()
    
    fun registrarPersonas(items: List<Resumible>) {
        for (item in items) {
            if (item is Persona) registro.registrar(item)
        }
    }
}

class ImpresorInforme {
    fun imprimirMarkdown(titulo: String, items: List<Resumible>) {
        val informe = InformeMarkdown()
        println(informe.generar(titulo, items))
    }
}

/**
 * SRP refactorizado. En lugar de tener una sola clase "dios", 
 * tenemos clases pequeñas con un solo motivo para cambiar.
 */
class InformeAppService {
    fun ejecutar() {
        println("[SRP] Obteniendo datos...")
        val datos = RepositorioDatos().obtenerDatos()

        println("[SRP] Registrando personas...")
        val servicioRegistro = ServicioRegistro()
        servicioRegistro.registrarPersonas(datos)

        println("[SRP] Generando informe:\n")
        val impresor = ImpresorInforme()
        impresor.imprimirMarkdown("Listado SRP Refactor", datos)

        println("\n[SRP] Búsqueda final: ${servicioRegistro.registro.buscar("ana")?.resumen()}")
    }
}

fun main() {
    InformeAppService().ejecutar()
}
