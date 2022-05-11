package school.cesar.criptocorretora.builders

import school.cesar.criptocorretora.entidades.Cripto
import java.math.BigDecimal

class CriptoBuilder {

    private var criptoNumero: Long = 0

    private fun calculaIdCripto(): Long = ++this.criptoNumero

    fun buildCripto(nome: String, contacaoInicialEmDollar: BigDecimal): Cripto {
        val newCripto: Cripto = Cripto(
            id = criptoNumero,
            nome = nome,
            cotacaoAtualEmDollar = contacaoInicialEmDollar
        )
        this.calculaIdCripto()
        return newCripto
    }
}
