package com.example.rest_api_jwt.entities;

import com.example.rest_api_jwt.repositories.CourseRepository;
import com.example.rest_api_jwt.repositories.LessonRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
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
    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "uuid")
    private List<Lesson> lessons;


    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

    public boolean isAuthor(String author){
        if(author==this.author)
            return true;
        return false;
    }

}
