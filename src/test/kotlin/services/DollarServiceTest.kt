package services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.services.DollarService
import java.math.BigDecimal

class DollarServiceTest {
    private val dollarService: DollarService = DollarService()

    @Test
    fun `Deve receber um valor BigDecimal não nulo`() {
        val cotacao = dollarService.pegarValorDollarAtual()
        Assertions.assertTrue(cotacao is BigDecimal)
    }
}