package school.cesar.criptocorretora.services

class CompraService(
    private val usuarioService: UsuarioService,
    private val criptoService: CriptoService
) {

    fun comprar(idUsuario: Long, idCripto: Long) {
        val usuario = usuarioService.buscarPorId(idUsuario)
        val cripto = criptoService
    }

}