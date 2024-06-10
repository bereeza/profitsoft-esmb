package com.profitsoft.servicemailsender;

import com.profitsoft.servicemailsender.dto.MailSaveDto;
import com.profitsoft.servicemailsender.entity.Mail;
import com.profitsoft.servicemailsender.entity.Status;
import com.profitsoft.servicemailsender.repository.MailRepository;
import com.profitsoft.servicemailsender.service.MailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.SimpleMailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MailService.class})
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @MockBean
    private MailRepository mailRepository;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    @DisplayName("Mail sent successfully")
    public void emailSentSuccessfullyTest() {
        MailSaveDto mailSaveDto = MailSaveDto.builder()
                .subject("Subject")
                .content("Content")
                .to(new String[]{"timofejberezanskij@gmail.com"})
                .from("module5mbes@gmail.com")
                .status(Status.OK)
                .attempt(0)
                .build();

        Mail mail = Mail.builder()
                .subject(mailSaveDto.getSubject())
                .content(mailSaveDto.getContent())
                .to(mailSaveDto.getTo())
                .from(mailSaveDto.getFrom())
                .status(mailSaveDto.getStatus())
                .attempt(mailSaveDto.getAttempt())
                .build();
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        mailService.saveMail(mailSaveDto);

        verify(mailSender, times(1))
                .send(any(SimpleMailMessage.class));

        verify(mailRepository, times(1))
                .save(mail);
    }

    @Test
    @DisplayName("Failed to send message")
    public void emailFailedToSendTest() {
        MailSaveDto mailSaveDto = MailSaveDto.builder()
                .subject("Subject")
                .content("Content")
                .to(new String[]{"timofejberezanskij@gmail.com"})
                .from("module5mbes@gmail.com")
                .status(Status.OK)
                .attempt(0)
                .build();

        doThrow(new MailSendException("Failed to send email"))
                .when(mailSender).send(any(SimpleMailMessage.class));

        mailService.saveMail(mailSaveDto);

        verify(mailSender, times(1))
                .send(any(SimpleMailMessage.class));

        verify(mailRepository, times(1))
                .save(argThat(savedMail -> savedMail.getAttempt() == 1 && savedMail.getStatus() == Status.FAILED));
    }

}