package hu.bme.aut.shopper.database

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.shopper.model.db.ShoppingListItem

@Dao
interface ShoppingListItemDAO {
    @Query("SELECT * FROM shoppingItem WHERE completed = 'false'")
    fun getAllListItem(): LiveData<List<ShoppingListItem>>
    @Query("DELETE FROM shoppingItem")
    fun deleteAll(): Void
    @Insert
    fun addAll(shoppingListItems: List<ShoppingListItem>): Void
    @Query("SELECT * FROM shoppingItem WHERE completed = 'true'")
    fun getApprovedItems(): LiveData<List<ShoppingListItem>>
    @Query("SELECT * FROM shoppingItem WHERE id = :id")
    fun getById(id: Long): ShoppingListItem
}