package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.EmailAndCode;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmailCert(String email);
    boolean checkEmailCert(EmailAndCode emailAndCode);
}
