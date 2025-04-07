<template>
    <div class="container mx-auto p-4">
        <!-- Header -->
        <h1 class="text-2xl font-bold text-center mb-6">Quản Lý Giá & Khuyến Mãi</h1>

        <div class="flex flex-col md:flex-row gap-6">
            <!-- Sidebar: Bộ lọc -->
            <div class="md:w-1/4 p-4 rounded-lg shadow">
                <h2 class="text-lg font-semibold mb-4">Bộ Lọc</h2>
                <select v-model="filter" class="w-full p-2 border rounded">
                    <option value="all">Tất cả</option>
                    <option value="promo">Có khuyến mãi</option>
                    <option value="no-promo">Không khuyến mãi</option>
                </select>
                <input
                    v-model="search"
                    placeholder="Tìm kiếm sản phẩm..."
                    class="w-full p-2 border rounded mt-4"
                />
            </div>

            <!-- Nội dung chính -->
            <div class="md:w-3/4">
                <!-- Danh sách sản phẩm -->
                <div class="overflow-x-auto">
                    <table class="w-full rounded-lg shadow">
                        <thead class="">
                            <tr>
                                <th class="p-3 text-left">Tên</th>
                                <th class="p-3 text-left">Giá gốc</th>
                                <th class="p-3 text-left">Giá hiện tại</th>
                                <th class="p-3 text-left">Khuyến mãi (%)</th>
                                <th class="p-3 text-left">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="product in filteredProducts"
                                :key="product.id"
                                class="border-b"
                            >
                                <td class="p-3">{{ product.name }}</td>
                                <td class="p-3">
                                    {{ product.originalPrice.toLocaleString() }} VNĐ
                                </td>
                                <td class="p-3">{{ product.currentPrice.toLocaleString() }} VNĐ</td>
                                <td class="p-3">{{ product.discountPercent || 0 }}%</td>
                                <td class="p-3">
                                    <button
                                        @click="selectProduct(product)"
                                        class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600"
                                    >
                                        Cập nhật
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- Form cập nhật -->
                <div v-if="selectedProduct" class="mt-6 bg-white p-4 rounded-lg shadow">
                    <h2 class="text-lg font-semibold mb-4">Cập nhật: {{ selectedProduct.name }}</h2>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <input
                            v-model="form.price"
                            type="number"
                            placeholder="Giá mới (VNĐ)"
                            class="p-2 border rounded"
                        />
                        <input
                            v-model="form.discountCode"
                            placeholder="Mã giảm giá"
                            class="p-2 border rounded"
                        />
                        <input
                            v-model="form.discountPercent"
                            type="number"
                            placeholder="% khuyến mãi"
                            class="p-2 border rounded"
                        />
                        <input v-model="form.startDate" type="date" class="p-2 border rounded" />
                        <input v-model="form.endDate" type="date" class="p-2 border rounded" />
                    </div>
                    <div class="mt-4 flex gap-4">
                        <button
                            @click="updateProduct"
                            class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                        >
                            Lưu
                        </button>
                        <button
                            @click="selectedProduct = null"
                            class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
                        >
                            Hủy
                        </button>
                    </div>
                </div>

                <!-- Lịch sử giá & khuyến mãi -->
                <div v-if="selectedProduct" class="mt-6">
                    <h2 class="text-lg font-semibold mb-4">Lịch sử giá & khuyến mãi</h2>
                    <table class="w-full bg-white rounded-lg shadow">
                        <thead class="bg-gray-200">
                            <tr>
                                <th class="p-3 text-left">Giá cũ</th>
                                <th class="p-3 text-left">Giá mới</th>
                                <th class="p-3 text-left">Khuyến mãi</th>
                                <th class="p-3 text-left">Ngày thay đổi</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="history in selectedProduct.history"
                                :key="history.date"
                                class="border-b"
                            >
                                <td class="p-3">{{ history.oldPrice.toLocaleString() }} VNĐ</td>
                                <td class="p-3">{{ history.newPrice.toLocaleString() }} VNĐ</td>
                                <td class="p-3">{{ history.discount || 'Không' }}</td>
                                <td class="p-3">{{ history.date }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// Mock data
const products = ref([
    {
        id: 1,
        name: 'Gói Premium',
        originalPrice: 150000,
        currentPrice: 120000,
        discountPercent: 20,
        history: [{ oldPrice: 150000, newPrice: 120000, discount: '20%', date: '2025-04-01' }],
    },
    {
        id: 2,
        name: 'Sticker Pack',
        originalPrice: 50000,
        currentPrice: 50000,
        discountPercent: 0,
        history: [],
    },
    {
        id: 3,
        name: 'Gói VIP',
        originalPrice: 300000,
        currentPrice: 240000,
        discountPercent: 20,
        history: [{ oldPrice: 300000, newPrice: 240000, discount: '20%', date: '2025-03-15' }],
    },
])

// State
const filter = ref('all')
const search = ref('')
const selectedProduct = ref(null)
const form = ref({
    price: '',
    discountCode: '',
    discountPercent: '',
    startDate: '',
    endDate: '',
})

// Computed: Lọc sản phẩm
const filteredProducts = computed(() => {
    return products.value.filter((p) => {
        const matchFilter =
            filter.value === 'all' ||
            (filter.value === 'promo' && p.discountPercent > 0) ||
            (filter.value === 'no-promo' && !p.discountPercent)
        const matchSearch = p.name.toLowerCase().includes(search.value.toLowerCase())
        return matchFilter && matchSearch
    })
})

// Methods
const selectProduct = (product) => {
    selectedProduct.value = product
    form.value = {
        price: product.currentPrice,
        discountCode: '',
        discountPercent: product.discountPercent || '',
        startDate: '',
        endDate: '',
    }
}

const updateProduct = () => {
    if (selectedProduct.value) {
        const updatedProduct = { ...selectedProduct.value }
        const oldPrice = updatedProduct.currentPrice
        updatedProduct.currentPrice = form.value.price || oldPrice
        updatedProduct.discountPercent = form.value.discountPercent || 0

        // Cập nhật lịch sử
        updatedProduct.history.push({
            oldPrice,
            newPrice: updatedProduct.currentPrice,
            discount: form.value.discountPercent ? `${form.value.discountPercent}%` : 'Không',
            date: new Date().toISOString().split('T')[0],
        })

        // Cập nhật danh sách
        products.value = products.value.map((p) =>
            p.id === updatedProduct.id ? updatedProduct : p,
        )
        selectedProduct.value = null
    }
}
</script>

<style scoped>
/* Responsive adjustments */
@media (max-width: 768px) {
    table {
        font-size: 14px;
    }
    button {
        padding: 0.5rem 1rem;
    }
}
</style>
