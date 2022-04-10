package hu.bme.aut.shopper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.shopper.model.db.ShoppingListItem

@Database(entities = [ShoppingListItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingListItemDAO(): ShoppingListItemDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "shoppingListItem.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}