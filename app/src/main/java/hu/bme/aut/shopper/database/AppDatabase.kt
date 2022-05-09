package hu.bme.aut.shopper.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.shopper.model.db.ShoppingListItem
import hu.bme.aut.shopper.util.MoshiConverter

@Database(entities = [ShoppingListItem::class], version = 1, exportSchema = false)
@TypeConverters(MoshiConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingListItemDAO(): ShoppingListItemDAO
}