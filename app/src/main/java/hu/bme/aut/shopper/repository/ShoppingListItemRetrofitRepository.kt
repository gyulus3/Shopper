package hu.bme.aut.shopper.repository

import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.model.network.CreateListItem
import hu.bme.aut.shopper.model.network.toListItem
import hu.bme.aut.shopper.network.ShoppingListAPI
import javax.inject.Inject

class ShoppingListItemRetrofitRepository @Inject constructor(
    private val shoppingListAPI: ShoppingListAPI
) {
    private var authorizationHeader = "Bearer ccaab3bf42c14bdb2e76b0d8ea9872a7f7cc604e"
    private var projectId = "2291150363"

    suspend fun getAllShoppingListItem() : List<ShoppingListItem> {
        return shoppingListAPI.getItems(projectId, authorizationHeader).map {
            it.toListItem()
        }
    }

    suspend fun insert(shoppingListItem: CreateListItem) {
        shoppingListAPI.createNewItem(shoppingListItem, projectId, authorizationHeader)
    }

    suspend fun getById(id: Long) : ShoppingListItem {
        return shoppingListAPI.getItemById(id, projectId, authorizationHeader).toListItem()
    }

    suspend fun delete(id: Long) {
        shoppingListAPI.deleteItem(id, projectId, authorizationHeader)
    }

    suspend fun update(id: Long, createListItem: CreateListItem) {
        shoppingListAPI.updateItem(id, createListItem, projectId, authorizationHeader)
    }

    suspend fun approve(id: Long) {
        shoppingListAPI.approveItem(id, projectId, authorizationHeader)
    }

    suspend fun getCompletedListItem(): List<ShoppingListItem> {
        return shoppingListAPI.getAllCompleted(projectId, authorizationHeader).items.map {
            it.toListItem()
        }
    }
}