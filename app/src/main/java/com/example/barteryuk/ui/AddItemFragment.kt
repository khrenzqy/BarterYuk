package com.example.barteryuk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.barteryuk.databinding.FragmentAddItemBinding
import com.example.barteryuk.model.BarterItem

class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Spinner Kategori
        val categories = arrayOf("Elektronik", "Fashion", "Hobi", "Furniture", "Lainnya")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter

        binding.btnUploadImage.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur Upload Gambar (Dummy) Berhasil", Toast.LENGTH_SHORT).show()
            binding.ivItemImage.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val description = binding.etDescription.text.toString()
            val value = binding.etValue.text.toString()
            val category = binding.spinnerCategory.selectedItem.toString()
            
            val selectedConditionId = binding.rgCondition.checkedRadioButtonId
            val condition = if (selectedConditionId != -1) {
                view.findViewById<RadioButton>(selectedConditionId).text.toString()
            } else {
                "Bekas"
            }

            if (name.isNotEmpty() && description.isNotEmpty() && value.isNotEmpty()) {
                val newItem = BarterItem(name, description, value, category, condition)
                viewModel.addItem(newItem)
                Toast.makeText(requireContext(), "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
