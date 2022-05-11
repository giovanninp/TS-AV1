package school.cesar.criptocorretora.validadores

import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil

class UsuarioValidator(
    private val cpfUtil: CPFUtil,
    private val emailUtil: EmailUtil,
    private val senhaUtil: SenhaUtil
) {

    fun valida(usuario: Usuario) {
        validaCamposObrigatorios(usuario)
        validaTamanhoCampos(usuario)
        validaFormatoCampos(usuario)
    }

    private fun validaCamposObrigatorios(usuario: Usuario) {
        if (usuario.nome.isBlank()) {
            throw UsuarioInvalidoException("O nome deve ser preenchido")
        }

        if (usuario.cpf.isBlank()) {
            throw UsuarioInvalidoException("O cpf deve ser preenchido")
        }

        if (usuario.email.isBlank()) {
            throw UsuarioInvalidoException("O e-mail deve ser preenchido")
        }

        if (usuario.senha.isBlank()) {
            throw UsuarioInvalidoException("O senha deve ser preenchido")
        }
    }

    private fun validaTamanhoCampos(usuario: Usuario) {
        if (usuario.nome.length > 200) {
            throw UsuarioInvalidoException("O campo nome deve ter menos de 200 caracteres")
        }

        if (usuario.cpf.length != 11) {
            throw UsuarioInvalidoException("O campo cpf deve ter 11 caracteres numericos")
        }

        if (usuario.senha.length !in 8..15) {
            throw UsuarioInvalidoException("O campo confirmação senha deve ter entre 8 e 15 caracteres")
        }
    }

    private fun validaFormatoCampos(usuario: Usuario) {
        if (!cpfUtil.isCPF(usuario.cpf)) {
            throw UsuarioInvalidoException("O cpf é invalido")
        }

        if (!emailUtil.isEmailValido(usuario.email)) {
            throw UsuarioInvalidoException("O a emal deve seguir o formato palavra@palavra.palavra")
        }

        if (!senhaUtil.isFormatoOK(usuario.senha)) {
            throw UsuarioInvalidoException("O a senha deve conter numeros, letras maisculas e minusculas")
        }
    }
}
