package school.cesar.criptocorretora.repositories

import school.cesar.criptocorretora.entidades.Usuario

class UsarioRepository {

    private val usuarios = mutableListOf<Usuario>()

    fun add(usuario: Usuario) {
        usuarios.add(usuario)
    }
}
