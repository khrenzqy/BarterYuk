package com.example.barteryuk.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarterItem(
    val name: String,
    val description: String,
    val estimatedValue: String,
    val category: String,
    val condition: String = "Baru",
    var status: String = "Tersedia",
    val ownerEmail: String? = null
) : Parcelable
