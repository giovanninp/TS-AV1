package school.cesar.criptocorretora.validadores

import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException

class CriptoValidador {

    fun valida(cripto: Cripto) {
        validaCamposObrigatorios(cripto)
    }

    private fun validaCamposObrigatorios(cripto: Cripto) {
        if (cripto.nome.isBlank()) {
            throw UsuarioInvalidoException("O campo nome deve ser preenchido")
        }
    }
}