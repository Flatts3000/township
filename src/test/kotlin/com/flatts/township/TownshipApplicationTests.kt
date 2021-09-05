package com.flatts.township

import com.flatts.township.models.SupplyImpl
import com.flatts.township.models.SupplyPileImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TownshipApplicationTests {

    @Test
    fun test_coerceIn() {
        val pile = SupplyPileImpl(SupplyImpl())
        pile.quantity = 198.0
        pile.pileSize = 200
        val i = 4.0

        pile.updateQuantity(i)
        
        Assertions.assertEquals(200.0, pile.quantity)
    }
}
