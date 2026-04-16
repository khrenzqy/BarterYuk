#👤 Nama 
- Kharenina Rizqy Putri Nugroho - 24082010144 -




# 📦 BarterYuk - Aplikasi Tukar Barang (Marketplace Barter)

**BarterYuk** adalah aplikasi Android berbasis Kotlin yang dirancang untuk mempermudah pengguna dalam melakukan tukar-menukar barang (barter) secara modern. Aplikasi ini menggunakan arsitektur MVVM (Model-View-ViewModel) dan komponen Jetpack terbaru untuk performa yang optimal dan kode yang terstruktur.

---

## 🚀 Fitur Utama

- **Autentikasi Pengguna**: Sistem Login dan Register (dengan data simulasi/dummy) untuk mengelola profil pengguna.
- **Dashboard Marketplace**: Tampilan barang-barang terbaru dengan sistem *Dot Indicator* yang interaktif.
- **Manajemen Barang**: Pengguna dapat menambahkan barang yang ingin ditukar melalui formulir yang mudah digunakan.
- **Detail Barang Dinamis**: Menampilkan rincian barang dan secara otomatis menyesuaikan tombol aksi (Edit vs Ajukan Barter) berdasarkan kepemilikan.
- **Profil Pengguna**: Menampilkan informasi akun dan daftar koleksi barang pribadi ("Barang Saya").
- **Navigasi Terstruktur**: Menggunakan *Jetpack Navigation Component* untuk alur aplikasi yang mulus dan aman.

---

## 🛠️ Teknologi yang Digunakan

- **Language**: Kotlin 
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: XML Layouts dengan **ViewBinding**
- **Navigation**: Jetpack Navigation Component (SafeArgs)
- **Components**: 
  - `RecyclerView` (Grid & Horizontal Layout)
  - `LiveData` & `ViewModel` (State Management)
  - `CardView` & `Material Components` (Modern UI)
  - `NestedScrollView` (Smooth Scrolling)

---

## 📁 Struktur Proyek

```text
app/src/main/java/com/example/barteryuk/
├── adapter/
│   └── BarterAdapter.kt        # Pengelola list barang di UI
├── model/
│   └── BarterItem.kt           # Data class utama untuk barang
└── ui/
    ├── MainViewModel.kt        # Pusat data dan logika bisnis
    ├── HomeFragment.kt         # Dashboard utama
    ├── ProfileFragment.kt      # Manajemen akun dan "Barang Saya"
    ├── DetailFragment.kt       # Rincian barang & aksi barter
    ├── AddItemFragment.kt      # Form tambah barang baru
    ├── LoginFragment.kt        # Halaman masuk
    ├── RegisterFragment.kt     # Halaman pendaftaran
    └── WelcomeFragment.kt      # Splash/Welcome screen
```

---

## 📖 Cara Penggunaan (Demo)

1. **Awal**: Aplikasi dimulai di halaman `Welcome`. Anda bisa memilih `Login` atau `Daftar`.
2. **Login**: Gunakan akun admin (`admin@admin.co.id` / `admin123`) atau daftar akun baru.
3. **Jelajah**: Di `Home`, Anda bisa melihat berbagai barang yang tersedia. Klik salah satu untuk melihat detail.
4. **Tambah Barang**: Pergi ke menu `Profile`, klik tombol tambah (`+`), isi formulir, dan simpan. Barang akan muncul di "Barang Saya" dan di halaman Home.
5. **Barter**: Jika Anda melihat barang orang lain di Home, Anda bisa menekan tombol "Ajukan Barter" di halaman Detail.

---

## 📝 Catatan Pengembangan

Proyek ini dibuat dengan fokus pada **dokumentasi kode yang bersih (Clean Code)**. Setiap file Kotlin telah dilengkapi dengan komentar detail (KDoc) untuk mempermudah pengembang lain memahami alur logika aplikasi.

---

*Dibuat untuk keperluan Tugas/Presentasi Pengembangan Aplikasi Mobile.*
