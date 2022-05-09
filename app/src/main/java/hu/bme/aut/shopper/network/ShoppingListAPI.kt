package hu.bme.aut.shopper.network

import hu.bme.aut.shopper.model.network.CompletedList
import hu.bme.aut.shopper.model.network.CreateListItem
import hu.bme.aut.shopper.model.network.ShoppingListResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ShoppingListAPI {
    @POST("tasks")
    suspend fun createNewItem(@Body shoppingListItem: CreateListItem, @Query("project_id") projectId: String, @Header("Authorization") token: String): Response<Unit>

    @GET("tasks")
    suspend fun getItems(@Query("project_id") projectId: String, @Header("Authorization") token: String): List<ShoppingListResult>

    @GET("tasks/{id}")
    suspend fun getItemById(@Path("id") itemId: Long, @Query("project_id") projectId: String, @Header("Authorization") token: String): ShoppingListResult

    @POST("tasks/{id}")
    suspend fun updateItem(@Path("id") itemId: Long, @Body shoppingListItem: CreateListItem, @Query("project_id") projectId: String, @Header("Authorization") token: String): Response<Unit>

    @POST("tasks/{id}/close")
    suspend fun approveItem(@Path("id") itemId: Long, @Query("project_id") projectId: String, @Header("Authorization") token: String): Response<Unit>

    @DELETE("tasks/{id}")
    suspend fun deleteItem(@Path("id") itemId: Long, @Query("project_id") projectId: String, @Header("Authorization") token: String): Response<Unit>

    @GET("https://api.todoist.com/sync/v8/completed/get_all")
    suspend fun getAllCompleted(@Query("project_id") projectId: String, @Header("Authorization") token: String): CompletedList
}