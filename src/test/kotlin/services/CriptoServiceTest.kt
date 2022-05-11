package services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.services.CriptoService
import school.cesar.criptocorretora.validadores.CriptoValidador
import java.math.BigDecimal

class CriptoServiceTest {
    private val criptoBuilder: CriptoBuilder = CriptoBuilder()
    private val criptoValidator: CriptoValidador = CriptoValidador()
    private val criptoRepositoryMock: CriptoRepository = mockk<CriptoRepository>()
    private val criptoRepository: CriptoRepository = CriptoRepository()
    private val criptoServiceMock: CriptoService = CriptoService(
        criptoBuilder,
        criptoValidator,
        criptoRepositoryMock
    )
    private val criptoService: CriptoService = CriptoService(criptoBuilder, criptoValidator, criptoRepository)
    private val cripto: Cripto = Cripto(0, "Santoastolfo", BigDecimal(2))

    @Test
    fun `Deve falhar ao buscar id inexistente`() {
        assertThrows<RuntimeException> {
            every { criptoRepositoryMock.buscarPeloId(0) } returns throw CriptoInvalidaException("Id não existente")
            criptoServiceMock.buscarPorId(0)
        }.also {
            Assertions.assertEquals(
                "Id não existente",
                it.message
            )
        }
    }

    @Test
    fun `Deve retornar um cripto a partir do id`() {
        every { criptoRepositoryMock.buscarPeloId(0) } returns cripto
        Assertions.assertEquals(
            cripto.toString(),
            criptoServiceMock.buscarPorId(0).toString()
        )
    }

    @Test
    fun `Deve criar um cripto e adicionar ao repositorio`() {
        criptoService.add("astolfomatos", BigDecimal(2))
        val foundCripto = criptoService.buscarPorId(0)
        Assertions.assertEquals(
            foundCripto.toString(),
            criptoBuilder.buildCripto("astolfomatos", BigDecimal(2)).copy(id = 0).toString()
        )
    }

    @Test
    fun `Deve inserir varios criptos e persistir`() {
        val criptoList:ArrayList<Cripto> = ArrayList<Cripto>()

        for (i in 0..19) {
            val name = "Georgenes-$i"
            val cotacao = BigDecimal(2 * i)
            val newCripto = Cripto(i.toLong(),name,cotacao)
            criptoService.add(name,cotacao)
        }

        criptoList.map {
            Assertions.assertEquals(
                it.toString(),
                criptoService.buscarPorId(it.id).toString()
            )
        }
    }
}
