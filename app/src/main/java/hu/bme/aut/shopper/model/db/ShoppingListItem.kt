package hu.bme.aut.shopper.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "ShoppingItem")
data class ShoppingListItem (
        var id: Long?,
        var content: String?,
        var description: String?,
        var completed: Boolean,
        var project_id: Long?,
        var created: LocalDate?,
)