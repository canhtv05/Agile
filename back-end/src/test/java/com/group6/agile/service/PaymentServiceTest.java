package com.group6.agile.service;

import com.group6.agile.dto.request.PaymentRequest;
import com.group6.agile.dto.response.PaymentResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {
    private final PaymentService paymentService = new PaymentService();

    @Test
    public void testPaymentSuccess_Momo() {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId("12345");
        request.setAmount(100000);
        request.setPaymentMethod("MOMO");
        request.setTransactionInfo("Thanh toán đơn hàng #12345");

        PaymentResponse response = paymentService.processPayment(request);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("12345", response.getOrderId());
        assertTrue(response.getTransactionId().startsWith("momo_txn_"));
        assertEquals("Thanh toán thành công", response.getMessage());
    }

    @Test
    public void testPaymentFailure_InvalidAmount() {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId("12345");
        request.setAmount(0);
        request.setPaymentMethod("MOMO");
        request.setTransactionInfo("Thanh toán đơn hàng #12345");

        PaymentResponse response = paymentService.processPayment(request);

        assertEquals("FAILED", response.getStatus());
        assertEquals("12345", response.getOrderId());
        assertNull(response.getTransactionId());
        assertEquals("Thanh toán thất bại: Số tiền không hợp lệ", response.getMessage());
    }

    @Test
    public void testPaymentFailure_UnsupportedMethod() {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId("12345");
        request.setAmount(100000);
        request.setPaymentMethod("UNKNOWN");
        request.setTransactionInfo("Thanh toán đơn hàng #12345");

        PaymentResponse response = paymentService.processPayment(request);

        assertEquals("FAILED", response.getStatus());
        assertEquals("12345", response.getOrderId());
        assertNull(response.getTransactionId());
        assertEquals("Thanh toán thất bại: Phương thức thanh toán không hỗ trợ", response.getMessage());
    }
}