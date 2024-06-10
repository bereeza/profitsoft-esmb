package com.profitsoft.servicemailsender.conrtoller;

import com.profitsoft.servicemailsender.dto.MailSaveDto;
import com.profitsoft.servicemailsender.dto.Response;
import com.profitsoft.servicemailsender.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * MailController used to save mail in elastic.
 */
@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping
    public Response sendMail(@Valid @RequestBody MailSaveDto mail) {
        String id = mailService.saveMail(mail);
        return new Response(id);
    }
}
