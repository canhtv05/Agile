package com.group6.agile.service;

import com.group6.agile.dto.request.PaymentRequest;
import com.group6.agile.dto.response.PaymentResponse;
import com.group6.agile.service.impl.MomoGateway;
import com.group6.agile.service.impl.VisaGateway;
import com.group6.agile.service.impl.ZaloPayGateway;
import com.group6.agile.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            // Kiểm tra đầu vào
            if (request.getOrderId() == null || request.getOrderId().isEmpty()) {
                return new PaymentResponse("FAILED", request.getOrderId(), null, "Order ID không hợp lệ");
            }
            if (request.getAmount() <= 0) {
                return new PaymentResponse("FAILED", request.getOrderId(), null, "Số tiền không hợp lệ");
            }
            if (request.getPaymentMethod() == null || request.getPaymentMethod().isEmpty()) {
                return new PaymentResponse("FAILED", request.getOrderId(), null, "Phương thức thanh toán không hợp lệ");
            }

            // Mã hóa thông tin giao dịch
            String signature = SecurityUtil.generateSignature(request.getTransactionInfo());

            // Chọn cổng thanh toán
            PaymentGateway gateway;
            switch (request.getPaymentMethod().toUpperCase()) {
                case "MOMO":
                    gateway = new MomoGateway();
                    break;
                case "ZALOPAY":
                    gateway = new ZaloPayGateway();
                    break;
                case "VISA":
                    gateway = new VisaGateway();
                    break;
                default:
                    return new PaymentResponse("FAILED", request.getOrderId(), null, "Phương thức thanh toán không hỗ trợ");
            }

            // Gọi cổng thanh toán
            String transactionId = gateway.processPayment(
                request.getOrderId(),
                request.getAmount(),
                request.getTransactionInfo(),
                signature
            );

            // Xác nhận đơn hàng (giả lập)
            confirmOrder(request.getOrderId());

            return new PaymentResponse("SUCCESS", request.getOrderId(), transactionId, "Thanh toán thành công");

        } catch (Exception e) {
            return new PaymentResponse("FAILED", request.getOrderId(), null, "Thanh toán thất bại: " + e.getMessage());
        }
    }

    private void confirmOrder(String orderId) {
        // Giả lập xác nhận đơn hàng
        System.out.println("Đơn hàng " + orderId + " đã được xác nhận.");
    }
}