package hu.bme.aut.shopper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.shopper.model.network.CreateListItem
import hu.bme.aut.shopper.repository.ShoppingListItemRetrofitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val shoppingListItemRetrofitRepository: ShoppingListItemRetrofitRepository
): ViewModel() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val _content = savedStateHandle.get<String>("content")
    private val _description = savedStateHandle.get<String>("description")
    private val _id = savedStateHandle.get<Long>("id")

    val content = MutableLiveData("")
    val description = MutableLiveData("")
    val id = MutableLiveData<Long>()

    init {
        content.value = _content
        description.value = _description
        id.value = _id
    }

    fun save() {
        viewModelScope.launch {
            if (id.value!!.compareTo(0) == 0) {
                try {
                    shoppingListItemRetrofitRepository.insert(CreateListItem(content.value!!,  description.value!!))
                } catch (e :Exception) {}
            } else {
                try {
                    shoppingListItemRetrofitRepository.update(id.value!!, CreateListItem(content.value!!,  description.value!!))
                } catch (e: Exception) {}
            }
        }
    }


}