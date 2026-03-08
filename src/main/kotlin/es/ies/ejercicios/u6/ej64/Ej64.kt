package es.ies.ejercicios.u6.ej64

/**
 * Ejercicio 6.4 — Comentar y documentar el código (RA7.h).
 *
 * Punto de partida: `ProgramaDocumentable.kt`.
 *
 * TODO:
 * - Revisa el código base y aplica la teoría de comentarios/KDoc.
 * - Genera la documentación HTML con Dokka y deja el resultado en `Doc/`.
 * - Documenta el proceso y enlaza permalinks en `docs/ejercicios/6.4.md`.
 */
object Ej64

fun main() {
    println("Ejercicio 6.4 (plantilla)")
    println("- Revisa `ProgramaDocumentable.kt` y mejora KDoc/comentarios según la teoría")
    println("- Genera Dokka HTML y deja el resultado en `Doc/`")

    val items: List<Resumible> = listOf(
        Persona(nombre = "Ana", edad = 20),
        Alumno(nombre = "Luis", edad = 19, curso = "1DAM"),
        Persona(nombre = " Marta ", edad = 18),
    )

    val informeMd = InformeMarkdown()
    val informeCsv = InformeCsv()

    println("\n[DEMO] Informe Markdown")
    println(informeMd.generar(titulo = "Listado", items = items))

    println("\n[DEMO] Informe CSV")
    println(informeCsv.generar(titulo = "Listado", items = items))

    println("\n[DEMO] Registro (normalización de nombre)")
    val registro = RegistroPersonas()
    for (item in items) {
        if (item is Persona) registro.registrar(item)
    }
    println("Buscar marta -> ${registro.buscar("marta")?.resumen()}")
}
