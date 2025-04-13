<template>
  <div class="container mx-auto p-4">
    <div class="flex justify-between items-center mb-4">
      <div>
        <a class="text-blue-500" href="#">
          Giỏ hàng
        </a>
        &gt;
        <span class="text-gray-500">
          Thông tin giao hàng
        </span>
      </div>
    </div>

    <div class="flex flex-col lg:flex-row">
      <!-- Shipping Information Section -->
      <div class="w-full lg:w-2/3 bg-white p-6 rounded shadow-md">
        <h2 class="text-xl font-semibold mb-4">
          Thông tin giao hàng
        </h2>
        <p class="mb-4">
          Bạn đã có tài khoản?
          <a class="text-blue-500" href="#">
            Đăng nhập
          </a>
        </p>
        <form>
          <div class="mb-4">
            <input v-model="fullName" :class="{'border-red-500': errors.fullName}" class="w-full p-2 border border-gray-300 rounded" placeholder="Họ và tên" type="text"/>
            <p v-if="errors.fullName" class="text-red-500 text-sm mt-1">{{ errors.fullName }}</p>
          </div>
          <div class="mb-4">
            <input v-model="email" :class="{'border-red-500': errors.email}" class="w-full p-2 border border-gray-300 rounded" placeholder="Email" type="email"/>
            <p v-if="errors.email" class="text-red-500 text-sm mt-1">{{ errors.email }}</p>
          </div>
          <div class="mb-4">
            <input v-model="phone" :class="{'border-red-500': errors.phone}" class="w-full p-2 border border-gray-300 rounded" placeholder="Số điện thoại" type="text"/>
            <p v-if="errors.phone" class="text-red-500 text-sm mt-1">{{ errors.phone }}</p>
          </div>
          <div class="mb-4">
            <input v-model="address" :class="{'border-red-500': errors.address}" class="w-full p-2 border border-gray-300 rounded" placeholder="Địa chỉ" type="text"/>
            <p v-if="errors.address" class="text-red-500 text-sm mt-1">{{ errors.address }}</p>
          </div>
          <div class="flex space-x-4 mb-4">
            <select v-model="province" class="w-1/3 p-2 border border-gray-300 rounded">
              <option>Chọn tỉnh / thành</option>
            </select>
            <select v-model="district" class="w-1/3 p-2 border border-gray-300 rounded">
              <option>Chọn quận / huyện</option>
            </select>
            <select v-model="ward" class="w-1/3 p-2 border border-gray-300 rounded">
              <option>Chọn phường / xã</option>
            </select>
          </div>
          <p v-if="errors.location" class="text-red-500 text-sm mt-1">{{ errors.location }}</p>

          <h2 class="text-xl font-semibold mb-4">
            Phương thức vận chuyển
          </h2>
          <div class="flex items-center justify-center border border-gray-300 rounded p-4 mb-4">
            <img alt="Shipping method icon" class="w-16 h-16" height="100" src="https://storage.googleapis.com/a1aa/image/r6A07BcaBCCiRnpLYjVwcnSrSXr4RHJUnfokjxJ4OKs.jpg" width="100"/>
            <p class="text-gray-500 ml-4">
              Vui lòng chọn tỉnh / thành để có danh sách phương thức vận chuyển.
            </p>
          </div>

          <h2 class="text-xl font-semibold mb-4">
            Phương thức thanh toán
          </h2>
          <div class="border border-gray-300 rounded p-4 mb-4">
            <div class="flex items-center mb-2">
              <input v-model="paymentMethod" type="radio" id="cod" name="payment" value="COD" />
              <label for="cod" class="flex items-center">
                <img alt="COD icon" class="w-6 h-6 mr-2" height="24" src="https://storage.googleapis.com/a1aa/image/bLTKdLEqrODO5UhnJIZkJXDSkjHf8z9zpkPpCGHcxqA.jpg" width="24"/>
                Thanh toán khi giao hàng (COD)
              </label>
            </div>
            <div class="flex items-center">
              <input v-model="paymentMethod" type="radio" id="bank" name="payment" value="Bank Transfer" />
              <label for="bank" class="flex items-center">
                <img alt="Bank transfer icon" class="w-6 h-6 mr-2" height="24" src="https://storage.googleapis.com/a1aa/image/rdTLd3NrNnjc-mwwrH0ilxdzCMQfG0zYSwnhbQ1byRQ.jpg" width="24"/>
                Chuyển khoản qua ngân hàng
              </label>
            </div>
          </div>

          <div class="flex justify-between">
            <a class="text-blue-500" href="#">
              Giỏ hàng
            </a>
            <button @click.prevent="submitForm" class="bg-blue-500 text-white px-4 py-2 rounded" type="submit">
              Hoàn tất đơn hàng
            </button>
          </div>
        </form>
      </div>

      <!-- Order Summary Section -->
      <div class="w-full lg:w-1/3 bg-white p-6 rounded shadow-md mt-6 lg:mt-0 lg:ml-6">
        <div class="flex items-center mb-4">
          <img alt="Product image" class="w-12 h-12 rounded" height="50" src="https://storage.googleapis.com/a1aa/image/HHhSHEeRSxhX3ZCMcilhfQtniAbisnbdIIkFvzzJGI0.jpg" width="50"/>
          <div class="ml-4">
            <p class="text-gray-700">
              Đầm tay dài, 24ADKC025D
            </p>
            <p class="text-gray-500">
              Đen / L / Jersey
            </p>
          </div>
          <p class="ml-auto text-gray-700">
            539,700đ
          </p>
        </div>
        <div class="mb-4">
          <input class="w-full p-2 border border-gray-300 rounded mb-2" placeholder="Mã giảm giá" type="text"/>
          <button class="bg-gray-200 text-gray-700 px-4 py-2 rounded">
            Sử dụng
          </button>
        </div>
        <div class="mb-4">
          <p class="text-gray-700">
            Tạm tính
          </p>
          <p class="text-gray-700">
            539,700đ
          </p>
        </div>
        <div class="mb-4">
          <p class="text-red-500">
            Đừng bỏ lỡ cơ hội được miễn phí giao hàng cho hóa đơn trên 699.000đ bạn nhé!
          </p>
        </div>
        <div class="flex justify-between items-center mb-4">
          <p class="text-gray-700 font-semibold">
            Tổng cộng
          </p>
          <p class="text-gray-700 font-semibold">
            539,700đ
          </p>
        </div>
        <div class="border border-red-500 p-4 rounded">
          <p class="text-red-500 text-sm">
            "Cảm ơn quý khách đã đặt hàng. Sau khi "Đặt hàng" thành công, EVA DE EVA sẽ liên hệ xác nhận đơn hàng theo số Hotline 02836226301 / 02836226302..."
          </p>
        </div>
      </div>
    </div>

    <footer class="text-center text-gray-500 mt-6">
      <p>
        Powered by Haravan
      </p>
    </footer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fullName: "",
      email: "",
      phone: "",
      address: "",
      province: "",
      district: "",
      ward: "",
      paymentMethod: "COD",
      errors: {} // ✅ Thêm lưu lỗi
    };
  },
  methods: {
    validateForm() {
      const errors = {};

      if (!this.fullName.trim()) errors.fullName = "Vui lòng nhập họ tên";
      if (!this.email.trim()) {
        errors.email = "Vui lòng nhập email";
      } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.email)) {
        errors.email = "Email không hợp lệ";
      }

      if (!this.phone.trim()) {
        errors.phone = "Vui lòng nhập số điện thoại";
      } else if (!/^\d{10,11}$/.test(this.phone)) {
        errors.phone = "Số điện thoại không hợp lệ";
      }

      if (!this.address.trim()) errors.address = "Vui lòng nhập địa chỉ";

      if (!this.province || !this.district || !this.ward) {
        errors.location = "Vui lòng chọn đầy đủ tỉnh, quận, phường";
      }

      this.errors = errors;
      return Object.keys(errors).length === 0;
    },
    submitForm() {
      if (this.validateForm()) {
        console.log('Form submitted');
        console.log(this.$data);
        // Gửi dữ liệu tại đây nếu cần
      } else {
        console.log("Có lỗi trong form");
      }
    }
  }
};
</script>

<style scoped>
/* Có thể thêm hiệu ứng lỗi tại đây nếu cần */
</style>
