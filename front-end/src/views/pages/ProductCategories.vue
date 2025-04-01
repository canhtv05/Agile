<template>
    <div class="min-h-screen">
        <!-- Header -->
        <header class="shadow">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
                <h1 class="text-2xl font-bold text-gray-900">Product Categories</h1>
            </div>
        </header>

        <!-- Main Content -->
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <div class="flex flex-col lg:flex-row gap-8">
                <!-- Sidebar: Categories -->
                <aside class="w-full lg:w-1/4">
                    <div class="bg-white rounded-lg shadow p-4">
                        <h2 class="text-lg font-semibold text-gray-900 mb-4">Categories</h2>
                        <ul class="space-y-2">
                            <li
                                v-for="category in categories"
                                :key="category.id"
                                @click="selectCategory(category.id)"
                                :class="{
                                    'bg-indigo-600 text-white': selectedCategory === category.id,
                                    'text-gray-700 hover:bg-gray-100':
                                        selectedCategory !== category.id,
                                }"
                                class="cursor-pointer px-4 py-2 rounded-md transition duration-200"
                            >
                                {{ category.name }}
                            </li>
                        </ul>
                    </div>
                </aside>

                <!-- Main Content: Products -->
                <main class="w-full lg:w-3/4">
                    <div class="bg-white rounded-lg shadow p-6">
                        <h2 class="text-lg font-semibold text-gray-900 mb-4">
                            Products in {{ getSelectedCategoryName }}
                        </h2>
                        <div
                            v-if="filteredProducts.length > 0"
                            class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6"
                        >
                            <div
                                v-for="product in filteredProducts"
                                :key="product.id"
                                class="border rounded-lg overflow-hidden shadow hover:shadow-lg transition duration-200"
                            >
                                <img
                                    :src="product.image"
                                    :alt="product.name"
                                    class="w-full h-48 object-cover"
                                />
                                <div class="p-4">
                                    <h3 class="text-md font-medium text-gray-900">
                                        {{ product.name }}
                                    </h3>
                                    <p class="text-sm text-gray-500 mt-1">
                                        {{ product.description }}
                                    </p>
                                    <p class="text-lg font-semibold text-indigo-600 mt-2">
                                        ${{ product.price.toFixed(2) }}
                                    </p>
                                    <button
                                        @click="addToCart(product)"
                                        class="mt-3 w-full bg-indigo-600 text-white py-2 rounded-md hover:bg-indigo-700 transition duration-200"
                                    >
                                        Add to Cart
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div v-else class="text-center text-gray-500 py-8">
                            No products found in this category.
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// Hardcoded data for categories
const categories = ref([
    { id: 1, name: 'Electronics' },
    { id: 2, name: 'Clothing' },
    { id: 3, name: 'Books' },
    { id: 4, name: 'Home & Garden' },
])

// Hardcoded data for products
const products = ref([
    {
        id: 1,
        categoryId: 1,
        name: 'Smartphone',
        description: 'A high-quality smartphone with advanced features.',
        price: 699.99,
        image: 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
    {
        id: 2,
        categoryId: 1,
        name: 'Laptop',
        description: 'A powerful laptop for work and gaming.',
        price: 1299.99,
        image: 'https://images.unsplash.com/photo-1496181133206-80ce9b88a0a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
    {
        id: 3,
        categoryId: 2,
        name: 'T-Shirt',
        description: 'A comfortable cotton t-shirt.',
        price: 19.99,
        image: 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
    {
        id: 4,
        categoryId: 2,
        name: 'Jeans',
        description: 'Stylish denim jeans for everyday wear.',
        price: 49.99,
        image: 'https://images.unsplash.com/photo-1541099649105-f69ad21f3246?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
    {
        id: 5,
        categoryId: 3,
        name: 'Fiction Novel',
        description: 'An exciting fiction novel to dive into.',
        price: 14.99,
        image: 'https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
    {
        id: 6,
        categoryId: 4,
        name: 'Plant Pot',
        description: 'A beautiful pot for your indoor plants.',
        price: 29.99,
        image: 'https://images.unsplash.com/photo-1598300046947-48a37f69664d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    },
])

// State for selected category
const selectedCategory = ref(categories.value[0].id) // Default to the first category

// Computed property to filter products by selected category
const filteredProducts = computed(() => {
    return products.value.filter((product) => product.categoryId === selectedCategory.value)
})

// Computed property to get the name of the selected category
const getSelectedCategoryName = computed(() => {
    const category = categories.value.find((cat) => cat.id === selectedCategory.value)
    return category ? category.name : 'Unknown Category'
})

// Function to select a category
const selectCategory = (categoryId) => {
    selectedCategory.value = categoryId
}

// Function to add a product to cart (mock)
const addToCart = (product) => {
    alert(`Added ${product.name} to cart!`)
}
</script>
