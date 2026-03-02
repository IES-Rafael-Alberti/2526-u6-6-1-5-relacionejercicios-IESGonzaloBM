package es.ies.ejercicios.u6.ej62

class MarkdownReport(logger: Logger? = null) : ReportTemplate(logger) {
    override fun header(title: String): String = "# $title"

    override fun formatLine(line: String): String = "- $line"
}
