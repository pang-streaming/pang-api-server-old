package com.pangapiserver.domain.stream.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    private String nickname;

    private String profileImage;

    private UUID streamId;

    private String streamUrl;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String chip;

    @Builder
    public StreamDocument(String username, String nickname, String profileImage, UUID streamId, String streamUrl, String title, String chip) {
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.streamId = streamId;
        this.streamUrl = streamUrl;
        this.title = title;
        this.chip = chip;
    }
}
