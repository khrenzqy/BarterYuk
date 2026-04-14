package com.example.barteryuk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.barteryuk.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = args.item
        binding.tvDetailName.text = item.name
        binding.tvDetailCategory.text = item.category
        binding.tvDetailValue.text = "Estimasi Nilai: ${item.estimatedValue}"
        binding.tvDetailCondition.text = "Kondisi: ${item.condition}"
        binding.tvDetailDescription.text = item.description

        binding.btnBarter.setOnClickListener {
            Toast.makeText(requireContext(), "Permintaan barter untuk ${item.name} telah diajukan!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
