package hu.bme.aut.shopper.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.repository.ShoppingListItemRepository
import javax.inject.Inject

class ListViewModel
@Inject constructor(
    private val repository: ShoppingListItemRepository
) : ViewModel() {

    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>> {
        return repository.getAllShoppingListItem()
    }

    fun createShoppingListItem(shoppingListItem: ShoppingListItem) {
        repository.insert(shoppingListItem)
    }
}