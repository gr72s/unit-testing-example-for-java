package cc.green.test

import cc.green.Calculator
import cc.green.Customer
import cc.green.Product
import cc.green.Store
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue


class CalculatorTests {
    @Test
    fun Sum_of_two_numbers() {
        // arrange
        val first = 10.0
        val second = 20.0
        val sut = Calculator()
        // act
        val result: Double = sut.Sum(first, second)
        // assert
        assertEquals(30, result)
    }
}

/**
 * use constructor create fixtures
 */
class CustomerTestsUseConstructor {

    var store: Store
    var sut: Customer

    init {
        store = Store()
        store.AddInventory(Product.Shampoo, 10)
        sut = Customer()
    }

    @Test
    fun Purchase_succeeds_when_enough_inventory() {
        val success = sut.Purchase(store, Product.Shampoo, 5)

        assertTrue(success)
        assertEquals(5, store.GetInventory(Product.Shampoo))
    }

    @Test
    fun Purchase_fails_when_not_enough_inventory() {
        val success = sut.Purchase(store, Product.Shampoo, 15)

        assertFalse(success)
        assertEquals(10, store.GetInventory(Product.Shampoo))
    }

}

/**
 * use factory create fixtures
 */
class CustomerTestsUseFactory {
    @Test
    fun Purchase_succeeds_when_enough_inventory() {
        val store = CreateStoreWithInventory(Product.Shampoo, 10)
        val sut = CreateCustomer()

        val success = sut.Purchase(store, Product.Shampoo, 5)

        assertTrue(success)
        assertEquals(5, store.GetInventory(Product.Shampoo))
    }

    @Test
    fun Purchase_fails_when_not_enough_inventory() {
        val store = CreateStoreWithInventory(Product.Shampoo, 10)
        val sut = CreateCustomer()

        val success = sut.Purchase(store, Product.Shampoo, 15)

        assertFalse(success)
        assertEquals(10, store.GetInventory(Product.Shampoo))
    }

    private fun CreateStoreWithInventory(product: Product, quantity: Int): Store {
        val store = Store()
        store.AddInventory(product, quantity)
        return store
    }

    private fun CreateCustomer(): Customer {
        return Customer()
    }
}