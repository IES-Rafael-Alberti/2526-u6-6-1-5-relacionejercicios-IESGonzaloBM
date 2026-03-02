package es.ies.ejercicios.u6.ej61

// ==========================================
// ESTRUCTURA 1: Especialización
// ==========================================
// La subclase es un tipo especial de la superclase que redefine o especializa 
// el comportamiento existente, manteniendo la misma interfaz.

open class Empleado(val nombre: String, val salarioBase: Double) {
    open fun calcularSalario(): Double {
        return salarioBase
    }

    open fun trabajar() {
        println("$nombre está trabajando de forma genérica.")
    }
}

class Desarrollador(nombre: String, salarioBase: Double, val lenguaje: String) : Empleado(nombre, salarioBase) {
    // Especializamos el cálculo del salario
    override fun calcularSalario(): Double {
        return super.calcularSalario() + 500.0
    }

    // Especializamos la forma de trabajar
    override fun trabajar() {
        println("$nombre está programando en $lenguaje.")
    }
}

class Gerente(nombre: String, salarioBase: Double, val bono: Double) : Empleado(nombre, salarioBase) {
    // Especializamos el cálculo del salario
    override fun calcularSalario(): Double {
        return super.calcularSalario() + bono
    }

    // Especializamos la forma de trabajar
    override fun trabajar() {
        println("$nombre está gestionando el equipo y planificando el proyecto.")
    }
}

// ==========================================
// ESTRUCTURA 2: Extensión
// ==========================================
// La subclase añade nueva funcionalidad (nuevos métodos/propiedades) 
// que no existen en la superclase, sin alterar el comportamiento base heredado.

open class Dispositivo(val marca: String) {
    fun encender() {
        println("El dispositivo $marca se ha encendido.")
    }
    
    fun apagar() {
        println("El dispositivo $marca se ha apagado.")
    }
}

class TelefonoInteligente(marca: String, val numero: String) : Dispositivo(marca) {
    // Añade nueva funcionalidad exclusiva de un teléfono
    fun llamar(contacto: String) {
        println("El teléfono $numero está llamando a $contacto...")
    }
}

class RelojInteligente(marca: String, val tieneSensorRitmoCardiaco: Boolean) : Dispositivo(marca) {
    // Añade nueva funcionalidad exclusiva de un reloj inteligente
    fun medirRitmoCardiaco() {
        if (tieneSensorRitmoCardiaco) {
            println("El reloj de marca $marca está midiendo el ritmo cardíaco.")
        } else {
            println("El reloj de marca $marca no tiene sensor de ritmo cardíaco.")
        }
    }
}

/**
 * Ejercicio 6.1 — Tipos de herencia, clases y subclases (RA7.a).
 */
fun main(args: Array<String>) {
    println("Ejemplo 1: Especialización (Polimorfismo)")
    val emp1: Empleado = Empleado("Ana", 1500.0)
    val emp2: Empleado = Desarrollador("Luis", 1500.0, "Kotlin")
    val emp3: Empleado = Gerente("Marta", 2000.0, 800.0)

    val listaEmpleados = listOf(emp1, emp2, emp3)
    for (empleado in listaEmpleados) {
        empleado.trabajar()
        println("Salario total: ${empleado.calcularSalario()}€")
        println("---")
    }

    println("\nEjemplo 2: Extensión")
    val disp1: Dispositivo = Dispositivo("Generic")
    val disp2: Dispositivo = TelefonoInteligente("Samsung", "+34 600 123 456")
    val disp3: Dispositivo = RelojInteligente("Apple", true)

    val listaDispositivos = listOf(disp1, disp2, disp3)
    for (dispositivo in listaDispositivos) {
        // Usamos las funcionalidades de la superclase
        dispositivo.encender()

        when (dispositivo) {
            is TelefonoInteligente -> dispositivo.llamar("Contacto de Emergencia")
            is RelojInteligente -> dispositivo.medirRitmoCardiaco()
        }

        dispositivo.apagar()
        println("---")
    }
}