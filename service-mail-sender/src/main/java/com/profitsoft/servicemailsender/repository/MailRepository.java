package com.profitsoft.servicemailsender.repository;

import com.profitsoft.servicemailsender.entity.Mail;
import com.profitsoft.servicemailsender.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MailRepository extends ElasticsearchRepository<Mail, String> {
    Page<Mail> findAllByStatus(Status status, Pageable pageable);
}

