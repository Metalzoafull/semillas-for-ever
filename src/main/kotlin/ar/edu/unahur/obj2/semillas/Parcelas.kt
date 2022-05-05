package ar.edu.unahur.obj2.semillas

abstract class Parcelas(val largo:Int, val ancho:Int, val horaSol:Int, val plantas: MutableList<Semilla>) {

    fun superficie() = this.ancho * this.largo

    fun cantMax(): Int {
        var result = 0
        result = if (this.ancho > this.largo){
            this.superficie() / 5
        }else {
            (this.superficie() / 3) + this.largo
        }
        return result
    }

    private fun toleraMenos() = this.plantas.count { semilla -> semilla.solTolera() < this.horaSol }

    fun complicaciones() = this.toleraMenos() >= 1

    fun plantarPlanta(semilla: Semilla){
        if (cantMax() == plantas.size && this.horaSol >= semilla.solTolera() + 2 ){
            error("ya se supero el limite de plantas para agregar o la parcela recibe mas sol del que la planta necesita")
        }else{
            this.plantas.add(semilla)
        }
    }

    abstract fun asociar(semilla: Semilla): Boolean

    fun porcentajeBienAsociadas(): Int {
        return plantas.count { s -> asociar(s) }
    }


}

class ParcelasEcologicas(largo: Int, ancho: Int, horaSol: Int, plantas: MutableList<Semilla>): Parcelas(largo, ancho, horaSol,
    plantas) {
    override fun asociar(semilla: Semilla) = !this.complicaciones() && semilla.esIdeal(this)

}

class ParcelasIndustriales(largo: Int, ancho: Int, horaSol: Int, plantas: MutableList<Semilla>): Parcelas(largo, ancho, horaSol,
    plantas)  {
    override fun asociar(semilla: Semilla) = this.plantas.size <= 2 && semilla.esFuerte()

}