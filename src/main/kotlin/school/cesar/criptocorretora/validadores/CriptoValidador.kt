package school.cesar.criptocorretora.validadores

import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.CamposObrigatorioNaoPreenchidoException

class CriptoValidador {

    fun valida(cripto: Cripto) {
        validaCamposObrigatorios(cripto)
    }

    fun validaCamposObrigatorios(cripto: Cripto) {
        if (cripto.nome == "") {
            throw CamposObrigatorioNaoPreenchidoException("O campo nome deve ser preenchido")
        }
    }
}