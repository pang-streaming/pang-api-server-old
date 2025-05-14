package com.pangapiserver.domain.stream.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "streams_tags")
public class StreamTagEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
