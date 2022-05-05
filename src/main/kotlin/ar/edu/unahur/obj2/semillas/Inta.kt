package ar.edu.unahur.obj2.semillas


class Inta(val parcelas: MutableList<Parcelas>) {

    fun promedioPlantas(): Int {
        var result = 0
        this.parcelas.forEach { p -> result += p.plantas.size }
        return result / parcelas.size
    }

    private fun asociadas(parcelas: MutableList<Parcelas>) = parcelas.maxByOrNull { p -> p.porcentajeBienAsociadas() }

    fun masAutosustentable(): Parcelas? {
        val lista : MutableList<Parcelas> = this.parcelas.filter { p -> p.plantas.size >= 4 } as MutableList<Parcelas>
        return this.asociadas(lista)
    }
}