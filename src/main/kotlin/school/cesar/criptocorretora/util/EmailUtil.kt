package school.cesar.criptocorretora.util

class EmailUtil {

    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    fun isEmailValido(email: String): Boolean =
        EMAIL_REGEX.toRegex().matches(email)
}
