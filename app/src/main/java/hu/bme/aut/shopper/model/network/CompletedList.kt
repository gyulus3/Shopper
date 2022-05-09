package hu.bme.aut.shopper.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompletedList(
    val items: List<CompletedListItem>
)
