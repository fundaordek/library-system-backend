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
