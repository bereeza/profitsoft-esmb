package com.profitsoft.servicemailsender.service;

import com.profitsoft.servicemailsender.dto.MailSaveDto;
import com.profitsoft.servicemailsender.entity.Mail;
import com.profitsoft.servicemailsender.entity.Status;
import com.profitsoft.servicemailsender.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * MailService used to save the message & send the mail to admin
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final MailRepository mailRepository;
    private final JavaMailSender mailSender;

    public String saveMail(MailSaveDto entity) {
        Mail mail = convertToMailDto(entity);
        try {
            sendEmail(mail);
            mail.setStatus(Status.OK);
            log.info("Mail saved successfully");
        } catch (Exception e) {
            // if we catch an error - we add the FAILED status
            mail.setStatus(Status.FAILED);
            mail.setAttempt(mail.getAttempt() + 1);
            log.error("Failed to send email: {}", e.getMessage(), e);
        }
        mailRepository.save(mail);
        return mail.getId();
    }

    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setText(mail.getContent());
        message.setSubject(mail.getSubject());
        mailSender.send(message);
    }

    private Mail convertToMailDto(MailSaveDto mail) {
        return Mail.builder()
                .subject(mail.getSubject())
                .content(mail.getContent())
                .to(mail.getTo())
                .from(mail.getFrom())
                .status(mail.getStatus())
                .attempt(mail.getAttempt())
                .build();
    }
}
