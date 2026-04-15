package com.example.barteryuk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        val isMyItem = args.isMyItem

        binding.tvDetailName.text = item.name
        binding.tvDetailCategory.text = "Kategori: ${item.category}"
        binding.tvDetailValue.text = "Rp ${item.estimatedValue}"
        binding.tvDetailCondition.text = "Kondisi: ${item.condition}"
        binding.tvDetailDescription.text = item.description

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        if (isMyItem) {
            binding.btnBarter.text = "Edit Barang"
            binding.btnBarter.setOnClickListener {
                Toast.makeText(requireContext(), "Fitur Edit (Dummy) untuk ${item.name}", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.btnBarter.text = "Ajukan Barter"
            binding.btnBarter.setOnClickListener {
                Toast.makeText(requireContext(), "Permintaan barter untuk ${item.name} telah diajukan!", Toast.LENGTH_LONG).show()
                binding.btnBarter.isEnabled = false
                binding.btnBarter.text = "Permintaan Terkirim"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
