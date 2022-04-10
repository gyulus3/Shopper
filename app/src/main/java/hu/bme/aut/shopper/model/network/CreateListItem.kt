package hu.bme.aut.shopper.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateListItem(
    var content: String,
    var description: String?,
)
