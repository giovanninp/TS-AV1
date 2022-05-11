package school.cesar.criptocorretora.services

import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.validadores.CriptoValidador
import java.math.BigDecimal

class CriptoService(
    private val criptoBuilder: CriptoBuilder,
    private val criptoValidador: CriptoValidador,
    private val criptoRepository: CriptoRepository
) {
    fun add(nome: String, contacaoInicialEmDollar: BigDecimal) {
        val cripto = criptoBuilder.buildCripto(nome, contacaoInicialEmDollar)
        criptoValidador.valida(cripto)
        criptoRepository.add(cripto)
    }

    fun buscarPorId(id: Long): Cripto =
        criptoRepository.buscarPeloId(id)
            ?: throw CriptoInvalidaException("Id não existente")
}
