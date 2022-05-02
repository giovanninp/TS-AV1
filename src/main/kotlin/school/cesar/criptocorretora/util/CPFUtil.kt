package school.cesar.criptocorretora.util

class CPFUtil {

    fun limpaCPF(cpf: String) = cpf
        .replace(".", "")
        .replace("-", "")

    fun isCPF(document: String): Boolean {
        if (document.isEmpty()) return false

        val numbers = document.filter { it.isDigit() }.map {
            it.toString().toInt()
        }

        if (numbers.size != 11) return false

        //repeticao
        if (numbers.all { it == numbers[0] }) return false

        //digito 1
        val dv1 = calculaDigito1(numbers)

        val dv2 = calculaDigito2(numbers, dv1)

        return numbers[9] == dv1 && numbers[10] == dv2
    }

    internal fun calculaDigito2(numbers: List<Int>, dv1: Int): Int {
        val dv2 = ((0..8).sumOf { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
            if (it >= 10) 0 else it
        }
        return dv2
    }

    internal fun calculaDigito1(numbers: List<Int>): Int {
        val dv1 = ((0..8).sumOf { (it + 1) * numbers[it] }).rem(11).let {
            if (it >= 10) 0 else it
        }
        return dv1
    }

}