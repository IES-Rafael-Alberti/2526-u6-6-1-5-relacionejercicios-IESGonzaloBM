package es.ies.ejercicios.u6.ej62

open class BaseLogger : Logger {
    override fun log(message: String) {
        TODO("Implementa el log base (si procede)")
    }
}

class StdoutLogger : BaseLogger() {
    final override fun log(message: String) {
        TODO("Implementa el log final (bloquea sobrescritura con final override)")
    }
}