package es.ies.ejercicios.u6.ej62

/**
 * Plantilla (para el alumnado): usa clase abstracta + "Template Method".
 *
 * Objetivo didáctico:
 * - Forzar herencia/implementación: `abstract` + miembro `abstract`.
 * - Bloquear sobrescritura del algoritmo: método `final` (o no-`open`).
 */
abstract class ReportTemplate {
    final fun generate(title: String, lines: List<String>): String =
        TODO("Implementa el algoritmo común (template method) y usa header/formatLine/footer")

    protected open fun header(title: String): String = TODO("Opcional: cabecera común/por subtipo")

    protected abstract fun formatLine(line: String): String

    protected open fun footer(): String = TODO("Opcional: pie común/por subtipo")
}