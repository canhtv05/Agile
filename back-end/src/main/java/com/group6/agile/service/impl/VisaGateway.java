package com.group6.agile.service.impl;

import com.group6.agile.service.PaymentGateway;

public class VisaGateway implements PaymentGateway {
    @Override
    public String processPayment(String orderId, double amount, String transactionInfo, String signature) {
        // Giả lập gọi API Visa
        if (!signature.matches("[0-9a-fA-F]+")) {
            throw new RuntimeException("Chữ ký không hợp lệ");
        }
        return "visa_txn_" + orderId;
    }
}