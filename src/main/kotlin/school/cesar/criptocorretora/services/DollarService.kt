package school.cesar.criptocorretora.services

import java.math.BigDecimal

class DollarService {

    fun pegarValorDollarAtual(): BigDecimal =
        BigDecimal((4..5).random())
}
