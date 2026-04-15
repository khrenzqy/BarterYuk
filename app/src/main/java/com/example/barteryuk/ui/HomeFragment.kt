package com.example.barteryuk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val recommendationAdapter = BarterAdapter(ArrayList(), showOwner = true) { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item, false)
            findNavController().navigate(action)
        }
        binding.rvRecommendations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendations.adapter = recommendationAdapter

        // Adapter untuk Semua Barang (Grid)
        val allItemsAdapter = BarterAdapter(ArrayList(), showOwner = true) { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item, false)
            findNavController().navigate(action)
        }
        binding.rvAllItems.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
        binding.rvAllItems.adapter = allItemsAdapter

        viewModel.barterItems.observe(viewLifecycleOwner) { items ->
            val recItems = items.take(3)
            recommendationAdapter.updateData(recItems)
            allItemsAdapter.updateData(items)
            setupDotIndicator(recItems.size)
        }

        binding.rvRecommendations.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstVisibleItemPosition()
                if (position != RecyclerView.NO_POSITION) {
                    updateDots(position)
                }
            }
        })
    }

    private fun setupDotIndicator(size: Int) {
        binding.dotIndicator.removeAllViews()
        if (size <= 0) return
        
        for (i in 0 until size) {
            val dot = ImageView(requireContext())
            dot.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.presence_invisible))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.dotIndicator.addView(dot, params)
        }
        updateDots(0)
    }

    private fun updateDots(position: Int) {
        val childCount = binding.dotIndicator.childCount
        for (i in 0 until childCount) {
            val imageView = binding.dotIndicator.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.presence_online))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), android.R.drawable.presence_invisible))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
