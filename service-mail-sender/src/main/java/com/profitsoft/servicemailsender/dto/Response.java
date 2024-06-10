package com.profitsoft.servicemailsender.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

/**
 * Response used once; in MailController for the controller response.
 */
@Getter
@Builder
@Jacksonized
@RequiredArgsConstructor
public class Response {
    private final String result;
}
