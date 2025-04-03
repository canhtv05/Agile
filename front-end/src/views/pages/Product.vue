<template>
    <div class="bg-pink-50">
      <header class="bg-white shadow-md">
        <div class="container mx-auto flex justify-between items-center py-4 px-6">
          <div class="text-3xl font-bold">
            EVA DE EVA
          </div>
          <nav class="space-x-6">
            <a class="text-gray-700 hover:text-black" href="#">HÀNG MỚI VỀ</a>
            <a class="text-gray-700 hover:text-black" href="#">SẢN PHẨM</a>
            <a class="text-gray-700 hover:text-black" href="#">BỘ SƯU TẬP</a>
            <a class="text-gray-700 hover:text-black" href="#">CASUAL</a>
            <a class="text-gray-700 hover:text-black" href="#">LADY ME</a>
            <a class="text-gray-700 hover:text-black" href="#">SALE</a>
            <a class="text-gray-700 hover:text-black" href="#">SHOWROOM</a>
          </nav>
          <div class="flex space-x-4">
            <i class="fas fa-search text-gray-700"></i>
            <i class="fas fa-user text-gray-700"></i>
            <i class="fas fa-shopping-bag text-gray-700 relative">
              <span class="absolute top-0 right-0 bg-red-500 text-white text-xs rounded-full px-1">0</span>
            </i>
          </div>
        </div>
      </header>
  
      <main class="container mx-auto py-8 px-6">
        <div class="bg-pink-100 p-4 rounded-md">
          <h2 class="text-2xl font-bold text-gray-800 flex items-center">
            <span class="text-red-500 mr-2">•</span>
            End Of Season Sale - FW24
          </h2>
          <div class="flex space-x-2 mt-2">
            <div class="bg-red-500 text-white text-center px-2 py-1 rounded-md">
              <div class="text-lg font-bold">1</div>
              <div class="text-xs">Ngày</div>
            </div>
            <div class="bg-red-500 text-white text-center px-2 py-1 rounded-md">
              <div class="text-lg font-bold">01</div>
              <div class="text-xs">Giờ</div>
            </div>
            <div class="bg-red-500 text-white text-center px-2 py-1 rounded-md">
              <div class="text-lg font-bold">59</div>
              <div class="text-xs">Phút</div>
            </div>
            <div class="bg-red-500 text-white text-center px-2 py-1 rounded-md">
              <div class="text-lg font-bold">50</div>
              <div class="text-xs">Giây</div>
            </div>
          </div>
        </div>
  
        <!-- Các sản phẩm -->
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-4 mt-8">
          <div class="bg-white p-4 rounded-md shadow-md" v-for="(product, index) in products" :key="index">
            <div class="relative">
              <img :alt="product.name" :src="product.image" height="400" width="300"/>
              <div class="absolute top-2 left-2 bg-red-500 text-white text-xs px-2 py-1 rounded-md">
                {{ product.discount }}
              </div>
            </div>
            <div class="mt-4">
              <div class="text-red-500 text-xs font-bold">FINAL SALE</div>
              <div class="text-lg font-bold text-red-500">{{ product.discountPercentage }}%</div>
              <div class="text-xs text-gray-500">SHOP NOW</div>
            </div>
            <div class="mt-2 text-gray-700">{{ product.description }}</div>
            <div class="mt-1 text-red-500 font-bold">{{ product.price }}đ</div>
            <div class="text-gray-500 line-through">{{ product.originalPrice }}đ</div>
          </div>
        </div>
      </main>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        products: [
          {
            name: "Red dress with short sleeves",
            image: "https://storage.googleapis.com/a1aa/image/DehoV6yT9-YzdztOTedw6Ltd4tONTw6TfsjlXyfW7xY.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Đầm tay ngắn, Dáng ôm 24ADKE018D",
            price: "659,700",
            originalPrice: "2,199,000"
          },
          {
            name: "Black long dress with long sleeves",
            image: "https://storage.googleapis.com/a1aa/image/Zrm18L_adfMFWorSfikAbOWVsyd51_wzDcnrrPc6TzQ.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Đầm tay dài, 24ADKC025D",
            price: "539,700",
            originalPrice: "1,799,000"
          },
          {
            name: "Brown vest and skirt set",
            image: "https://storage.googleapis.com/a1aa/image/cpAJuuVASPiJLvda5j2adlOSRg56WWCfQeJSYXq8t5o.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Váy trong bộ Vest-Váy, 24AVJE011N",
            price: "449,700",
            originalPrice: "1,499,000"
          },
          {
            name: "Leopard print dress with belt",
            image: "https://storage.googleapis.com/a1aa/image/sRZ0uWvy9xVfv9PVFXFMfeCLGPHN6m0dJ0mjhbMYluo.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Đầm tay lỡ, Dáng thắt eo 24ADKE105F",
            price: "749,700",
            originalPrice: "2,499,000"
          },
          {
            name: "Floral print dress with belt",
            image: "https://storage.googleapis.com/a1aa/image/xuGrsb451mWUdJGP30A_txpJII_guNwiuAb7Tg3HKNk.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Đầm tay lỡ, Dáng thắt eo 24ADKE072F",
            price: "659,700",
            originalPrice: "2,199,000"
          },
          {
            name: "Brown blouse and beige baggy pants",
            image: "https://storage.googleapis.com/a1aa/image/HHhSHEeRSxhX3ZCMcilhfQtniAbisnbdIIkFvzzJGI0.jpg",
            discount: "-70%",
            discountPercentage: 70,
            description: "Quần lửng, Baggy 24AQDE026N",
            price: "359,700",
            originalPrice: "1,199,000"
          }
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  /* Your custom styles can go here */
  body {
    font-family: 'Roboto', sans-serif;
  }
  </style>
  