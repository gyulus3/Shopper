package hu.bme.aut.shopper.ui.fragments

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import hu.bme.aut.shopper.databinding.ListItemRowBinding
import hu.bme.aut.shopper.model.db.ShoppingListItem

class ShoppingListItemRecyclerViewAdapter(
    private val navigate: (ShoppingListItem) -> Unit,
    private val approve: (ShoppingListItem) -> Unit,
) : ListAdapter<ShoppingListItem, ShoppingListItemRecyclerViewAdapter.ShoppingListViewHolder>(ListItemDiffCallback()) {
    class ShoppingListViewHolder(
        private val binding: ListItemRowBinding,
        private val navigate: (ShoppingListItem) -> Unit,
        private val approve: (ShoppingListItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listItem: ShoppingListItem) {
            binding.shoppingListItem = listItem
            binding.item.setOnClickListener { navigate(listItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoppingListViewHolder(
            ListItemRowBinding.inflate(layoutInflater, parent, false),
            navigate,
            approve
        )
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ListItemDiffCallback : DiffUtil.ItemCallback<ShoppingListItem>() {
    override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return oldItem == newItem
    }
}