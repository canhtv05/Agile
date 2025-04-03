<template>
    <div class="product-detail container mx-auto p-6 rounded-lg shadow-lg">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <!-- Cột bên trái: Hình ảnh sản phẩm -->
            <div class="product-image">
                <img
                    :src="currentImage"
                    alt="Hình ảnh sản phẩm"
                    class="w-full h-auto rounded-lg shadow-md transition-all duration-300 transform hover:scale-105"
                />
            </div>

            <!-- Cột bên phải: Thông tin sản phẩm -->
            <div class="product-info">
                <!-- Tên sản phẩm -->
                <h2 class="text-3xl font-bold text-gray-800 mb-4">{{ product.name }}</h2>

                <!-- Giá sản phẩm -->
                <p class="text-2xl font-semibold text-red-500 mb-6">
                    {{ product.price.toLocaleString() }} VNĐ
                </p>

                <!-- Chọn màu sắc -->
                <div class="color-selection mb-6">
                    <h3 class="text-lg font-semibold text-gray-700 mb-3">Chọn màu sắc:</h3>
                    <div class="color-options flex gap-4">
                        <div
                            v-for="color in product.colors"
                            :key="color.name"
                            class="color-swatch w-10 h-10 rounded-full cursor-pointer border-2 transition-all duration-300 transform hover:scale-110"
                            :style="{ backgroundColor: color.hex }"
                            :class="{
                                'border-gray-800 shadow-lg': selectedColor === color.name,
                                'border-transparent': selectedColor !== color.name,
                            }"
                            @click="selectColor(color.name)"
                        ></div>
                    </div>
                    <p v-if="selectedColor" class="mt-2 text-sm text-gray-600">
                        Màu đã chọn:
                        <span class="font-semibold text-gray-800">{{ selectedColor }}</span>
                    </p>
                    <p v-else class="mt-2 text-sm text-red-500">Vui lòng chọn màu sắc!</p>
                </div>

                <!-- Chọn size -->
                <div class="size-selection mb-6">
                    <h3 class="text-lg font-semibold text-gray-700 mb-3">Chọn kích thước:</h3>
                    <div class="size-options flex gap-3">
                        <button
                            v-for="size in product.sizes"
                            :key="size"
                            class="size-button px-4 py-2 border rounded-lg text-sm font-medium transition-all duration-300 transform hover:scale-105"
                            :class="{
                                'bg-blue-600 text-white border-blue-600 shadow-md':
                                    selectedSize === size,
                                'bg-white text-gray-700 border-gray-300 hover:bg-gray-100':
                                    selectedSize !== size,
                            }"
                            @click="selectedSize = size"
                        >
                            {{ size }}
                        </button>
                    </div>
                    <p v-if="selectedSize" class="mt-2 text-sm text-gray-600">
                        Size đã chọn:
                        <span class="font-semibold text-gray-800">{{ selectedSize }}</span>
                    </p>
                    <p v-else class="mt-2 text-sm text-red-500">Vui lòng chọn kích thước!</p>
                </div>

                <!-- Nút thêm vào giỏ hàng -->
                <button
                    class="add-to-cart w-full py-3 rounded-lg text-white font-semibold transition-all duration-300"
                    :class="{
                        'bg-blue-600 hover:bg-blue-700': selectedColor && selectedSize,
                        'bg-gray-400 cursor-not-allowed': !selectedColor || !selectedSize,
                    }"
                    :disabled="!selectedColor || !selectedSize"
                    @click="addToCart"
                >
                    Thêm vào giỏ hàng
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// Import hình ảnh trực tiếp
import blackShirt from '../../assets/images/product/img-1.jpg'
import whiteShirt from '../../assets/images/product/img-2.jpg'
import greenShirt from '../../assets/images/product/img-3.jpg'
import redShirt from '../../assets/images/product/img-4.jpg'

// Dữ liệu sản phẩm (giả lập từ API)
const product = ref({
    name: 'Áo thun nam cao cấp',
    price: 250000,
    colors: [
        { name: 'Đen', hex: '#000000', image: blackShirt },
        { name: 'Trắng', hex: '#FFFFFF', image: whiteShirt },
        { name: 'Xanh lá', hex: '#00FF00', image: greenShirt },
        { name: 'Đỏ', hex: '#FF0000', image: redShirt },
    ],
    sizes: ['S', 'M', 'L', 'XL'],
})

// State cho màu sắc và size được chọn
const selectedColor = ref(null)
const selectedSize = ref(null)

// Computed property để lấy hình ảnh hiện tại dựa trên màu sắc được chọn
const currentImage = computed(() => {
    const selected = product.value.colors.find((color) => color.name === selectedColor.value)
    return selected ? selected.image : product.value.colors[0].image // Mặc định là hình ảnh của màu đầu tiên
})

// Hàm chọn màu sắc
const selectColor = (color) => {
    selectedColor.value = color
}

// Hàm thêm vào giỏ hàng
const addToCart = () => {
    if (!selectedColor.value || !selectedSize.value) {
        alert('Vui lòng chọn màu sắc và kích thước!')
        return
    }
    alert(
        `Đã thêm vào giỏ hàng: ${product.value.name} - Màu: ${selectedColor.value} - Size: ${selectedSize.value}`,
    )
}
</script>
