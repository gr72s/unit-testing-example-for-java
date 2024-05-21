package cc.green.test

import cc.green.Customer
import cc.green.Product
import cc.green.Store
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class T1 {

    @Test
    fun classical1() {
        // arrange
        val store = Store() // collaborator
        store.AddInventory(Product.Shampoo, 10)
        val customer = Customer() // SUT
        // act
        val succ = customer.Purchase(store, Product.Shampoo, 5) // MUT
        // assert
        assertTrue { succ }
        assertEquals(5, store.GetInventory(Product.Shampoo))
    }

    @Test
    fun london1() {
        // arrange
        val store = Mockito.mock(Store::class.java)
        Mockito.`when`(store.HasEnoughInventory(Product.Shampoo, 5)).thenReturn(true)
        // act
        val customer = Customer()
        // assert
        val success = customer.Purchase(store, Product.Shampoo, 5)
        assertTrue { success }
        Mockito.verify(store, Mockito.times(1)).RemoveInventory(Product.Shampoo, 5)
    }

    @Test
    fun london2() {
        // arrange
        val store = Mockito.mock(Store::class.java)
        Mockito.`when`(store.HasEnoughInventory(Product.Shampoo, 5)).thenReturn(false)
        // act
        val customer = Customer()
        // assert
        val success = customer.Purchase(store, Product.Shampoo, 5)
        assertTrue { !success }
        Mockito.verify(store, Mockito.times(0)).RemoveInventory(Product.Shampoo, 5)
    }


}