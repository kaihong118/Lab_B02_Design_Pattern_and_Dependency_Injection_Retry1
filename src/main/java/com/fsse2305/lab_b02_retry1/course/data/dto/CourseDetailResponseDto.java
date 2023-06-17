package com.fsse2305.lab_b02_retry1.course.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDetailData;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;
import com.fsse2305.lab_b02_retry1.person.data.dto.PersonDetailResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailResponseDto {
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("course_name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("teacher")
    private PersonDetailResponseDto teacher;
    @JsonProperty("students")
    private List<PersonDetailResponseDto> students = new ArrayList<>();

    public CourseDetailResponseDto(CourseDetailData courseDetailData) {
        this.courseId = courseDetailData.getCourseId();
        this.name = courseDetailData.getName();
        this.price = courseDetailData.getPrice();
        this.teacher = new PersonDetailResponseDto(courseDetailData.getTeacher());

        for(PersonDetailData personDetailData : courseDetailData.getStudents()) {
            students.add(new PersonDetailResponseDto(personDetailData));
        }
    }
}
