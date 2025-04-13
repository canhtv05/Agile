package com.group6.agile.service.impl;

import com.group6.agile.service.PaymentGateway;

public class ZaloPayGateway implements PaymentGateway {
    @Override
    public String processPayment(String orderId, double amount, String transactionInfo, String signature) {
        // Giả lập gọi API ZaloPay
        if (transactionInfo == null || transactionInfo.isEmpty()) {
            throw new RuntimeException("Thông tin giao dịch rỗng");
        }
        return "zalopay_txn_" + orderId;
    }
}