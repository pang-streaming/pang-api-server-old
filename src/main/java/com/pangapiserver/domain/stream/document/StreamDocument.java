package com.pangapiserver.domain.stream.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Getter
@Document(indexName = "streams")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String username;

    private String profileImage;

    private UUID streamId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String chip;

    @Builder
    public StreamDocument(String username, String profileImage, UUID streamId, String title, String chip) {
        this.username = username;
        this.profileImage = profileImage;
        this.streamId = streamId;
        this.title = title;
        this.chip = chip;
    }
}
