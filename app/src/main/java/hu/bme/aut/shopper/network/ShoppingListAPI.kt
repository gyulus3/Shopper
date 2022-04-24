package hu.bme.aut.shopper.network

import hu.bme.aut.shopper.model.network.CreateListItem
import hu.bme.aut.shopper.model.network.ShoppingListResult
import retrofit2.Call
import retrofit2.http.*

interface ShoppingListAPI {
    @POST("/tasks")
    fun createNewItem(@Body shoppingListItem: CreateListItem): Call<ShoppingListResult>

    @GET("/tasks")
    fun getItems(): Call<ShoppingListResult>

    @GET("/tasks/{id}")
    fun getItemById(@Path("id") itemId: Long): Call<ShoppingListResult>

    @PUT("/tasks/{id}")
    fun updateItem(@Path("id") itemId: Long): Call<ShoppingListResult>

    @POST("/tasks/{id}/close")
    fun approveItem(@Path("id") itemId: Long): Call<ShoppingListResult>

    @DELETE("/tasks/{id}")
    fun deleteItem(@Path("id") itemId: Long): Call<ShoppingListResult>
}