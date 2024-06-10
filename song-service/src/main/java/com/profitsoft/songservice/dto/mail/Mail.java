package com.profitsoft.songservice.dto.mail;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Entity to send to the message broker
 */
@Builder
@Data
@Jacksonized
public class Mail {
    private String subject;

    private String content;

    private String from;

    private String[] to;

    private Status status;
}
