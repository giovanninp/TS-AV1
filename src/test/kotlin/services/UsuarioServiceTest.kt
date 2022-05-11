package services

import io.mockk.MockK
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.repositories.UsuarioRepository
import school.cesar.criptocorretora.services.UsuarioService
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil
import school.cesar.criptocorretora.validadores.UsuarioValidator

class UsuarioServiceTest {
    private val usuarioValidator:UsuarioValidator = UsuarioValidator(
        CPFUtil(),
        EmailUtil(),
        SenhaUtil(),
    )
    private val usuarioRepository: UsuarioRepository = UsuarioRepository()
    private val usuarioRepositoryMock: UsuarioRepository = mockk<UsuarioRepository>()
    private val usuarioService: UsuarioService = UsuarioService(usuarioValidator,usuarioRepository)
    private val usuarioServiceMock: UsuarioService = UsuarioService(
        usuarioValidator,
        usuarioRepositoryMock
    )
    private val usuario: Usuario = Usuario(
        0,
        "28005625014",
        "Arnoldinho",
        "arnoldinho@maromba.com",
        "ArnoldS@123",
        Carteira()
    )

    @Test
    fun `Deve falhar ao buscar id inexistente`() {
        assertThrows<RuntimeException> {
            every { usuarioRepositoryMock.buscarPorId(0) } returns throw UsuarioInvalidoException("Id não existente")
            usuarioServiceMock.buscarPorId(0)
        }.also {
            Assertions.assertEquals(
                "Id não existente",
                it.message
            )
        }
    }

    @Test
    fun `Deve retornar um usuario a partir do id`() {
        every { usuarioRepositoryMock.buscarPorId(0) } returns usuario
        Assertions.assertEquals(
            usuario.toString(),
            usuarioServiceMock.buscarPorId(0).toString()
        )
    }

    @Test
    fun `Deve retornar um usuario diferente de null`() {
        usuarioService.adicionar(usuario)
        Assertions.assertNotEquals(
            null,
            usuarioService.buscarPorId(0).toString()
        )
    }

    @Test
    fun `Deve criar um cripto e adicionar ao repositorio`() {
        usuarioService.adicionar(usuario)
        val foundUser = usuarioService.buscarPorId(0)
        Assertions.assertEquals(foundUser.toString(), usuario.toString())
    }
}