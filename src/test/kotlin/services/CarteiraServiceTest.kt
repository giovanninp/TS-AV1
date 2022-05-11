package services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.services.CarteiraService
import school.cesar.criptocorretora.services.CriptoService
import school.cesar.criptocorretora.services.UsuarioService
import java.math.BigDecimal

class CarteiraServiceTest {
    private val usuarioServiceMock: UsuarioService = mockk<UsuarioService>()
    private val criptoServiceMock: CriptoService = mockk<CriptoService>()
    private val carteiraService: CarteiraService = CarteiraService(usuarioServiceMock, criptoServiceMock)
    private val cripto: Cripto = Cripto(0, "Criptonho",BigDecimal(10))
    private val usuario: Usuario = Usuario(0,"53670701087", "Glauber", "glauber@glauber.com", "Test@123", Carteira())


    @Test
    fun `Deve retornar valores do cripto da carteira existentes`() {
        val carteira:Carteira = Carteira();
        carteira.cripto.put(cripto, BigDecimal(50))

        every { usuarioServiceMock.buscarPorId(0) } returns usuario.copy(carteira = carteira)
        Assertions.assertNotEquals(carteiraService.consultarValoresAgrupados(0).toString(), "{}")
    }

    @Test
    fun `Deve retornar valor vazio para usuario sem valores de cripto`() {
        every { usuarioServiceMock.buscarPorId(0) } returns usuario
        Assertions.assertEquals(carteiraService.consultarValoresAgrupados(0).toString(), "{}")
    }

    @Test
    fun `Deve falhar caso tente consultar valot da carteira de um usuario inexistente`() {
        assertThrows<RuntimeException> {
            every { usuarioServiceMock.buscarPorId(0) } returns throw UsuarioInvalidoException("Id Não encontrado")
            carteiraService.consultarValoresAgrupados(0)
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }
    }

    @Test
    fun `Deve falhar se comprar com id de usuario inexistente`() {
        assertThrows<RuntimeException> {
            every { usuarioServiceMock.buscarPorId(0) } returns throw UsuarioInvalidoException("Id Não encontrado")
            carteiraService.comprar(0, 0,BigDecimal(10))
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }
    }

    @Test
    fun `Deve falhar se comprar com usuario com carteira cripto vazia`() {
        assertThrows<RuntimeException> {
            every { usuarioServiceMock.buscarPorId(0) } returns usuario
            every { criptoServiceMock.buscarPorId(0) } returns throw CriptoInvalidaException("Id Não encontrado")
            carteiraService.comprar(0, 0,BigDecimal(10))
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }
    }

    @Test
    fun `Deve falhar caso a soma do saldo não bata com o valor anterior mais o comprado`() {
        every { usuarioServiceMock.buscarPorId(0) } returns usuario
        every { criptoServiceMock.buscarPorId(0) } returns cripto.copy(cotacaoAtualEmDollar = BigDecimal(2))
        carteiraService.comprar(0, 0,BigDecimal(10))
        val currCripto = criptoServiceMock.buscarPorId(0)
        val saldo = usuario.carteira.cripto[currCripto]
        Assertions.assertEquals(BigDecimal(5),saldo)
    }

    @Test
    fun `Deve falhar caso a soma do valor seja negativa`() {
        every { usuarioServiceMock.buscarPorId(0) } returns usuario
        every { criptoServiceMock.buscarPorId(0) } returns cripto.copy(cotacaoAtualEmDollar = BigDecimal(2))
        carteiraService.comprar(0, 0,BigDecimal(-1))
        val currCripto = criptoServiceMock.buscarPorId(0)
        val saldo = usuario.carteira.cripto[currCripto]
        Assertions.assertEquals(BigDecimal(0),saldo)
    }
}