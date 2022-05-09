package hu.bme.aut.shopper.model.network

import com.squareup.moshi.JsonClass
import hu.bme.aut.shopper.model.db.ShoppingListItem
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class ShoppingListResult(
    val id: Long,
    val content: String,
    val description: String? = "",
    val completed: Boolean,
    val created: String,
)

fun ShoppingListResult.toListItem() = ShoppingListItem(
    id = id,
    content = content,
    description = description,
    completed = completed,
    created = created
)