package hu.bme.aut.shopper.ui.details

import androidx.lifecycle.ViewModel
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.repository.ShoppingListItemRepository
import javax.inject.Inject

class DetailsViewModel
@Inject
constructor(
    private val repository: ShoppingListItemRepository
) : ViewModel() {
    fun approveShoppingListItem(shoppingListItem: ShoppingListItem) {
        repository.update(shoppingListItem)
    }
}