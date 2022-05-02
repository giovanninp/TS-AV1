package school.cesar.criptocorretora.entidades

data class Usuario(
    val id: Long,
    val cpf: Long,
    val nome: String,
    val carteira: MutableList<Cripto>
)
