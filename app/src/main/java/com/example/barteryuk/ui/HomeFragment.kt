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
import com.example.barteryuk.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter untuk Rekomendasi (Horizontal)
        val recommendationAdapter = BarterAdapter(ArrayList()) { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
            findNavController().navigate(action)
        }
        binding.rvRecommendations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendations.adapter = recommendationAdapter

        // Adapter untuk Semua Barang (Grid)
        val allItemsAdapter = BarterAdapter(ArrayList()) { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
            findNavController().navigate(action)
        }
        binding.rvAllItems.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
        binding.rvAllItems.adapter = allItemsAdapter

        viewModel.barterItems.observe(viewLifecycleOwner) { items ->
            // Gunakan 3 barang pertama untuk rekomendasi sebagai dummy
            recommendationAdapter.updateData(items.take(3))
            allItemsAdapter.updateData(items)
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addItemFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
