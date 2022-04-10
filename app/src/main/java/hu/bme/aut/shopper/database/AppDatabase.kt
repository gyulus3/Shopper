package hu.bme.aut.shopper.database

import androidx.room.Database
import hu.bme.aut.shopper.model.db.ShoppingListItem

@Database(entities = [ShoppingListItem::class], version = 1)
class AppDatabase {
}