package hu.bme.aut.shopper.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.shopper.ui.list.adapter.ShoppingListItemAdapter

class ListActivity : AppCompatActivity() {
    lateinit var shoppingListItemAdapter: ShoppingListItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}