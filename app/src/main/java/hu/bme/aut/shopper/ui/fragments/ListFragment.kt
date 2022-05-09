package hu.bme.aut.shopper.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.shopper.databinding.FragmentListBinding
import hu.bme.aut.shopper.viewmodel.ListViewModel

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private val adapter = ShoppingListItemRecyclerViewAdapter(
        {
            if(!it.completed) {
                findNavController().navigate(
                    ListFragmentDirections.actionToDetail(it.id!!)
                )
            }
        },
        {
            viewModel.approve(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.swApproved.setOnClickListener {
            viewModel.load()
        }

        binding.newButton.setOnClickListener {
            findNavController().navigate(
                ListFragmentDirections.actionToUpdate("", "", 0)
            )
        }

        with(binding) {
            listItems.adapter = adapter
            listItems.layoutManager = LinearLayoutManager(context)
        }

        viewModel.items.observe(viewLifecycleOwner) { adapter.submitList(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshLayout.setOnRefreshListener {
            viewModel.load()
            binding.refreshLayout.isRefreshing = false
        }

        binding.swApproved.setOnClickListener {
            viewModel.switchShowApproved()
            viewModel.load()
            binding.refreshLayout.isRefreshing = false
        }

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean { return false }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val swipedPosition = viewHolder.adapterPosition
                    viewModel.approve(viewModel.items.value?.get(swipedPosition)!!)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.listItems)

    }
}