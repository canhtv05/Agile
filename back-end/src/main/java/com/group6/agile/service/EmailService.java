package com.group6.agile.service;

import com.group6.agile.dto.request.SendEmailRequest;
import com.group6.agile.dto.response.EmailResponse;

public interface EmailService {

    EmailResponse sendEmail(SendEmailRequest request);
}
