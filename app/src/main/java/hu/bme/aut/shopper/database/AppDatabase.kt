package hu.bme.aut.shopper.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.shopper.model.db.ShoppingListItem

@Database(entities = [ShoppingListItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingListItemDAO(): ShoppingListItemDAO
}