package com.pangapiserver.domain.market.document;

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
@Document(indexName = "products")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDocument {
    @Id
    private UUID id;

    private String image;

    @Field(type = FieldType.Text)
    private String name;

    private int price;

    @Builder
    public ProductDocument(String image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
