package com.example.rest_api_jwt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String uuid;
    @Column(nullable = false)
    private String course_id;
    @Column(nullable = false)
    private String content_url;
    @Column(nullable = false)
    private String content_type;
    @Column(nullable = false)
    private Integer lesson_order;



    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }



}
