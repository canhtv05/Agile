package com.group6.agile.controller;

import com.group6.agile.dto.request.PaymentRequest;
import com.group6.agile.dto.response.PaymentResponse;
import com.group6.agile.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }
}