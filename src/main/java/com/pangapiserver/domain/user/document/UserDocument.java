package com.pangapiserver.domain.user.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pangapiserver.domain.user.enumeration.Role;
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
@Document(indexName = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String username;

    private String nickname;

    private String profileImage;

    private String bannerImage;

    private String description;

    private Role role;

    @Builder
    public UserDocument(String username, String nickname, String profileImage, String bannerImage, String description, Role role) {
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.bannerImage = bannerImage;
        this.description = description;
        this.role = role;
    }
}
