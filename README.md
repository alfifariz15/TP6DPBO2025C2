# TP6DPBO2025C2
Saya Muhammad Alfi Fariz dengan NIM 2311174 mengerjakan TP 6 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program Flappy Bird
1. Class Utama: FlappyBird
- Merupakan turunan dari JPanel dan mengimplementasikan ActionListener dan KeyListener.
- Bertanggung jawab terhadap logika permainan, input pemain, dan tampilan grafis.

2. Komponen-Komponen Utama
Komponen & Fungsi nya:
- Player = Objek burung (bird) yang dikendalikan pemain.
- Pipe = Rintangan (atas dan bawah) yang harus dihindari.
- Timer = Dua timer: gameLoop (gerak burung & pipe) dan pipesCooldown (spawn pipe).
- JLabel = Menampilkan skor di layar.
- Image = Gambar background, burung, dan pipa (diambil dari folder assets).

3. Gambar & Aset
- Semua aset gambar diambil dari folder assets dan ditampilkan dengan drawImage() pada metode paintComponent().

# Alur Program
1. Inisialisasi
- Ukuran frame diset 360 x 640.
- Gambar dimuat ke dalam variabel Image.
- Player dan ArrayList<Pipe> dibuat.
- JLabel untuk skor ditambahkan ke panel.
- Dua Timer:
pipesCooldown → Menambahkan pipa baru setiap 1500 ms.
gameLoop → Memanggil move() dan repaint() setiap 1/60 detik.

2. Pergerakan dan Logika Game (move() dan actionPerformed)
- Gravitasi diterapkan ke player.
- Posisi Y pemain diubah berdasarkan kecepatan.
- Semua pipa digeser ke kiri.
- Method ini dipanggil otomatis melalui Timer gameLoop.

3. Gambar Ulang Komponen (paintComponent)
- Gambar background, player, dan semua pipa.
- Mengecek:
Tabrakan antara player dan pipe.
Player jatuh ke bawah layar.
Player melewati pipa atas → Tambah skor.
- Jika isGameOver == true, tampilkan teks "GAME OVER" dan "Press R to Restart".

4. Input Pemain (keyPressed)
Tombol & Aksi nya:
- Spasi = Melompat (mengatur velocityY pemain menjadi negatif).
- R (jika game over) = Reset game (memanggil restartGame()).

5. Restart Game (restartGame())
- Posisi & kecepatan player di-reset ke awal.
- Pipe dihapus (clear()).
- Skor di-reset ke 0.
- Game status diubah ke tidak game over.

# Screenrecord Program
https://github.com/user-attachments/assets/f71d350f-8819-41f2-a7b3-2e9ca21e3552
