package hu.bme.aut.shopper.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.shopper.database.AppDatabase
import hu.bme.aut.shopper.database.ShoppingListItemDAO

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "shopper"
    ).build()

    @Provides
    fun provideShoppingListItemDao(appDatabase: AppDatabase): ShoppingListItemDAO {
        return appDatabase.shoppingListItemDAO()
    }
}