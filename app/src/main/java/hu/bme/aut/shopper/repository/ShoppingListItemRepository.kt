package hu.bme.aut.shopper.repository

import androidx.lifecycle.LiveData
import hu.bme.aut.shopper.database.ShoppingListItemDAO
import hu.bme.aut.shopper.model.db.ShoppingListItem

class ShoppingListItemRepository(private val shoppingListItemDAO: ShoppingListItemDAO){
    fun getAllShoppingListItem() : LiveData<List<ShoppingListItem>> {
        return shoppingListItemDAO.getAllListItem()
    }

    fun insert(shoppingListItem: ShoppingListItem) {
        shoppingListItemDAO.insert(shoppingListItem)
    }

    fun getById(id: Long) : LiveData<ShoppingListItem> {
        return shoppingListItemDAO.getById(id)
    }

    fun delete(shoppingListItem: ShoppingListItem) {
        shoppingListItemDAO.delete(shoppingListItem)
    }

    fun update(shoppingListItem: ShoppingListItem) {
        shoppingListItemDAO.update(shoppingListItem)
    }
}