package com.example.barteryuk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barteryuk.model.BarterItem

/**
 * MainViewModel adalah pusat manajemen state aplikasi menggunakan pola MVVM.
 * ViewModel ini bersifat Activity-scoped sehingga datanya bisa dibagikan antar Fragment.
 */
class MainViewModel : ViewModel() {
    
    // LiveData untuk daftar barang barter secara global
    private val _barterItems = MutableLiveData<ArrayList<BarterItem>>()
    val barterItems: LiveData<ArrayList<BarterItem>> = _barterItems

    // LiveData untuk menyimpan informasi user yang sedang login
    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    // Database dummy untuk menyimpan kredensial user (Email ke Password)
    private val registeredUsers = mutableMapOf<String, String>()
    // Database dummy untuk menyimpan nama user (Email ke Nama)
    private val userNames = mutableMapOf<String, String>()

    // List internal untuk mengelola data barang
    val listBarter = ArrayList<BarterItem>()

    init {
        setupDummyData()
        // Mendaftarkan akun Admin secara otomatis saat aplikasi dimulai
        register("Admin", "admin@admin.co.id", "admin123")
        _barterItems.value = listBarter
    }

    /**
     * Mengisi listBarter dengan data dummy awal untuk keperluan demo.
     */
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

    /**
     * Menambahkan barang baru ke dalam list global.
     * @param item Objek BarterItem yang akan ditambahkan.
     */
    fun addItem(item: BarterItem) {
        listBarter.add(item)
        _barterItems.value = listBarter // Memicu pembaruan UI pada semua observer
    }

    /**
     * Memvalidasi kredensial login user.
     * @param email Email input user.
     * @param password Password input user.
     * @return Boolean true jika login berhasil.
     */
    fun login(email: String, password: String): Boolean {
        return if (registeredUsers[email] == password) {
            _currentUser.value = User(userNames[email] ?: "User", email)
            true
        } else {
            false
        }
    }

    /**
     * Mendaftarkan user baru ke dalam database dummy.
     * @param name Nama lengkap user.
     * @param email Email user.
     * @param password Password user.
     */
    fun register(name: String, email: String, password: String) {
        registeredUsers[email] = password
        userNames[email] = name
    }

    /**
     * Menghapus sesi user yang sedang login (Logout).
     */
    fun logout() {
        _currentUser.value = null
    }
}

/**
 * Data class untuk merepresentasikan informasi User.
 */
data class User(
    val name: String,
    val email: String
)
