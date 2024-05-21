package cc.green

interface IStore {
    fun HasEnoughInventory(product: Product, quantity: Int): Boolean
    fun RemoveInventory(product: Product, quantity: Int)
    fun AddInventory(product: Product, quantity: Int)
    fun GetInventory(product: Product): Int
}

class Store : IStore {
    val _inventory = mutableMapOf<Product, Int>();

    override fun HasEnoughInventory(product: Product, quantity: Int): Boolean {
        return GetInventory(product) >= quantity;
    }

    override fun RemoveInventory(product: Product, quantity: Int) {
        if (!HasEnoughInventory(product, quantity)) {
            throw Exception("Not enough inventory");
        }

        _inventory[product] = _inventory[product]!! - quantity;
    }

    override fun AddInventory(product: Product, quantity: Int) {
        if (_inventory.containsKey(product)) {
            _inventory[product] = _inventory[product]!! + quantity;
        } else {
            _inventory.put(product, quantity);
        }
    }

    override fun GetInventory(product: Product): Int {
        val productExists = _inventory.get(product);
        return productExists ?: 0
    }
}

enum class Product {
    Shampoo, Book
}

class Customer {
    fun Purchase(store: Store, product: Product, quantity: Int): Boolean {
        if (!store.HasEnoughInventory(product, quantity)) {
            return false;
        }

        store.RemoveInventory(product, quantity);

        return true;
    }
}