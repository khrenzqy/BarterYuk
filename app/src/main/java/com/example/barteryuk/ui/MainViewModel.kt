package com.example.barteryuk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barteryuk.model.BarterItem

class MainViewModel : ViewModel() {
    private val _barterItems = MutableLiveData<ArrayList<BarterItem>>()
    val barterItems: LiveData<ArrayList<BarterItem>> = _barterItems

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    val listBarter = ArrayList<BarterItem>()

    init {
        setupDummyData()
        _barterItems.value = listBarter
    }

    private fun setupDummyData() {
        listBarter.add(BarterItem("Laptop ASUS", "RAM 8GB, SSD 256GB", "5.000.000", "Elektronik", "Bekas"))
        listBarter.add(BarterItem("Sepeda Gunung", "Masih mulus, ban baru", "2.500.000", "Hobi", "Bekas"))
        listBarter.add(BarterItem("Kamera Canon", "DSLR 1200D + Lensa Kit", "3.000.000", "Elektronik", "Baru"))
        listBarter.add(BarterItem("Meja Kayu", "Meja jati solid", "1.200.000", "Furniture", "Baru"))
        listBarter.add(BarterItem("Smartphone", "Layar pecah sedikit", "1.000.000", "Elektronik", "Bekas"))
    }

    fun addItem(item: BarterItem) {
        listBarter.add(item)
        _barterItems.value = listBarter
    }

    fun login(email: String, name: String) {
        _currentUser.value = User(name, email)
    }

    fun register(name: String, email: String) {
        _currentUser.value = User(name, email)
    }

    fun logout() {
        _currentUser.value = null
    }
}

data class User(
    val name: String,
    val email: String
)
