package hu.bme.aut.shopper.model.network

import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class ShoppingListResult(
    val id: Double,
    val content: String,
    val description: String,
    val completed: Boolean,
    val project_id: Double,
    val created: LocalDate,
)