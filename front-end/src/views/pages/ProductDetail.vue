<template>
    <div class="">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <div class="lg:grid lg:grid-cols-2 lg:gap-x-8 lg:items-start">
                <!-- Image gallery -->
                <div class="flex flex-col">
                    <div class="aspect-w-4 aspect-h-3 rounded-lg overflow-hidden">
                        <img
                            src="../../assets/images/product/img-1.jpg"
                            alt="Product"
                            class="w-full h-full object-center object-cover hover:scale-105 transition-transform duration-300"
                        />
                    </div>
                    <div class="mt-4 grid grid-cols-4 gap-4">
                        <button class="aspect-w-1 aspect-h-1 rounded-md overflow-hidden">
                            <img
                                src="../../assets/images/product/img-2.jpg"
                                alt="Product thumbnail"
                                class="w-full h-full object-center object-cover hover:opacity-75"
                            />
                        </button>
                        <button class="aspect-w-1 aspect-h-1 rounded-md overflow-hidden">
                            <img
                                src="../../assets/images/product/img-3.jpg"
                                alt="Product thumbnail"
                                class="w-full h-full object-center object-cover hover:opacity-75"
                            />
                        </button>
                        <button class="aspect-w-1 aspect-h-1 rounded-md overflow-hidden">
                            <img
                                src="../../assets/images/product/img-4.jpg"
                                alt="Product thumbnail"
                                class="w-full h-full object-center object-cover hover:opacity-75"
                            />
                        </button>
                        <button class="aspect-w-1 aspect-h-1 rounded-md overflow-hidden">
                            <img
                                src="../../assets/images/product/img-5.jpg"
                                alt="Product thumbnail"
                                class="w-full h-full object-center object-cover hover:opacity-75"
                            />
                        </button>
                    </div>
                </div>

                <!-- Product info -->
                <div class="mt-10 px-4 sm:px-0 sm:mt-16 lg:mt-0">
                    <h1 class="text-3xl font-extrabold tracking-tight text-gray-900">
                        Áo Sơ Mi Nam Cao Cấp
                    </h1>
                    <div class="mt-3">
                        <h2 class="sr-only">Thông tin sản phẩm</h2>
                        <p class="text-3xl text-white-900">$299.00</p>
                        <p class="mt-1">
                            <span class="text-lg font-medium text-gray-500 line-through"
                                >$399.00</span
                            >
                            <span class="text-red-600 ml-2">-25%</span>
                        </p>
                    </div>

                    <!-- Reviews -->
                    <div class="mt-6">
                        <div class="flex items-center">
                            <div class="flex items-center">
                                <svg
                                    class="h-5 w-5 text-yellow-400"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                                <svg
                                    class="h-5 w-5 text-yellow-400"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                                <svg
                                    class="h-5 w-5 text-yellow-400"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                                <svg
                                    class="h-5 w-5 text-yellow-400"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                                <svg
                                    class="h-5 w-5 text-gray-300"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                                >
                                    <path
                                        d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                                    />
                                </svg>
                            </div>
                            <p class="ml-3 text-sm text-gray-500">4.8 (128 reviews)</p>
                        </div>
                    </div>

                    <div class="mt-6">
                        <h3 class="text-sm font-medium text-gray-900">Màu sắc</h3>
                        <div class="mt-2 flex space-x-3">
                            <button
                                class="h-8 w-8 rounded-full border-2 border-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 bg-gray-900"
                            ></button>
                            <button
                                class="h-8 w-8 rounded-full border-2 border-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 bg-blue-900"
                            ></button>
                            <button
                                class="h-8 w-8 rounded-full border-2 border-gray-300 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 bg-red-900"
                            ></button>
                        </div>
                    </div>

                    <!-- Add to cart -->
                    <div class="mt-10 flex sm:flex-col1">
                        <button
                            type="submit"
                            class="max-w-xs flex-1 bg-indigo-600 border border-transparent rounded-md py-3 px-8 flex items-center justify-center text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-50 focus:ring-indigo-500 sm:w-full"
                        >
                            Thêm giỏ hàng
                        </button>
                        <button
                            type="button"
                            class="ml-4 py-3 px-3 rounded-md flex items-center justify-center text-gray-400 hover:bg-gray-100 hover:text-gray-500"
                        >
                            <svg
                                class="h-6 w-6"
                                fill="none"
                                stroke="currentColor"
                                viewBox="0 0 24 24"
                            >
                                <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                                />
                            </svg>
                        </button>
                    </div>

                    <!-- Product description -->
                    <div class="mt-10">
                        <h2 class="text-sm font-medium text-gray-900">Mô tả</h2>
                        <div class="mt-4 prose prose-sm text-gray-500">
                            <p>
                                Áo sơ mi nam cao cấp được làm từ chất liệu cotton thoáng mát, thiết
                                kế thanh lịch và dễ dàng phối hợp với nhiều phong cách.
                            </p>
                            <ul class="list-disc pl-5 mt-2">
                                <li>Chất liệu cotton thoáng mát</li>
                                <li>Kiểu dáng thanh lịch, dễ phối đồ</li>
                                <li>Đa dạng màu sắc và kích cỡ</li>
                                <li>Giặt máy dễ dàng, không phai màu</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
