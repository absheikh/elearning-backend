package com.example.rest_api_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String file_url;
    @Column(nullable = false)
    private String lesson_id;
    @Column(nullable = false)
    private String uuid;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

}
