package com.profitsoft.songservice.service;

import com.profitsoft.songservice.dto.mail.Mail;
import com.profitsoft.songservice.dto.mail.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for manipulating mail entity.
 * In this example, I decided to go with just my mail and added it to the list
 */
@Service
public class MailService {

    @Value("${mail.from}")
    private String FROM;
    private final String CONTENT = "Content";
    private final String SUBJECT = "Subject";

    // only my mail (admin)
    private final String[] TO = {"timofejberezanskij@gmail.com"};

    // Use this @Annotation to follow the ACID rules
    @Transactional
    public Mail sendMail() {
        return Mail.builder()
                .content(CONTENT)
                .subject(SUBJECT)
                .from(FROM)
                .to(TO)
                .status(Status.PENDING)
                .build();
    }
}
