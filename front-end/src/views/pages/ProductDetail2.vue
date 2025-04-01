<template>
    <div class="bg-gray-100 min-h-screen">
        <!-- Header -->
        <header class="bg-white shadow">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
                <h1 class="text-2xl font-bold text-gray-900">{{ product.name }}</h1>
            </div>
        </header>

        <!-- Main Content -->
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <div class="lg:grid lg:grid-cols-2 lg:gap-x-8">
                <!-- Product Images -->
                <div class="lg:max-w-lg lg:self-end">
                    <div class="rounded-lg mb-4 overflow-hidden">
                        <img
                            :src="selectedImage"
                            :alt="product.name"
                            class="object-center object-cover w-full h-auto"
                        />
                    </div>
                    <div class="grid grid-cols-4 gap-2">
                        <button
                            v-for="(image, index) in product.images"
                            :key="index"
                            :class="{
                                'border-indigo-500': selectedImage === image,
                                'border-transparent': selectedImage !== image,
                            }"
                            type="button"
                            @click="selectedImage = image"
                            class="overflow-hidden border-2 rounded-md"
                        >
                            <img
                                :src="image"
                                :alt="`${product.name} - view ${index + 1}`"
                                class="object-center object-cover w-full h-24"
                            />
                        </button>
                    </div>
                </div>

                <!-- Product Details -->
                <div class="mt-10 lg:mt-0 lg:col-start-2 lg:row-span-2 lg:self-center">
                    <div class="mb-4">
                        <h2 class="text-3xl font-extrabold tracking-tight text-gray-900">
                            {{ product.name }}
                        </h2>
                        <div class="mt-3">
                            <p class="text-3xl text-gray-900">${{ product.price }}</p>
                        </div>
                    </div>

                    <!-- Rating -->
                    <div class="items-center mb-6 flex">
                        <div class="items-center flex">
                            <div v-for="i in 5" :key="i" class="flex-shrink-0">
                                <svg
                                    :class="
                                        i <= Math.round(product.star)
                                            ? 'text-indigo-500'
                                            : 'text-gray-300'
                                    "
                                    class="h-5 w-5"
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 20 20"
                                    fill="currentColor"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                            </div>
                        </div>
                        <p class="ml-3 text-sm text-gray-700">
                            {{ product.reviews.length }} reviews
                        </p>
                    </div>

                    <!-- Description -->
                    <div class="mb-6">
                        <p class="text-lg font-medium text-gray-900">Description</p>
                        <div class="mt-2 text-gray-500 space-y-4">
                            <p>{{ product.description }}</p>
                        </div>
                    </div>

                    <!-- Add to Cart Button -->
                    <div class="mb-6">
                        <button
                            @click="addToCart"
                            class="w-full bg-indigo-600 text-white py-3 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition duration-200"
                        >
                            Add to Cart
                        </button>
                    </div>
                </div>
            </div>

            <!-- Reviews Section -->
            <div class="mx-auto mt-16 lg:max-w-none max-w-2xl">
                <p class="text-2xl font-extrabold tracking-tight text-gray-900">Customer Reviews</p>
                <div
                    class="mt-6 pb-10 space-y-10 divide-y divide-gray-200 border-t border-b border-gray-200"
                >
                    <div v-for="(review, index) in product.reviews" :key="index" class="pt-10">
                        <div class="items-center justify-between flex">
                            <p class="font-medium text-gray-900">{{ review.title }}</p>
                            <p class="text-sm text-gray-500">{{ review.date }}</p>
                        </div>
                        <div class="items-center mt-2 flex">
                            <div class="items-center flex">
                                <div v-for="i in 5" :key="i" class="flex-shrink-0">
                                    <svg
                                        :class="
                                            i <= review.rating ? 'text-indigo-500' : 'text-gray-300'
                                        "
                                        class="h-5 w-5"
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 20 20"
                                        fill="currentColor"
                                    >
                                        <path
                                            d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                        />
                                    </svg>
                                </div>
                            </div>
                            <p class="ml-2 text-sm text-gray-500">
                                {{ review.rating }} out of 5 stars
                            </p>
                        </div>
                        <div class="mt-4 text-sm text-gray-600 space-y-6">
                            <p>{{ review.content }}</p>
                        </div>
                        <div class="mt-4 items-center flex">
                            <div>
                                <p class="text-sm font-medium text-gray-900">{{ review.author }}</p>
                                <div class="mt-1 items-center flex">
                                    <svg
                                        v-if="review.verified"
                                        class="h-5 w-5 text-green-500"
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 20 20"
                                        fill="currentColor"
                                    >
                                        <path
                                            fill-rule="evenodd"
                                            d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
                                            clip-rule="evenodd"
                                        />
                                    </svg>
                                    <span
                                        :class="{ 'ml-2': review.verified }"
                                        class="text-sm text-gray-500"
                                    >
                                        {{ review.verified ? 'Verified Purchase' : 'Customer' }}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

// Dữ liệu fix cứng
const product = ref({
    id: 'prod1',
    name: 'T-Shirt',
    description: 'A comfortable cotton t-shirt, perfect for everyday wear.',
    imageUrl:
        'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    star: 4.5,
    price: 199,
    categoryId: 'cat1',
    images: [
        'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
        'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
        'https://images.unsplash.com/photo-1503602642458-232111445657?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80',
    ],
    reviews: [
        {
            title: 'Great Product!',
            rating: 5,
            content: 'I love this t-shirt, very comfortable!',
            date: '2023-10-01',
            author: 'John Doe',
            verified: true,
        },
        {
            title: 'Good but pricey',
            rating: 4,
            content: 'Nice quality but a bit expensive.',
            date: '2023-09-15',
            author: 'Jane Smith',
            verified: false,
        },
    ],
})

// State cho hình ảnh được chọn
const selectedImage = ref(product.value.imageUrl)

// Hàm thêm vào giỏ hàng (mock)
const addToCart = () => {
    alert(`${product.value.name} has been added to your cart!`)
}
</script>
