package com.group6.agile.dto.request;

public class PaymentRequest {
    private String orderId;
    private double amount;
    private String paymentMethod; // MOMO, ZALOPAY, VISA
    private String transactionInfo;

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getTransactionInfo() { return transactionInfo; }
    public void setTransactionInfo(String transactionInfo) { this.transactionInfo = transactionInfo; }
}