package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creacion de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val soja2 = Soja(2.0, 2009)
        val soja3 = Soja(1.5, 2009)
        val quinoa = Quinoa(0.3,2010,0.2)
        val quinoa2 = Quinoa(0.4,2010,0.1)
        val quinoa3 = Quinoa(0.4,2010,0.1)
        val quinoa4 = Quinoa(0.4,2010,0.1)

        val sojaT = SojaTransgenica(0.8, 2008)
        val peperina = Peperina(1.0, 2021)
        val parcela = ParcelasEcologicas(1, 20, 12, mutableListOf(soja2,soja3))
        val parcela2 = ParcelasIndustriales(1, 20, 8, mutableListOf(quinoa, quinoa2))
        val parcela3 = ParcelasEcologicas(2,30, 10, mutableListOf(quinoa, quinoa2, quinoa3, quinoa4))
        val inta = Inta(mutableListOf(parcela, parcela2, parcela3))


        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("horas sol"){
            soja2.solTolera().shouldBe(12)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoa.daSemillas().shouldBeTrue()
            sojaT.daSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
            quinoa.esFuerte().shouldBeTrue()
            quinoa2.esFuerte().shouldBeTrue()
            soja2.esFuerte().shouldBeTrue()
            soja3.esFuerte().shouldBeTrue()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
            peperina.espacio().shouldBe(4.0)
        }

        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            Math.ceil(superficie).shouldBe(4)
        }

        it("verificacion de parcela"){
            //parcela.cantMax().shouldBe(4)
            /*parcela.plantarPlanta(soja)
            parcela.plantarPlanta(soja)
            parcela.plantarPlanta(soja)
            parcela.plantarPlanta(soja)
            parcela.plantarPlanta(soja).shouldBeNull()*/
        }

        it("parcelas ideales"){
            menta.esIdeal(parcela).shouldBeTrue()
            quinoa.esIdeal(parcela).shouldBeTrue()
            soja.esIdeal(parcela).shouldBeFalse()
            sojaT.esIdeal(parcela).shouldBeTrue()
            soja.esIdeal(parcela2).shouldBeTrue()
        }

        it("asocian bien"){
            parcela.asociar(soja2).shouldBe(true)
            parcela2.asociar(soja3).shouldBeTrue()
        }

        it("promedio"){
            inta.promedioPlantas().shouldBe(2)
        }

        it("autosustentable") {
            inta.masAutosustentable().shouldBe(parcela3)
        }

    }
})