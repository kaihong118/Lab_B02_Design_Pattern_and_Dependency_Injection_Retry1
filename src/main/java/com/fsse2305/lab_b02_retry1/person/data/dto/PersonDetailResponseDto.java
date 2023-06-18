package com.fsse2305.lab_b02_retry1.person.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDetailData;
import com.fsse2305.lab_b02_retry1.course.data.dto.CourseDetailResponseDto;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;

import java.util.ArrayList;
import java.util.List;

public class PersonDetailResponseDto {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("hkid_number")
    private String hkid;
    @JsonProperty("teacher_course")
    private List<CourseDetailResponseDto> teacherCourse = new ArrayList<>();

    public PersonDetailResponseDto(PersonDetailData personDetailData) {
        this.firstName = personDetailData.getFirstName();
        this.lastName = personDetailData.getLastName();
        this.hkid = personDetailData.getHkid();
    }

    public void setTeacherCourse(List<CourseDetailData> teacherCourse) {
        for(CourseDetailData courseDetailData : teacherCourse) {
            this.teacherCourse.add(new CourseDetailResponseDto(courseDetailData));
        }
    }
}
