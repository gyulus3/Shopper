package hu.bme.aut.shopper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.shopper.R
import hu.bme.aut.shopper.databinding.FragmentDetailBinding
import hu.bme.aut.shopper.viewmodel.DetailsViewModel

@AndroidEntryPoint
class DetailFragment: Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editButton.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionToUpdate(
                    viewModel.item.value!!.content,
                    viewModel.item.value!!.description!!,
                    viewModel.item.value!!.id!!)
            )
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete()
            findNavController().navigate(R.id.list)
        }
    }
}