package es.ies.ejercicios.u6.ej64

/**
 * Representa un elemento que puede generar un resumen en formato de texto.
 * Interfaz usada para asegurar que cualquier objeto que participe en el informe tenga su representación String.
 */
interface Resumible {
    /**
     * Devuelve una cadena de texto con el resumen del elemento.
     * @return El resumen en formato [String].
     */
    fun resumen(): String
}

/**
 * Plantilla principal para generar un informe en distintos formatos usando el patrón Template Method.
 *
 * Implementa [Resumible] y obliga a las subclases a definir cómo se formatea cada línea.
 * El método [generar] está bloqueado (`final` implícitamente por defecto en Kotlin) para forzar un flujo común.
 */
abstract class PlantillaInforme : Resumible {
    
    /**
     * Genera el contenido completo del informe uniendo cabecera, todos los items formateados y el pie.
     * @param titulo El título principal del informe.
     * @param items Lista de elementos [Resumible] a incluir en el cuerpo.
     * @return El texto final del informe concatenado.
     */
    fun generar(titulo: String, items: List<Resumible>): String {
        val sb = StringBuilder()

        sb.appendLine(cabecera(titulo))

        for (item in items) {
            sb.appendLine(formatearItem(item))
        }

        sb.appendLine(pie())
        return sb.toString() 
    }

    /**
     * Define la cabecera del informe. Por defecto devuelve el mismo título.
     * Se puede sobrescribir mediante herencia en los subtipos.
     */
    protected open fun cabecera(titulo: String): String = titulo

    /**
     * Da formato específico a cada elemento [Resumible]. Obligatorio de implementar por las subclases.
     */
    protected abstract fun formatearItem(item: Resumible): String

    /**
     * Define el pie del informe. Por defecto un mensaje de 'fin'.
     */
    protected open fun pie(): String = "-- fin --"

    override fun resumen(): String = "PlantillaInforme"
}

/**
 * Subclase de [PlantillaInforme] que genera informes en sintaxis Markdown.
 */
class InformeMarkdown : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "# $titulo"

    override fun formatearItem(item: Resumible): String = "- ${item.resumen()}"
}

/**
 * Subclase de [PlantillaInforme] que genera informes en formato CSV separando cabeceras por comas 
 * e impidiendo que los items tengan comas internamente.
 */
class InformeCsv : PlantillaInforme() {
    override fun cabecera(titulo: String): String = "titulo,$titulo\nitem"

    // Reemplazamos la coma por punto y coma internamente para no romper el formato CSV
    override fun formatearItem(item: Resumible): String = item.resumen().replace(",", ";")
}

/**
 * Representa a una persona básica en nuestro sistema con su nombre y edad.
 * 
 * @property nombre Nombre de la persona.
 * @property edad Edad de la persona (entero).
 */
open class Persona(
    val nombre: String,
    val edad: Int,
) : Resumible {

    init {
        println("[Persona:init] nombre=$nombre edad=$edad")
    }

    /**
     * Constructor secundario que permite registrar una persona solo con su nombre.
     * Delega en el principal asumiendo que la edad por defecto es 0.
     */
    constructor(nombre: String) : this(nombre, edad = 0) {
        println("[Persona:secondary] constructor(nombre)")
    }

    override fun resumen(): String = "$nombre ($edad)"
}

/**
 * Especialización de [Persona] que añade un curso académico.
 * Demuestra el uso de constructores secundarios sin bloque init explícito al delegar sobre 'super'.
 */
class Alumno : Persona {
    /**
     * Curso que cursa el alumno (ej. "1DAM").
     */
    val curso: String

    constructor(nombre: String, edad: Int, curso: String) : super(nombre, edad) {
        this.curso = curso
        println("[Alumno:secondary] nombre=$nombre edad=$edad curso=$curso")
    }

    constructor(nombre: String, curso: String) : this(nombre, edad = 0, curso = curso) {
        println("[Alumno:secondary] constructor(nombre, curso)")
    }

    override fun resumen(): String = "Alumno: ${super.resumen()} curso=$curso"
}

/**
 * Registro de personas que evita duplicidades.
 * Utiliza un diccionario interno indexando a los usuarios por su nombre normalizado.
 */
class RegistroPersonas {
    private val personasPorNombre = mutableMapOf<String, Persona>()

    /**
     * Registra una nueva [Persona] usando su nombre normalizado como clave.
     * Si existía otra persona con un nombre equivalente, la sobrescribirá.
     */
    fun registrar(persona: Persona) {
        val clave = normalizarNombre(persona.nombre)
        personasPorNombre[clave] = persona
    }

    /**
     * Busca una persona por su nombre.
     * @param nombre El nombre a buscar (no necesita estar exacto, se normalizará automáticamente).
     * @return La [Persona] si fue encontrada, o null si no existe ninguna coincidencia.
     */
    fun buscar(nombre: String): Persona? = personasPorNombre[normalizarNombre(nombre)]

    // Importante: Se normaliza el nombre con trim() y minúsculas para evitar guardar "Juan" y "juan " 
    // como dos personas distintas. Esta es una regla de negocio clave para no ensuciar BBDD.
    private fun normalizarNombre(nombre: String): String {
        return nombre.trim().lowercase()
    }
}
