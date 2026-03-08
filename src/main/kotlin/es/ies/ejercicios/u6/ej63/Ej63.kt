package es.ies.ejercicios.u6.ej63



open class Vehiculo(val marca: String, val modelo: String) 
{
    init {
        println("[Vehiculo init] Inicializando superclase Vehiculo: $marca $modelo")
    }

    constructor(marca: String) : this(marca, "Desconocido") {
        println("[Vehiculo constructor secundario] Terminado de crear con marca: $marca")
    }
}

class Coche(marca: String, modelo: String, val numeroPuertas: Int) : Vehiculo(marca, modelo) 
{
    init {
        println("[Coche init] Inicializando coche de $numeroPuertas puertas")
    }

    constructor(marca: String, modelo: String) : this(marca, modelo, 4) {
        println("[Coche constructor secundario] Creando coche por defecto con 4 puertas")
    }
}

class Moto(marca: String, modelo: String, val cilindrada: Int) : Vehiculo(marca, modelo) 
{
    init {
        println("[Moto init] Inicializando moto de ${cilindrada}cc")
    }

    constructor(marca: String) : this(marca, "Genérica", 125) {
        println("[Moto constructor secundario] Creando moto genérica de 125cc")
    }
}

class Camion : Vehiculo 
{
    val capacidadCargaToneladas: Double

    constructor(marca: String, modelo: String, carga: Double) : super(marca, modelo) {
        this.capacidadCargaToneladas = carga
        println("[Camion constructor secundario] Llamo a super(marca, modelo). Carga: $carga t")
    }
    
    constructor(marca: String) : super(marca) {
        this.capacidadCargaToneladas = 3.5
        println("[Camion constructor secundario] Llamo a super(marca). Carga: 3.5 t")
    }
}





fun main() 
{
    println("1. Instanciando Coche (Primario)")
    val miCoche1 = Coche("Toyota", "Corolla", 5)

    println("\n2. Instanciando Coche (Secundario)")
    val miCoche2 = Coche("Seat", "Ibiza")

    println("\n3. Instanciando Moto (Secundario)")
    val miMoto = Moto("Yamaha")

    println("\n4. Instanciando Camion (Secundario delegado a Super primario)")
    val miCamion = Camion("Volvo", "FH16", 20.0)

    println("\n5. Instanciando Camion (Secundario delegado a Super secundario)")
    val miCamion2 = Camion("Scania")
}