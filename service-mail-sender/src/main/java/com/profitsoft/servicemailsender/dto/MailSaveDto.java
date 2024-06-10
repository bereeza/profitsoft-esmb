package com.profitsoft.servicemailsender.dto;

import com.profitsoft.servicemailsender.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * MailSaveDto is used to save the mail (use in controller & service).
 */
@Data
@Builder
@Jacksonized
public class MailSaveDto {

    private String subject;

    private String content;

    @NotBlank(message = "Sender is required.")
    private String from;

    @NotNull
    private String[] to;

    private Status status;

    @NotNull
    private long attempt;
}
