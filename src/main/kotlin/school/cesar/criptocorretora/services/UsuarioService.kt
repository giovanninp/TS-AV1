package school.cesar.criptocorretora.services

import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.repositories.UsuarioRepository
import school.cesar.criptocorretora.validadores.UsuarioValidator

class UsuarioService(
    private val usuarioValidator: UsuarioValidator,
    private val usuarioRepository: UsuarioRepository
) {

    fun add(usuario: Usuario) {
        usuarioValidator.valida(usuario)
        usuarioRepository.add(usuario)
    }

    fun buscarPorId(id: Long) =
        usuarioRepository.buscarPorId(id)
}
