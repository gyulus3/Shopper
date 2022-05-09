package hu.bme.aut.shopper.repository

import androidx.lifecycle.LiveData
import hu.bme.aut.shopper.database.ShoppingListItemDAO
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.model.network.CreateListItem
import javax.inject.Inject

class ShoppingListItemRepository @Inject constructor(
    private val shoppingListItemDAO: ShoppingListItemDAO
) {
    fun getAllShoppingListItem() : LiveData<List<ShoppingListItem>> {
        return shoppingListItemDAO.getAllListItem()
    }

    fun getCompletedShoppingListItem() : LiveData<List<ShoppingListItem>> {
        return shoppingListItemDAO.getApprovedItems()
    }

    fun addAll(shoppingListItems: List<ShoppingListItem>) {
        shoppingListItemDAO.addAll(shoppingListItems)
    }

    fun deleteAll() {
        shoppingListItemDAO.deleteAll()
    }

    fun getById(id: Long) : ShoppingListItem {
        return shoppingListItemDAO.getById(id)
    }
}