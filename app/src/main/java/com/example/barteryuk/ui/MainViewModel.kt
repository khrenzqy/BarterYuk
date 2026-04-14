package com.example.barteryuk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barteryuk.model.BarterItem

class MainViewModel : ViewModel() {
    private val _barterItems = MutableLiveData<ArrayList<BarterItem>>()
    val barterItems: LiveData<ArrayList<BarterItem>> = _barterItems

    val listBarter = ArrayList<BarterItem>()

    init {
        setupDummyData()
        _barterItems.value = listBarter
    }

    private fun setupDummyData() {
        listBarter.add(BarterItem("Laptop ASUS", "RAM 8GB, SSD 256GB", "5.000.000", "Elektronik", "Bekas"))
        listBarter.add(BarterItem("Sepeda Gunung", "Masih mulus, ban baru", "2.500.000", "Hobi", "Bekas"))
        listBarter.add(BarterItem("Kamera Canon", "DSLR 1200D + Lensa Kit", "3.000.000", "Elektronik", "Baru"))
    }

    fun addItem(item: BarterItem) {
        listBarter.add(item)
        _barterItems.value = listBarter
    }
}
