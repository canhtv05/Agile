package com.group6.agile.dto.response;

public class PaymentResponse {
    private String status; // SUCCESS, FAILED
    private String orderId;
    private String transactionId;
    private String message;

    // Constructor
    public PaymentResponse(String status, String orderId, String transactionId, String message) {
        this.status = status;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.message = message;
    }

    // Getters
    public String getStatus() { return status; }
    public String getOrderId() { return orderId; }
    public String getTransactionId() { return transactionId; }
    public String getMessage() { return message; }
}