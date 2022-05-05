package ar.edu.unahur.obj2.semillas


abstract class Semilla(var altura: Double, val anioSemilla: Int){

    open fun solTolera() = 7

    fun esFuerte() = this.solTolera() > 9

    open fun daSemillas() = this.esFuerte()

    abstract fun espacio(): Double

    abstract fun esIdeal(parcelas: Parcelas): Boolean
}


open class Menta(altura: Double, anioSemilla: Int): Semilla(altura, anioSemilla) {

    //override fun espacio() = this.altura + 1

    override fun daSemillas() = this.altura > 0.4

    override fun espacio(): Double = this.altura + 1

    override fun esIdeal(parcelas: Parcelas) = parcelas.superficie() > 6



}

open class Soja(altura: Double, anioSemilla: Int): Semilla(altura, anioSemilla) {

    override fun solTolera(): Int {
        var result = 0

        if (this.altura < 0.5){
            result = 6
        }else if (this.altura in 0.5..1.0){
            result = 8
        }else if (this.altura > 1){
            result = 12
        }

        return result
    }

    fun alturaEstandar() = this.altura in 0.75..0.9

    fun edadRequeria() = this.anioSemilla > 2007

    override fun daSemillas() = this.alturaEstandar() && this.edadRequeria()

    override fun espacio() = this.altura / 2

    override fun esIdeal(parcelas: Parcelas) = this.solTolera() == parcelas.horaSol



}

class Quinoa(altura: Double, anioSemilla: Int, var espacio: Double): Semilla(altura, anioSemilla) {


    override fun espacio() = this.espacio

    override fun esIdeal(parcelas: Parcelas): Boolean {
        val plantasParcela = parcelas.plantas
        var result = false
        if (!(plantasParcela.count { p -> p.altura > 1.5 } >= 1)){
            result = true
        }
        return result
    }

    override fun solTolera(): Int {
        var result = 0
        result = if (this.espacio < 0.3){
            10
        }else{
            super.solTolera()
        }
        return result
    }

    override fun daSemillas() = this.anioSemilla in 2001..2008 || super.daSemillas()


}

class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura, anioSemilla){

    override fun daSemillas() = false

    override fun esIdeal(parcelas: Parcelas) = parcelas.plantas.size <= 1


}

class Peperina(altura: Double, anioSemilla: Int): Menta(altura, anioSemilla){

    override fun espacio() = (super.espacio()) * 2
}