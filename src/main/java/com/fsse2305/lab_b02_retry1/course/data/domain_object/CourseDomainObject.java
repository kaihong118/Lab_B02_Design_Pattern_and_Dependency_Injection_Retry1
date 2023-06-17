package com.fsse2305.lab_b02_retry1.course.data.domain_object;

import com.fsse2305.lab_b02_retry1.course.data.dto.CourseDetailRequestDto;

public class CourseDomainObject {
    private String courseId;
    private String name;
    private Double price;
    private String teacherId;

    public CourseDomainObject(CourseDetailRequestDto courseDetailRequestDto) {
        this.courseId = courseDetailRequestDto.getCourseId();
        this.name = courseDetailRequestDto.getName();
        this.price = courseDetailRequestDto.getPrice();
        this.teacherId = courseDetailRequestDto.getTeacherId();
    }

    public String getCouseId() {
        return courseId;
    }

    public void setCouseId(String couseId) {
        this.courseId = couseId;
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
