package school.cesar.criptocorretora.entidades

import java.math.BigDecimal

data class Cripto(
    val id: Long,
    val nome: String,
    val cotacaoAtualEmDollar: BigDecimal
)
