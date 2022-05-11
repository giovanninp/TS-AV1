package validadores

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil
import school.cesar.criptocorretora.validadores.UsuarioValidator
import java.math.BigDecimal

class UsuarioValidatorTest {
    private val validator: UsuarioValidator = UsuarioValidator(
        cpfUtil = CPFUtil(),
        emailUtil = EmailUtil(),
        senhaUtil = SenhaUtil())
    private val usuario: Usuario = Usuario(
        1,
        "07803651437",
        "Jorge",
        "jorges@welliyngton.uol",
        "oncaBraba14",
        Carteira()
    )

    @Test
    fun `Deve falhar quando o nome for vazio`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(nome=""))
        }.also {
            Assertions.assertEquals("O nome deve ser preenchido", it.message)
        }
    }

    @Test
    fun `Deve falhar quando o cpf for vazio`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(cpf=""))
        }.also {
            Assertions.assertEquals("O cpf deve ser preenchido", it.message)
        }
    }

    @Test
    fun `Deve falhar quando o email for vazio`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(email=""))
        }.also {
            Assertions.assertEquals("O e-mail deve ser preenchido", it.message)
        }
    }

    @Test
    fun `Deve falhar quando o senha for vazio`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha=""))
        }.also {
            Assertions.assertEquals("O senha deve ser preenchido", it.message)
        }
    }

    @Test
    fun `Deve falhar quando nome for acima dos 200 caracteres`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(nome="a".repeat(201)))
        }.also {
            Assertions.assertEquals("O campo nome deve ter menos de 200 caracteres", it.message)
        }
    }

    @Test
    fun `Deve falhar quando cpf for acima dos 11 caracteres`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(cpf="0".repeat(12)))
        }.also {
            Assertions.assertEquals("O campo cpf deve ter 11 caracteres numericos", it.message)
        }
    }

    @Test
    fun `Deve falhar quando cpf for abaixo dos 11 caracteres`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(cpf="0".repeat(10)))
        }.also {
            Assertions.assertEquals("O campo cpf deve ter 11 caracteres numericos", it.message)
        }
    }

    @Test
    fun `Deve falhar quando a senha for acima dos 15 caracteres`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha="0".repeat(16)))
        }.also {
            Assertions.assertEquals("O campo confirmação senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }

    @Test
    fun `Deve falhar quando a senha for abaixo dos 8 caracteres`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha="0".repeat(7)))
        }.also {
            Assertions.assertEquals("O campo confirmação senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }

    @Test
    fun `Deve falhar quando o cpf conter um char nao numerico`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(cpf="000000000a0"))
        }.also {
            Assertions.assertEquals("O cpf é invalido", it.message)
        }
    }

    @Test
    fun `Deve falhar quando o email nao tiver o char '@'`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(email="emalebom.com"))
        }.also {
            Assertions.assertEquals("O a emal deve seguir o formato palavra@palavra.palavra", it.message)
        }
    }

    @Test
    fun `Deve falhar quando a senha for totalmente minuscula`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha="oncamalvadinha"))
        }.also {
            Assertions.assertEquals("O a senha deve conter numeros, letras maisculas e minusculas", it.message)
        }
    }

    @Test
    fun `Deve falhar quando a senha for totalmente maiuscula`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha="ONCAMALVADONA"))
        }.also {
            Assertions.assertEquals("O a senha deve conter numeros, letras maisculas e minusculas", it.message)
        }
    }

    @Test
    fun `Deve falhar quando a senha for composta apenas por numeros`() {
        assertThrows<RuntimeException> {
            validator.valida(usuario.copy(senha="0".repeat(9)))
        }.also {
            Assertions.assertEquals("O a senha deve conter numeros, letras maisculas e minusculas", it.message)
        }
    }
}