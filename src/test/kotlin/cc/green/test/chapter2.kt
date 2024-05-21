package cc.green.test

import cc.green.Customer
import cc.green.Product
import cc.green.Store
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class Classical {

    @Test
    fun Purchase_succeeds_when_enough_inventory() {
        // arrange
        val store = Store() // collaborator
        store.AddInventory(Product.Shampoo, 10)
        val customer = Customer() // SUT
        // act
        val success = customer.Purchase(store, Product.Shampoo, 5) // MUT
        // assert
        assertTrue(success)
        assertEquals(5, store.GetInventory(Product.Shampoo))
    }

    @Test
    fun Purchase_fails_when_not_enough_inventory() {
        // arrange
        val store = Store() // collaborator
        store.AddInventory(Product.Shampoo, 10)
        val customer = Customer() // SUT
        // act
        val success = customer.Purchase(store, Product.Shampoo, 15) // MUT
        // assert
        assertFalse(success)
        assertEquals(5, store.GetInventory(Product.Shampoo))
    }

}

class London {
    @Test
    fun Purchase_succeeds_when_enough_inventory() {
        // arrange
        val store = Mockito.mock(Store::class.java)
        Mockito.`when`(store.HasEnoughInventory(Product.Shampoo, 5)).thenReturn(true)
        val customer = Customer()
        // act
        val success = customer.Purchase(store, Product.Shampoo, 5)
        // assert
        assertTrue { success }
        Mockito.verify(store, Mockito.times(1)).RemoveInventory(Product.Shampoo, 5)
    }

    @Test
    fun Purchase_fails_when_not_enough_inventory() {
        // arrange
        val store = Mockito.mock(Store::class.java)
        Mockito.`when`(store.HasEnoughInventory(Product.Shampoo, 5)).thenReturn(false)
        val customer = Customer()
        // act
        val success = customer.Purchase(store, Product.Shampoo, 5)
        // assert
        assertFalse(success)
        Mockito.verify(store, Mockito.times(0)).RemoveInventory(Product.Shampoo, 5)
    }

}