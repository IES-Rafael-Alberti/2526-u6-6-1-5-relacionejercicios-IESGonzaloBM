package es.ies.ejercicios.u6.ej62

/**
 * Ejemplo de interfaz: alternativa cuando no necesitas estado/implementación base,
 * o cuando quieres permitir múltiples implementaciones sin herencia de clases.
 */
fun interface Logger {
    fun log(message: String)
}