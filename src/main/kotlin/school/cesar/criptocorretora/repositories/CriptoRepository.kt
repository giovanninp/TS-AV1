package school.cesar.criptocorretora.repositories

import school.cesar.criptocorretora.entidades.Cripto

class CriptoRepository {

    private val criptos = mutableListOf<Cripto>()

    fun add(cripto: Cripto) {
        criptos.add(cripto)
    }

    fun excluir(cripto: Cripto) {
        criptos.remove(cripto)
    }

    fun buscarPeloId(id: Long) =
        criptos.firstOrNull { it.id == id }

    fun buscarPeloNome(nome: String) =
        criptos.firstOrNull { it.nome == nome }
}
