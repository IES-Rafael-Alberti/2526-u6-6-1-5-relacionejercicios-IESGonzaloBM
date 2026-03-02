package es.ies.ejercicios.u6.ej62

/**
 * Ejercicio 6.2 — Forzado y bloqueo de herencia (RA7.b).
 */

fun main() {
    println("=== Generando reportes con subclases y polimorfismo ===")

    val consoleLogger = Logger { message -> println("[LOG] $message") }

    val lines = listOf("Primera línea", "Segunda línea", "Tercera línea")

    val csvReport: ReportTemplate = CsvReport(consoleLogger)
    println("--- CSV ---")
    println(csvReport.generate("Mi Reporte CSV", lines))
    println()

    val markdownReport: ReportTemplate = MarkdownReport(consoleLogger)
    println("--- MARKDOWN ---")
    println(markdownReport.generate("Mi Reporte MD", lines))
}
