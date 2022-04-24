package hu.bme.aut.shopper.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "ShoppingItem")
data class ShoppingListItem (
        @PrimaryKey
        var id: Long?,
        var content: String?,
        var description: String?,
        var completed: Boolean,
        var project_id: Long?,
        var created: String?,
)