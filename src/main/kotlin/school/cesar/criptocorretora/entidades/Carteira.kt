package school.cesar.criptocorretora.entidades

data class Carteira(
    val cripto: MutableList<Pair<Cripto, Long>>
)
