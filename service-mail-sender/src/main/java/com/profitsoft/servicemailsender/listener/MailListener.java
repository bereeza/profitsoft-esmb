package com.profitsoft.servicemailsender.listener;

import com.profitsoft.servicemailsender.dto.MailSaveDto;
import com.profitsoft.servicemailsender.entity.Status;
import com.profitsoft.servicemailsender.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.profitsoft.servicemailsender.config.RabbitConfig.QUEUE_MAIL;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailListener {

    private final MailService mailService;

    @RabbitListener(queues = QUEUE_MAIL)
    public void receiveMail(MailSaveDto message) {
        message.setStatus(Status.PENDING);
        message.setAttempt(0);
        mailService.saveMail(message);
    }
}

