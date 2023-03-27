package com.example.rest_api_jwt.services;

import com.example.rest_api_jwt.entities.Course;
import com.example.rest_api_jwt.entities.User;
import com.example.rest_api_jwt.pojos.CourseRequest;
import com.example.rest_api_jwt.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    public ResponseEntity<?> addCourse(User currentUser, CourseRequest request){
        if(request.getTitle()== null || request.getTitle().isEmpty() || request.getDescription()== null || request.getDescription().isEmpty())
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid request", 400, null),
                    HttpStatus.BAD_REQUEST);



        var course = Course.builder()
                .author(currentUser.getUuid())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

                 try{
                        courseRepository.save(course);
                        logger.info("Course Added");
                        return new ResponseEntity<>(new ApiResponse<>(true, "Course Added", 200,  course),
                    HttpStatus.OK);


                } catch (Exception e){
                    logger.error(e.getMessage());
                     return new ResponseEntity<>(new ApiResponse<>(false, "Something went wrong", 500,  null),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }

    }

    public ResponseEntity<?> getMyCourses(User currentUser) {
        try{
            List courses = courseRepository.findAllByAuthor(currentUser.getUuid());
            logger.info("Courses fetched");
            return new ResponseEntity<>(new ApiResponse<>(true, "Courses fetched", 200,  courses),
                    HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(false, "Something went wrong", 500,  null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
