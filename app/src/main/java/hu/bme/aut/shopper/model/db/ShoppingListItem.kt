package hu.bme.aut.shopper.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.shopper.model.network.CreateListItem

@Entity(tableName = "ShoppingItem")
data class ShoppingListItem (
        @PrimaryKey
        var id: Long?,
        var content: String,
        val description: String? = "",
        var completed: Boolean,
        var created: String,
)

fun ShoppingListItem.toCreateListItem() = CreateListItem(
        content = content,
        description = description,
)