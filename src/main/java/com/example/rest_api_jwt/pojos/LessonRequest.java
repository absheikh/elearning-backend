package com.example.rest_api_jwt.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    private String title;
    private String description;
    private String content_type;
    private String content_url;
    private Integer lesson_order;
}
