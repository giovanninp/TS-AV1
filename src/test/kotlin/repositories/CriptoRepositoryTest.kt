package repositories

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.repositories.CriptoRepository
import java.math.BigDecimal
import java.util.Arrays

class CriptoRepositoryTest {
    private val criptoRepository: CriptoRepository = CriptoRepository()
    private val cripto: Cripto = Cripto(0,"Georgenes", BigDecimal(5))
    private val criptoBuilder = CriptoBuilder()

    @Test
    fun `Deve excluir um cripto`() {
        criptoRepository.add(cripto)
        Assertions.assertEquals(
            cripto.toString(),
            criptoRepository.buscarPeloId(0).toString()
        )
        criptoRepository.excluir(cripto)
        Assertions.assertEquals(null,criptoRepository.buscarPeloId(0))
    }

    @Test
    fun `Deve inserir varios criptos e persistir`() {
        val criptoList:ArrayList<Cripto> = ArrayList<Cripto>()

        for (i in 0..20) {
            val newCripto = criptoBuilder.buildCripto("Georgenes-$i",BigDecimal(2 * i))
            criptoList.add(newCripto)
        }

        criptoList.map {
            criptoRepository.add(it)
        }

        criptoList.map {
            Assertions.assertEquals(
                it.toString(),
                criptoRepository.buscarPeloId(it.id).toString()
            )
            Assertions.assertEquals(
                it.toString(),
                criptoRepository.buscarPeloNome(it.nome).toString()
            )
        }
    }

    @Test
    fun `Deve retornar cripto pelo nome`() {
        criptoRepository.add(cripto)
        Assertions.assertEquals(
            cripto.toString(),
            criptoRepository.buscarPeloNome(cripto.nome).toString()
        )
    }
}