<template>
    <div class="category-management container mx-auto p-6 min-h-screen">
        <!-- Tiêu đề và nút Thêm danh mục -->
        <div class="flex justify-between items-center mb-6">
            <h2 class="text-3xl font-bold text-gray-800">Quản lý danh mục sản phẩm</h2>
            <button
                class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-all duration-300"
                @click="openAddModal"
            >
                Thêm danh mục
            </button>
        </div>

        <!-- Thanh tìm kiếm -->
        <div class="mb-6">
            <input
                v-model="searchQuery"
                type="text"
                placeholder="Tìm kiếm danh mục..."
                class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>

        <!-- Bảng danh sách danh mục -->
        <div class="rounded-lg shadow-lg overflow-x-auto">
            <table class="w-full text-left">
                <thead>
                    <tr class="text-white-700">
                        <th class="p-4">STT</th>
                        <th class="p-4">Tên danh mục</th>
                        <th class="p-4">Mô tả</th>
                        <th class="p-4">Hình ảnh</th>
                        <th class="p-4">Trạng thái</th>
                        <th class="p-4">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="(category, index) in filteredCategories"
                        :key="category.id"
                        class="border-b hover:bg-black transition-all duration-200"
                    >
                        <td class="p-4">{{ index + 1 }}</td>
                        <td class="p-4">{{ category.name }}</td>
                        <td class="p-4">{{ category.description || 'Không có mô tả' }}</td>
                        <td class="p-4">
                            <img
                                :src="category.image"
                                alt="Hình ảnh danh mục"
                                class="w-12 h-12 object-cover rounded-lg"
                            />
                        </td>
                        <td class="p-4">
                            <span
                                :class="{
                                    'text-green-500': category.status === 'Hoạt động',
                                    'text-red-500': category.status === 'Không hoạt động',
                                }"
                            >
                                {{ category.status }}
                            </span>
                        </td>
                        <td class="p-4 flex gap-2">
                            <button
                                class="bg-yellow-500 text-white px-3 py-1 rounded-lg hover:bg-yellow-600 transition-all duration-300"
                                @click="openEditModal(category)"
                            >
                                Sửa
                            </button>
                            <button
                                class="bg-red-500 text-white px-3 py-1 rounded-lg hover:bg-red-600 transition-all duration-300"
                                @click="confirmDelete(category.id)"
                            >
                                Xóa
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Modal thêm/sửa danh mục -->
        <div
            v-if="showModal"
            class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
        >
            <div class="bg-white rounded-lg p-6 w-full max-w-md">
                <h3 class="text-2xl font-bold text-gray-800 mb-4">
                    {{ isEditMode ? 'Sửa danh mục' : 'Thêm danh mục' }}
                </h3>
                <form @submit.prevent="saveCategory">
                    <div class="mb-4">
                        <label class="block text-gray-700 mb-2">Tên danh mục</label>
                        <input
                            v-model="formData.name"
                            type="text"
                            class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Nhập tên danh mục"
                            required
                        />
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700 mb-2">Mô tả</label>
                        <textarea
                            v-model="formData.description"
                            class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Nhập mô tả (không bắt buộc)"
                        ></textarea>
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700 mb-2">Hình ảnh</label>
                        <input
                            type="file"
                            accept="image/*"
                            @change="handleImageUpload"
                            class="w-full p-3 border rounded-lg"
                        />
                        <img
                            v-if="formData.image"
                            :src="formData.image"
                            alt="Hình ảnh danh mục"
                            class="mt-2 w-20 h-20 object-cover rounded-lg"
                        />
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700 mb-2">Trạng thái</label>
                        <select
                            v-model="formData.status"
                            class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        >
                            <option value="Hoạt động">Hoạt động</option>
                            <option value="Không hoạt động">Không hoạt động</option>
                        </select>
                    </div>
                    <div class="flex gap-3">
                        <button
                            type="submit"
                            class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-all duration-300"
                        >
                            Lưu
                        </button>
                        <button
                            type="button"
                            class="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition-all duration-300"
                            @click="closeModal"
                        >
                            Hủy
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

import blackShirt from '../../assets/images/product/img-1.jpg'
import whiteShirt from '../../assets/images/product/img-2.jpg'
import greenShirt from '../../assets/images/product/img-3.jpg'
import redShirt from '../../assets/images/product/img-4.jpg'

// Dữ liệu giả lập danh mục sản phẩm
const categories = ref([
    {
        id: 1,
        name: 'Áo thun',
        description: 'Danh mục áo thun nam và nữ',
        image: blackShirt,
        status: 'Hoạt động',
    },
    {
        id: 2,
        name: 'Quần jeans',
        description: 'Danh mục quần jeans thời trang',
        image: whiteShirt,
        status: 'Hoạt động',
    },
    {
        id: 3,
        name: 'Áo khoác',
        description: 'Danh mục áo khoác mùa đông',
        image: greenShirt,
        status: 'Không hoạt động',
    },
    {
        id: 4,
        name: 'Áo nit',
        description: 'Danh mục áo khoác mùa đông',
        image: redShirt,
        status: 'Không hoạt động',
    },
])

// State cho modal và form
const showModal = ref(false)
const isEditMode = ref(false)
const formData = ref({
    id: null,
    name: '',
    description: '',
    image: '',
    status: 'Hoạt động',
})

// State cho tìm kiếm
const searchQuery = ref('')

// Computed property để lọc danh mục theo tìm kiếm
const filteredCategories = computed(() => {
    return categories.value.filter((category) =>
        category.name.toLowerCase().includes(searchQuery.value.toLowerCase()),
    )
})

// Mở modal thêm danh mục
const openAddModal = () => {
    isEditMode.value = false
    formData.value = { id: null, name: '', description: '', image: '', status: 'Hoạt động' }
    showModal.value = true
}

// Mở modal sửa danh mục
const openEditModal = (category) => {
    isEditMode.value = true
    formData.value = { ...category }
    showModal.value = true
}

// Đóng modal
const closeModal = () => {
    showModal.value = false
}

// Xử lý upload hình ảnh
const handleImageUpload = (event) => {
    const file = event.target.files[0]
    if (file) {
        formData.value.image = URL.createObjectURL(file)
    }
}

// Lưu danh mục (Thêm hoặc Sửa)
const saveCategory = () => {
    if (isEditMode.value) {
        // Sửa danh mục
        const index = categories.value.findIndex((cat) => cat.id === formData.value.id)
        if (index !== -1) {
            categories.value[index] = { ...formData.value }
            alert('Cập nhật danh mục thành công!')
        }
    } else {
        // Thêm danh mục
        const newCategory = {
            id: categories.value.length + 1,
            ...formData.value,
        }
        categories.value.push(newCategory)
        alert('Thêm danh mục thành công!')
    }
    closeModal()
}

// Xác nhận xóa danh mục
const confirmDelete = (id) => {
    if (confirm('Bạn có chắc chắn muốn xóa danh mục này?')) {
        categories.value = categories.value.filter((cat) => cat.id !== id)
        alert('Xóa danh mục thành công!')
    }
}
</script>
