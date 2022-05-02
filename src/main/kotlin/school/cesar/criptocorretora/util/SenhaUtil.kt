package school.cesar.criptocorretora.util

import java.math.BigInteger
import java.security.MessageDigest

class SenhaUtil {

    fun isFormatoOK(senha: String): Boolean {
        var contemLetraMinuscula = false
        var contemLetraMaiscula = false
        var contemNumero = false

        for (letra in senha) {
            contemLetraMinuscula = contemLetraMinuscula || temLetraMiniscula(letra)
            contemLetraMaiscula = contemLetraMaiscula || temLetraMaiscula(letra)
            contemNumero = contemNumero || temNumero(letra)
        }

        return contemLetraMinuscula && contemLetraMaiscula && contemNumero
    }

    private fun temNumero(letra: Char): Boolean {
        var contemNumero1 = false
        if (letra in '0'..'9') {
            contemNumero1 = true
        }
        return contemNumero1
    }

    private fun temLetraMaiscula(letra: Char): Boolean {
        var contemLetraMaiscula1 = false
        if (letra in 'A'..'Z') {
            contemLetraMaiscula1 = true
        }
        return contemLetraMaiscula1
    }

    private fun temLetraMiniscula(letra: Char): Boolean {
        var contemLetraMinuscula1 = false
        if (letra in 'a'..'z') {
            contemLetraMinuscula1 = true
        }
        return contemLetraMinuscula1
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")

        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}
