package es.ies.ejercicios.u6.ej61

open class Empleado(val nombre: String, val salarioBase: Double) 
{
    open fun calcularSalario(): Double = salarioBase
    open fun trabajar() = println("$nombre esta trabajando como Empleado")
}

class Dev(nombre: String, salarioBase: Double, val lenguaje: String) : Empleado(nombre, salarioBase) 
{
    override fun calcularSalario(): Double = super.calcularSalario() + 500.0
    override fun trabajar() = println("$nombre esta programando en $lenguaje.")
}

class Disenador(nombre: String, salarioBase: Double, val bono: Double) : Empleado(nombre, salarioBase) 
{
    override fun calcularSalario(): Double = super.calcularSalario() + bono
    override fun trabajar() = println("$nombre esta gestionando el equipo y planificando el proyecto.")
}




open class Dispositivo(val marca: String) 
{
    fun encender() = println("El dispositivo $marca se ha encendido.")
    fun apagar() = println("El dispositivo $marca se ha apagado.")
}

class iPhone(marca: String, val numero: String) : Dispositivo(marca) 
{
    fun llamar(contacto: String) = println("El teléfono $numero está llamando a $contacto...")
}

class iWatch(marca: String, val tieneSensorRitmoCardiaco: Boolean) : Dispositivo(marca) {
    fun medirRitmoCardiaco() {
        if (tieneSensorRitmoCardiaco) 
            println("El reloj de marca $marca está midiendo el ritmo cardíaco.") 
        else 
            println("El reloj de marca $marca no tiene sensor de ritmo cardíaco.")
    }
}



fun main() {
    println("ESPECIALIZACION")

    val listaEmpleados = listOf(
        Empleado("Ana", 7890.0),
        Dev("Luis", 11323.0, "Kotlin"),
        Disenador("Marta", 23443.0, 123.0)
    )

    listaEmpleados.forEach {
        it.trabajar()
        println("Salario total: ${it.calcularSalario()}€\n===========")
    }

    println("\nEXTENSION")

    val listaDispositivos = listOf(
        Dispositivo("Generic"),
        iPhone("Samsung", "+34 600 123 456"),
        iWatch("Apple", true)
    )
    
    listaDispositivos.forEach { dispositivo ->
        dispositivo.encender()

        when (dispositivo) {
            is iPhone -> dispositivo.llamar("Contacto de Emergencia")
            is iWatch -> dispositivo.medirRitmoCardiaco()
        }

        dispositivo.apagar()
    }
}