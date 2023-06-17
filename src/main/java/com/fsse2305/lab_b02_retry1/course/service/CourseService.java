package com.fsse2305.lab_b02_retry1.course.service;

import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDetailData;
import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDomainObject;
import com.fsse2305.lab_b02_retry1.course.data.entity.CourseEntity;

import java.util.List;

public interface CourseService {
    CourseDetailData createCourse(CourseDomainObject courseDomainObject);

    List<CourseDetailData> getAllCourse();

    CourseDetailData updateCourse(CourseDomainObject courseDomainObject);

    CourseDetailData deleteCourse(String courseId);

    CourseDetailData addStudent(String courseId, String hkid);

    CourseDetailData deleteStudent(String courseId, String hkid);

    CourseEntity findByCourseId(String courseId);
}
