package com.example.barteryuk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barteryuk.R
import com.example.barteryuk.adapter.BarterAdapter
import com.example.barteryuk.databinding.FragmentProfileBinding
import com.example.barteryuk.model.BarterItem

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BarterAdapter(ArrayList(), showOwner = false) { item ->
            val action = ProfileFragmentDirections.actionProfileFragmentToDetailFragment(item, true)
            findNavController().navigate(action)
        }

        binding.rvMyItems.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 3)
        binding.rvMyItems.adapter = adapter

        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.tvUserName.text = user.name
                binding.tvUserEmail.text = user.email
            }
        }

        viewModel.barterItems.observe(viewLifecycleOwner) { items ->
            val currentUserEmail = viewModel.currentUser.value?.email
            val userItems = items.filter { it.ownerEmail == currentUserEmail }
            adapter.updateData(ArrayList(userItems))
        }

        binding.btnAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addItemFragment)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_welcomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
