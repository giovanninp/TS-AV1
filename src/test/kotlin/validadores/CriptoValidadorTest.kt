package validadores

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.validadores.CriptoValidador
import java.math.BigDecimal

class CriptoValidadorTest {
    val validator: CriptoValidador = CriptoValidador()
    val cripto: Cripto = Cripto(0,"BancoLascado", BigDecimal(5.13))

    @Test
    fun `Deve falhar quando o nome for vazio`(){
        assertThrows<RuntimeException> {
            validator.valida(cripto.copy(nome = ""))
        }.also {
            Assertions.assertEquals("O campo nome deve ser preenchido", it.message)
        }
    }
}