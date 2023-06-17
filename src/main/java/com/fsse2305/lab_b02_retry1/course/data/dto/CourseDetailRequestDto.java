package com.fsse2305.lab_b02_retry1.course.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDetailRequestDto {
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("course_name")
    private String name;
    private Double price;
    @JsonProperty("teacher_id")
    private String teacherId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
