# 📚 Dokumentasi Teknis Proyek BarterYuk

Dokumen ini berisi penjelasan struktur kode, hubungan antar file, dan alur data dalam aplikasi BarterYuk untuk keperluan pengembangan dan presentasi.

---

## 1. Arsitektur Data (Model)

### **`BarterItem.kt`** (Data Class)
*   **Fungsi**: Template data utama untuk barang yang dibarter.
*   **Atribut**: Nama, Deskripsi, Nilai Estimasi, Kategori, Kondisi, Status, dan Email Pemilik.
*   **Mekanisme**: Menggunakan `@Parcelize` agar objek bisa dikirim utuh antar Fragment melalui Navigation SafeArgs.

### **`User.kt`** (Data Class)
*   **Fungsi**: Menyimpan informasi sesi pengguna (Nama & Email).
*   **Hubungan**: Digunakan untuk identifikasi pemilik barang dan sesi login.

---

## 2. Logika Bisnis & State Management

### **`MainViewModel.kt`**
*   **Fungsi**: Jantung data aplikasi (Activity-scoped).
*   **Isi**:
    *   `_barterItems`: Menyimpan list global semua barang.
    *   `_currentUser`: Menyimpan data user yang sedang aktif.
    *   Logika `login()`, `register()`, dan `addItem()`.
*   **Alur Data**: Menggunakan `LiveData`. Saat data di ViewModel diubah, Fragment yang mengamati (`observe`) akan otomatis memperbarui tampilan UI secara real-time.

---

## 3. Penghubung Antarmuka (Adapter)

### **`BarterAdapter.kt`**
*   **Fungsi**: Menghubungkan data `BarterItem` ke dalam desain kartu di `item_barter.xml`.
*   **Parameter Kunci**:
    *   `showOwner`: Boolean. Jika `true` (di Home), memunculkan nama pemilik. Jika `false` (di Profil), menyembunyikannya.
    *   `onItemClick`: Callback untuk menangani aksi klik item.

---

## 4. Alur Navigasi (Navigation Component)

### **`nav_graph.xml`**
*   **Fungsi**: Peta navigasi visual aplikasi.
*   **Passing Parameter**:
    *   `item`: Objek `BarterItem` yang dikirim ke Detail.
    *   `isMyItem`: Boolean penanda apakah barang tersebut milik user yang sedang login.

---

## 5. Penjelasan Per Fragment

### **`LoginFragment` & `RegisterFragment`**
*   **Fungsi**: Menangani autentikasi.
*   **Hubungan**: Terhubung ke `MainViewModel` untuk validasi data. Sekarang mendukung navigasi timbal balik ("Daftar dulu" / "Login aja").

### **`HomeFragment`**
*   **Fungsi**: Dashboard utama aplikasi.
*   **Fitur**: Menampilkan list rekomendasi (Horizontal) dengan **Dot Indicator** yang bergerak dinamis saat di-scroll, dan list semua barang (Grid).

### **`ProfileFragment`**
*   **Fungsi**: Halaman informasi pengguna dan "Barang Saya".
*   **Logika**: Memfilter list global barang menggunakan `ownerEmail` dari user yang aktif.
*   **Navigasi**: Pintu masuk ke `AddItemFragment` dan tombol Logout.

### **`DetailFragment`**
*   **Fungsi**: Menampilkan rincian lengkap barang.
*   **Logika Tombol**: 
    *   Jika `isMyItem == true` -> Tombol menjadi "**Edit Barang**".
    *   Jika `isMyItem == false` -> Tombol menjadi "**Ajukan Barter**".
*   **Fitur**: Tombol **Back** di pojok kiri atas untuk navigasi kembali yang mudah.

### **`AddItemFragment`**
*   **Fungsi**: Form input barang baru.
*   **Alur**: Mengambil input user -> Membuat objek `BarterItem` -> Menambahkan ke `MainViewModel` -> Kembali ke halaman sebelumnya.

---

## 6. Desain Layout (XML)

*   **`fragment_home.xml`**: Layout kompleks dengan `NestedScrollView` untuk performa scroll yang halus.
*   **`item_barter.xml`**: Desain kartu barang menggunakan `CardView` dan `ConstraintLayout`.
*   **`fragment_detail.xml`**: Tampilan detail dengan gambar besar dan informasi terstruktur.

---

*Dokumentasi ini dibuat untuk mempermudah pemahaman alur kerja aplikasi BarterYuk.*
