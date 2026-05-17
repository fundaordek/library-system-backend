# 📚 Kütüphane Otomasyon Sistemi (Library Management System)

Bu proje, modern bir kütüphanenin kitap yönetim, ödünç alma ve iade etme süreçlerini dijitalleştirmek amacıyla geliştirilmiş tam yığın (Full-Stack) bir otomasyon sistemidir. Proje, güvenli bir kimlik doğrulama mimarisi (JWT) üzerine kurulmuş olup hem güçlü bir backend hem de kullanıcı dostu, dinamik bir frontend arayüzüne sahiptir.

---

## 🚀 Öne Çıkan Özellikler
- **Koleksiyon Yönetimi:** Kitapların ISBN, yayınevi, yayın yılı ve yazar bilgileriyle listelenmesi ve aranması.
- **Dinamik Ödünç/İade Sistemi:** Kitapların anlık durum takibi (Rafta / Kullanımda) ve kullanıcı bazlı iade süreçleri.
- **Detaylı Ödünç Kartı:** Ödünç alınan kitabın kim tarafından alındığı ve son teslim tarihinin şık bir modal ile gösterilmesi.
- **Güvenli Oturum Yönetimi:** JWT (JSON Web Token) tabanlı "Güvenli Çıkış" ve oturum kontrol mekanizmaları.

---

## 🛠️ Kullanılan Teknolojiler

### Backend (Arka Plan)
- **Framework:** Java / Spring Boot
- **Güvenlik:** Spring Security & JWT (JSON Web Token)
- **Veritabanı:** PostgreSQL
- **Veri Erişimi:** Spring Data JPA (Hibernate)

### Frontend (Arayüz)
- **Kütüphane:** React.js (Hooks & Functional Components)
- **Tasarım/Stil:** Bootstrap 5 (Modern bileşenler ve responsive grid yapısı)
- **İletişim:** Axios (Asenkron API entegrasyonu)
- **Yönlendirme:** React Router DOM

---

## 🧪 Test Mimarisi (JUnit & En İyi Pratikler)
Projenin backend iş mantığı ve güvenliği, modern test yaklaşımları kullanılarak doğrulanmıştır.
- **JUnit 5 & Mockito:** Servis katmanındaki ödünç alma, iade etme ve kitap yönetim algoritmaları unit testler (birim testleri) ile kapsanmıştır.
- **Güvenlik Testleri:** JWT filtrelerinin ve Spring Security yapılandırmalarının doğru yetkilendirmeyi yapıp yapmadığı test sınıfları (`Test Classes`) ile otomatik olarak doğrulanmaktadır.
- ---
<img width="500" height="500" alt="girisyap" src="https://github.com/user-attachments/assets/5355e7a2-2091-4324-8a6f-5e5e43d92c84" />
<img width="1400" height="800" alt="arama" src="https://github.com/user-attachments/assets/35275ca1-a9d9-4d94-8ba3-3c4c8fffd6b6" />
<img width="1500" height="800" alt="iade" src="https://github.com/user-attachments/assets/e099f3e3-3257-480e-af39-e9f1cbaee15e" />
<img width="1500" height="800" alt="odunc" src="https://github.com/user-attachments/assets/3e99255d-632a-42bf-a0c2-031bafbd4d2b" />
<img width="500" height="500" alt="kayitol" src="https://github.com/user-attachments/assets/6cad2940-d466-441d-ac32-5ffc51bf564c" />
