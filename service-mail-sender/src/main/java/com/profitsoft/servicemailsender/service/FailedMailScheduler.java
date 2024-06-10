package com.profitsoft.servicemailsender.service;

import com.profitsoft.servicemailsender.entity.Mail;
import com.profitsoft.servicemailsender.entity.Status;
import com.profitsoft.servicemailsender.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Scheduler for receiving mail that has an error status and needs to be resent.
 * Use pagination to avoid receiving all messages at once
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FailedMailScheduler {
    private final MailService mailService;
    private final MailRepository mailRepository;

    private static final int PAGE_SIZE = 10;

    @Scheduled(cron = "0 */5 * * * *")
    public void scheduleFailedMail() {
        Pageable pageable = PageRequest.of(0, PAGE_SIZE);
        Page<Mail> failedMailsPage;

        do {
            failedMailsPage = mailRepository.findAllByStatus(Status.FAILED, pageable);
            List<Mail> failedMails = failedMailsPage.getContent();

            for (Mail mail : failedMails) {
                try {
                    mailService.sendEmail(mail);
                    mail.setStatus(Status.OK);
                    log.info("Message sent successfully. Attempt: {}", mail.getAttempt());
                } catch (Exception e) {
                    mail.setAttempt(mail.getAttempt() + 1);
                    log.error("Failed to resend email: {}", e.getMessage(), e);
                }
                mailRepository.save(mail);
            }

            pageable = pageable.next();
        } while (failedMailsPage.hasNext());
    }
}


