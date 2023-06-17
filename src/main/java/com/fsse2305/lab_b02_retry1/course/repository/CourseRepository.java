package com.fsse2305.lab_b02_retry1.course.repository;

import com.fsse2305.lab_b02_retry1.course.data.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, String> {
    boolean existsByCourseId(String courseId);

    CourseEntity findByCourseId(String courseId);
}
