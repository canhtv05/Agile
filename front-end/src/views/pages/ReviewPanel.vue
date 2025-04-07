<template>
    <div class="container mx-auto p-4">
        <!-- Header -->
        <h1 class="text-2xl font-bold text-center mb-6">Đánh Giá Sản Phẩm</h1>

        <!-- Danh sách đánh giá -->
        <div class="p-4 rounded-lg shadow mb-6">
            <h2 class="text-lg font-semibold mb-4">Danh sách đánh giá</h2>
            <div v-if="reviews.length" class="space-y-4">
                <div v-for="review in reviews" :key="review.id" class="border-b pb-4">
                    <div class="flex items-center gap-2">
                        <span class="font-medium">{{ review.user }}</span>
                        <div class="flex">
                            <svg
                                v-for="n in 5"
                                :key="n"
                                class="w-5 h-5"
                                :class="n <= review.rating ? 'text-yellow-400' : 'text-gray-300'"
                                fill="currentColor"
                                viewBox="0 0 20 20"
                            >
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                />
                            </svg>
                        </div>
                    </div>
                    <p class="text-gray-600 mt-1">{{ review.comment }}</p>
                    <span class="text-sm text-gray-400">{{ review.date }}</span>
                </div>
            </div>
            <p v-else class="text-gray-500">Chưa có đánh giá nào.</p>
        </div>

        <!-- Form viết đánh giá -->
        <div class="p-4 rounded-lg shadow">
            <h2 class="text-lg font-semibold mb-4">Viết đánh giá của bạn</h2>
            <div class="space-y-4">
                <!-- Số sao -->
                <div class="flex items-center gap-2">
                    <span>Đánh giá:</span>
                    <div class="flex">
                        <button
                            v-for="n in 5"
                            :key="n"
                            @click="newReview.rating = n"
                            class="focus:outline-none"
                        >
                            <svg
                                class="w-6 h-6"
                                :class="n <= newReview.rating ? 'text-yellow-400' : 'text-gray-300'"
                                fill="currentColor"
                                viewBox="0 0 20 20"
                            >
                                <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                />
                            </svg>
                        </button>
                    </div>
                </div>

                <!-- Comment -->
                <textarea
                    v-model="newReview.comment"
                    placeholder="Nhận xét về sản phẩm (ví dụ: áo đẹp, chất vải xịn...)"
                    class="w-full p-2 border rounded resize-none"
                    rows="3"
                ></textarea>

                <!-- Nút gửi -->
                <button
                    @click="submitReview"
                    :disabled="!newReview.rating || !newReview.comment"
                    class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:bg-gray-400"
                >
                    Gửi đánh giá
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// Mock data đánh giá
const reviews = ref([
    {
        id: 1,
        user: 'Ngọc Anh',
        rating: 5,
        comment: 'Áo đẹp lắm, vải mềm, mặc thoải mái!',
        date: '2025-04-10',
    },
    {
        id: 2,
        user: 'Minh Tuấn',
        rating: 4,
        comment: 'Chất lượng tốt, nhưng giao hơi chậm.',
        date: '2025-04-09',
    },
])

// Form đánh giá mới
const newReview = ref({
    rating: 0,
    comment: '',
})

// Gửi đánh giá
const submitReview = () => {
    if (newReview.value.rating && newReview.value.comment) {
        reviews.value.push({
            id: reviews.value.length + 1,
            user: 'Bạn', // Giả lập user là "Bạn"
            rating: newReview.value.rating,
            comment: newReview.value.comment,
            date: new Date().toISOString().split('T')[0],
        })
        newReview.value = { rating: 0, comment: '' } // Reset form
    }
}
</script>

<style scoped>
/* Responsive */
@media (max-width: 768px) {
    .text-2xl {
        font-size: 1.5rem;
    }
    svg {
        width: 1.25rem;
        height: 1.25rem;
    }
}
</style>
