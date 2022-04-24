package hu.bme.aut.shopper.util
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MoshiConverter {
    private val _moshiBuilder = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun listToJson(list: List<String>): String = _moshiBuilder
        .toJson(list)

    @TypeConverter
    fun jsonToList(json: String): List<String> = _moshiBuilder
        .fromJson<List<String>>(json)
        .orEmpty()

    @TypeConverter
    fun jsonToIntList(json: String): List<Int> = _moshiBuilder
        .fromJson<List<Int>>(json)
        .orEmpty()

    @TypeConverter
    fun intListToJson(list: List<Int>): String = _moshiBuilder
        .toJson(list)
}

inline fun <reified T> Moshi.fromJson(json: String) = this.adapter(T::class.java).fromJson(json)

inline fun <reified T> Moshi.toJson(value: T): String = this.adapter(T::class.java).toJson(value)