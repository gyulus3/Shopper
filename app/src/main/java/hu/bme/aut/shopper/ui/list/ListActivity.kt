package hu.bme.aut.shopper.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.shopper.ui.list.adapter.ShoppingListItemAdapter

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {
    lateinit var shoppingListItemAdapter: ShoppingListItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}