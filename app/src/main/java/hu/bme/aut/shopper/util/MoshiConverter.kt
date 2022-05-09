package hu.bme.aut.shopper.util
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.shopper.model.db.ShoppingListItem
import org.json.JSONObject

class MoshiConverter {
    private val _moshiBuilder = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun fromShoppingListItem(shoppingListItem: ShoppingListItem): String {
        return JSONObject().apply {
            put("id", shoppingListItem.id)
            put("content", shoppingListItem.content)
            put("completed", shoppingListItem.completed)
            put("created", shoppingListItem.created)
            put("description", shoppingListItem.description)
        }.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toShoppingListItem(shoppingListItem: String): ShoppingListItem {
        val json = JSONObject(shoppingListItem)
        return ShoppingListItem(
            json.getLong("id"),
            json.getString("content"),
            json.getString("description"),
            json.getBoolean("completed"),
            json.getString("created")
        )
    }
}

inline fun <reified T> Moshi.fromJson(json: String) = this.adapter(T::class.java).fromJson(json)

inline fun <reified T> Moshi.toJson(value: T): String = this.adapter(T::class.java).toJson(value)