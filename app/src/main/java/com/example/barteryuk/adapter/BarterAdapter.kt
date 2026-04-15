package com.example.barteryuk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barteryuk.databinding.ItemBarterBinding
import com.example.barteryuk.model.BarterItem

class BarterAdapter(
    private val listBarter: ArrayList<BarterItem>,
    private val showOwner: Boolean = true,
    private val onItemClick: (BarterItem) -> Unit
) : RecyclerView.Adapter<BarterAdapter.BarterViewHolder>() {

    inner class BarterViewHolder(private val binding: ItemBarterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(barterItem: BarterItem) {
            with(binding) {
                tvItemName.text = barterItem.name
                tvItemCategory.text = barterItem.category
                tvItemValue.text = "Est. Nilai: ${barterItem.estimatedValue}"
                tvItemCondition.text = barterItem.condition
                
                if (showOwner) {
                    tvItemOwner.visibility = android.view.View.VISIBLE
                    tvItemOwner.text = "Oleh: ${barterItem.ownerEmail?.split("@")?.get(0) ?: "Anonim"}"
                } else {
                    tvItemOwner.visibility = android.view.View.GONE
                }
                
                root.setOnClickListener {
                    onItemClick(barterItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarterViewHolder {
        val binding = ItemBarterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BarterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BarterViewHolder, position: Int) {
        holder.bind(listBarter[position])
    }

    override fun getItemCount(): Int = listBarter.size

    fun updateData(newList: List<BarterItem>) {
        listBarter.clear()
        listBarter.addAll(newList)
        notifyDataSetChanged()
    }
}
