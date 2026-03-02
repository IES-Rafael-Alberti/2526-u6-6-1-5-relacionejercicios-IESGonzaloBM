package es.ies.ejercicios.u6.ej62

/**
 * Plantilla usando clase abstracta + "Template Method".
 *
 * Objetivo didáctico:
 * - Forzar herencia/implementación: `abstract` + miembro `abstract`.
 * - Bloquear sobrescritura del algoritmo: método `final`.
 */
abstract class ReportTemplate(private val logger: Logger? = null) {
    fun generate(title: String, lines: List<String>): String {
        logger?.log("Generando reporte: '$title'")
        
        val result = buildString {
            val h = header(title)
            if (h.isNotEmpty()) appendLine(h)
            
            for (line in lines) {
                appendLine(formatLine(line))
            }
            
            val f = footer()
            if (f.isNotEmpty()) appendLine(f)
        }
        
        logger?.log("Generación finalizada con éxito.")
        return result
    }

    protected open fun header(title: String): String = ""
    protected abstract fun formatLine(line: String): String
    protected open fun footer(): String = ""
}
