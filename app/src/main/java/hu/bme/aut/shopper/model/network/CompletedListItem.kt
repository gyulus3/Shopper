package hu.bme.aut.shopper.model.network

import com.squareup.moshi.JsonClass
import hu.bme.aut.shopper.model.db.ShoppingListItem
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class CompletedListItem(
    val id: Long,
    val content: String,
    val completed_date: String,
)

fun CompletedListItem.toListItem() = ShoppingListItem(
    id = id,
    content = content,
    description = "",
    completed = true,
    created = completed_date
)