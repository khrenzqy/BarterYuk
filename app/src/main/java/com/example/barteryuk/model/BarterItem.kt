package com.example.barteryuk.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class BarterItem merepresentasikan sebuah item/barang yang ditawarkan dalam aplikasi.
 * Menggunakan @Parcelize agar objek ini dapat dikirim antar Fragment melalui Navigation SafeArgs.
 */
@Parcelize
data class BarterItem(
    val name: String,           // Nama barang (misal: Laptop ASUS)
    val description: String,    // Deskripsi detail barang
    val estimatedValue: String, // Estimasi nilai barang dalam Rupiah
    val category: String,       // Kategori barang (Elektronik, Fashion, dll)
    val condition: String = "Baru", // Kondisi barang (Baru atau Bekas)
    var status: String = "Tersedia", // Status barang (Tersedia atau Sudah Dibarter)
    val ownerEmail: String? = null // Email pemilik barang untuk identifikasi kepemilikan
) : Parcelable
