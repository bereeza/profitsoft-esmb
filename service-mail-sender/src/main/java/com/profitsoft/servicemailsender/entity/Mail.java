package com.profitsoft.servicemailsender.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Mail data class, used to save in elastic
 * (MailSaveDto->Mail->save to elasticsearch)
 */
@Builder
@Data
@Document(indexName = "mails")
public class Mail {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String subject;

    @Field(type = FieldType.Keyword)
    private String content;

    @Field(type = FieldType.Keyword)
    private String from;

    @Field(type = FieldType.Keyword)
    private String[] to;

    @Field(type = FieldType.Keyword)
    private Status status;

    @Field(type = FieldType.Long)
    private long attempt;
}
