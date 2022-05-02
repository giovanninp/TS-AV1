package school.cesar.criptocorretora.builders

import school.cesar.criptocorretora.entidades.Cripto
import java.math.BigDecimal

class CriptoBuilder {

    private var criptoNumero: Long = 0

    fun buildCripto(nome: String, contacaoInicialEmDollar: BigDecimal): Cripto =
        Cripto(
            id = criptoNumero,
            nome = nome,
            cotacaoAtualEmDollar = contacaoInicialEmDollar
        )

    private fun calculaIdCripto(): Long =
        ++criptoNumero
}
