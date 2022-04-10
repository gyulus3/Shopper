package hu.bme.aut.shopper.database

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.shopper.model.db.ShoppingListItem

@Dao
interface ShoppingListItemDAO {
    @Query("SELECT * FROM shoppingItem WHERE completed = 'false'")
    fun getAllListItem(): LiveData<List<ShoppingListItem>>
    @Query("SELECT * FROM shoppingItem WHERE completed = 'true'")
    fun getApprovedItems(): LiveData<List<ShoppingListItem>>
    @Query("SELECT * FROM shoppingItem WHERE id = :id")
    fun getById(id: Long): LiveData<ShoppingListItem>

    @Insert
    fun insert(shoppingListItem: ShoppingListItem)
    @Update
    fun update(shoppingListItem: ShoppingListItem)
    @Query("UPDATE shoppingItem SET completed = 'true' WHERE id = :id")
    fun setItemApproved(id: Long): LiveData<ShoppingListItem>
    @Delete
    fun delete(shoppingListItem: ShoppingListItem)

}