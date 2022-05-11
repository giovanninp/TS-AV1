package repositories

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.repositories.UsuarioRepository
import java.math.BigDecimal

class UsuarioRepositoryTest {
    private val usuarioRepository: UsuarioRepository = UsuarioRepository()
    private val usuario: Usuario = Usuario(
        0,
        "28005625014",
        "Arnoldinho",
        "arnoldinho@maromba.com",
        "ArnoldS@123",
        Carteira()
    )

    @Test
    fun `Deve retornar null ao buscar um usuario inexistente`() {
        Assertions.assertEquals(
            null,
            usuarioRepository.buscarPorId(0)
        )
    }

    @Test
    fun `Deve adicionar e retornar novo usuario por id`() {
        usuarioRepository.add(usuario)
        val foundUser = usuarioRepository.buscarPorId(0)
        Assertions.assertEquals(usuario.toString(), foundUser.toString())
    }

    @Test
    fun `Deve inserir usuarios criptos e persistir`() {
        val userList:ArrayList<Usuario> = ArrayList<Usuario>()

        for (i in 0..20) {
            val newUser = Usuario(
                i.toLong(),
                "97387464006",
                "Gregorio $i o",
                "gregorio@$i.com",
                "SenhaSegura@1",
                Carteira()
            )
            userList.add(newUser)
        }

        userList.map {
            usuarioRepository.add(it)
        }

        userList.map {
            Assertions.assertEquals(
                it.toString(),
                usuarioRepository.buscarPorId(it.id).toString()
            )
        }
    }
}