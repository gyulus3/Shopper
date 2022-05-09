package hu.bme.aut.shopper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.repository.ShoppingListItemRetrofitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val shoppingListItemRetrofitRepository: ShoppingListItemRetrofitRepository,
) : ViewModel() {

    private val itemId = savedStateHandle.get<Long>("id")!!
    val item = MutableLiveData<ShoppingListItem>()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                item.value = shoppingListItemRetrofitRepository.getById(itemId)
            } catch (e: Exception) {
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            try {
                shoppingListItemRetrofitRepository.delete(itemId)
            } catch (e: Exception) {

            }
        }
    }
}