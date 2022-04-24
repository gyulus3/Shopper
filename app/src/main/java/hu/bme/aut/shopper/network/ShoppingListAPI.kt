package hu.bme.aut.shopper.network

import hu.bme.aut.shopper.model.network.CreateListItem
import hu.bme.aut.shopper.model.network.ShoppingListResult
import retrofit2.Call
import retrofit2.http.*

interface ShoppingListAPI {
    @POST("/rest/v1/tasks")
    fun createNewItem(@Body shoppingListItem: CreateListItem): Call<ShoppingListResult>

    @GET("/rest/v1/tasks")
    fun getItems(): Call<ShoppingListResult>

    @GET("/rest/v1/tasks/{id}")
    fun getItemById(@Path("id") itemId: Long): Call<ShoppingListResult>

    @PUT("/rest/v1/tasks/{id}")
    fun updateItem(@Path("id") itemId: Long): Call<ShoppingListResult>

    @POST("/rest/v1/tasks/{id}/close")
    fun approveItem(@Path("id") itemId: Long): Call<ShoppingListResult>

    @DELETE("/rest/v1/tasks/{id}")
    fun deleteItem(@Path("id") itemId: Long): Call<ShoppingListResult>
}