package hu.bme.aut.shopper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.repository.ShoppingListItemRepository
import hu.bme.aut.shopper.repository.ShoppingListItemRetrofitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val shoppingListItemRepository: ShoppingListItemRepository,
    private val shoppingListItemRetrofitRepository: ShoppingListItemRetrofitRepository
) : ViewModel() {
    val items = MutableLiveData(emptyList<ShoppingListItem>())
    private val showApproved = MutableLiveData(false)
    init {
        load()
    }

    fun approve(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch {
            try {
                shoppingListItemRetrofitRepository.approve(shoppingListItem.id!!)
                items.value = shoppingListItemRetrofitRepository.getAllShoppingListItem()
            } catch (e: Exception) {}
        }
    }

    fun switchShowApproved() {
        showApproved.value = showApproved.value!!.not()
    }

    fun load() {
        viewModelScope.launch {
            if(showApproved.value == false) {
                try {
                    val result = shoppingListItemRetrofitRepository.getAllShoppingListItem()
                    items.value = result
                    shoppingListItemRepository.deleteAll()
                    shoppingListItemRepository.addAll(result)
                } catch (e: Exception) {
                    shoppingListItemRepository.getAllShoppingListItem().value
                }
            } else {
                try {
                    val result = shoppingListItemRetrofitRepository.getCompletedListItem()
                    items.value = result
                    shoppingListItemRepository.deleteAll()
                    shoppingListItemRepository.addAll(result)
                } catch (e: Exception) { shoppingListItemRepository.getCompletedShoppingListItem().value
            }
        }
    }
}
}
