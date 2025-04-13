package com.group6.agile.service.impl;

import com.group6.agile.service.PaymentGateway;

public class MomoGateway implements PaymentGateway {
    @Override
    public String processPayment(String orderId, double amount, String transactionInfo, String signature) {
        // Giả lập gọi API Momo
        if (amount <= 0) {
            throw new RuntimeException("Số tiền không hợp lệ");
        }
        // Kiểm tra signature (giả lập)
        return "momo_txn_" + orderId; // Trả về transaction ID
    }
}