package hu.bme.aut.shopper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.shopper.R
import hu.bme.aut.shopper.databinding.FragmentUpdateBinding
import hu.bme.aut.shopper.viewmodel.UpdateViewModel

@AndroidEntryPoint
class UpdateFragment: Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val viewModel: UpdateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btDismiss.setOnClickListener { view ->
            view.findNavController().navigate(R.id.list)
        }
        binding.btAdd.setOnClickListener { view ->
            viewModel.save()
            view.findNavController().navigate(R.id.list)
        }

        return binding.root
    }
}