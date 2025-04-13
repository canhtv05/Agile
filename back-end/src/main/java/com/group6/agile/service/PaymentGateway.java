package com.group6.agile.service;

public interface PaymentGateway {
    String processPayment(String orderId, double amount, String transactionInfo, String signature);
}