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

    // Simpan data user yang teregistrasi (dummy)
    private val registeredUsers = mutableMapOf<String, String>() // Email to Password
    private val userNames = mutableMapOf<String, String>() // Email to Name

    val listBarter = ArrayList<BarterItem>()

    init {
        setupDummyData()
        // Tambahkan user Admin
        register("Admin", "admin@admin.co.id", "admin123")
        _barterItems.value = listBarter
    }

    private fun setupDummyData() {
        listBarter.add(BarterItem("Laptop ASUS", "RAM 8GB, SSD 256GB", "5.000.000", "Elektronik", "Bekas", ownerEmail = "dummy@mail.com"))
        listBarter.add(BarterItem("Sepeda Gunung", "Masih mulus, ban baru", "2.500.000", "Hobi", "Bekas", ownerEmail = "dummy@mail.com"))
        listBarter.add(BarterItem("Kamera Canon", "DSLR 1200D + Lensa Kit", "3.000.000", "Elektronik", "Baru"))
        listBarter.add(BarterItem("Meja Kayu", "Meja jati solid", "1.200.000", "Furniture", "Baru"))
        listBarter.add(BarterItem("Smartphone", "Layar pecah sedikit", "1.000.000", "Elektronik", "Bekas"))
        listBarter.add(BarterItem("Gitar Akustik", "Gitar Yamaha F310 original", "1.500.000", "Musik", "Bekas"))
        listBarter.add(BarterItem("Smartwatch", "Apple Watch Series 7", "4.500.000", "Elektronik", "Baru"))
        listBarter.add(BarterItem("Rak Buku", "Bahan besi minimalis", "500.000", "Furniture", "Baru"))
        listBarter.add(BarterItem("Sepatu Lari", "Nike Air Zoom Pegasus", "1.200.000", "Olahraga", "Baru"))
        listBarter.add(BarterItem("Keyboard Mechanical", "Keychron K2 RGB", "1.300.000", "Elektronik", "Bekas"))
    }

    fun addItem(item: BarterItem) {
        listBarter.add(item)
        _barterItems.value = listBarter
    }

    fun login(email: String, password: String): Boolean {
        return if (registeredUsers[email] == password) {
            _currentUser.value = User(userNames[email] ?: "User", email)
            true
        } else {
            false
        }
    }

    fun register(name: String, email: String, password: String) {
        registeredUsers[email] = password
        userNames[email] = name
    }

    fun logout() {
        _currentUser.value = null
    }
}

data class User(
    val name: String,
    val email: String
)
