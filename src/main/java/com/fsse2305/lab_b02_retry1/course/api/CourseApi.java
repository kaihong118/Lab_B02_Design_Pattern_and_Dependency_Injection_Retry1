package com.fsse2305.lab_b02_retry1.course.api;

import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDetailData;
import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDomainObject;
import com.fsse2305.lab_b02_retry1.course.data.dto.CourseDetailRequestDto;
import com.fsse2305.lab_b02_retry1.course.data.dto.CourseDetailResponseDto;
import com.fsse2305.lab_b02_retry1.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseApi {
    private CourseService courseService;

    @Autowired
    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public CourseDetailResponseDto createCourse(@RequestBody CourseDetailRequestDto courseDetailRequestDto) {
        CourseDomainObject courseDomainObject = new CourseDomainObject(courseDetailRequestDto);
        return new CourseDetailResponseDto(courseService.createCourse(courseDomainObject));
    }

    @GetMapping()
    public List<CourseDetailResponseDto> getAllCourse () {
        List<CourseDetailResponseDto> courseDetailResponseDtoList = new ArrayList<>();
        for(CourseDetailData courseDetailData : courseService.getAllCourse()) {
            courseDetailResponseDtoList.add(new CourseDetailResponseDto(courseDetailData));
        }
        return courseDetailResponseDtoList;
    }

    @PutMapping()
    public CourseDetailResponseDto updateCourse(@RequestBody CourseDetailRequestDto courseDetailRequestDto) {
        CourseDetailData courseDetailData = courseService.updateCourse(new CourseDomainObject(courseDetailRequestDto));
        return new CourseDetailResponseDto(courseDetailData);
    }

    @DeleteMapping("/{course_id}")
    public CourseDetailResponseDto deleteCourse(@PathVariable(value = "course_id") String courseId) {
        return new CourseDetailResponseDto(courseService.deleteCourse(courseId));
    }

    @PatchMapping("/{course_id}/add-student/{hkid_number}")
    public CourseDetailResponseDto addStudent(@PathVariable(value = "course_id") String courseId,
                                              @PathVariable(value = "hkid_number") String hkid) {
        return new CourseDetailResponseDto(courseService.addStudent(courseId, hkid));
    }

    @PatchMapping("/{course_id}/remove-student/{hkid_number}")
    public CourseDetailResponseDto deleteStudent(@PathVariable(value = "course_id") String courseId,
                                                 @PathVariable(value = "hkid_number") String hkid) {
        return new CourseDetailResponseDto(courseService.deleteStudent(courseId, hkid));
    }
}
