package com.fsse2305.lab_b02_retry1.course.data.domain_object;

import com.fsse2305.lab_b02_retry1.course.data.entity.CourseEntity;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;
import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailData {
    private String courseId;
    private String name;
    private Double price;
    private PersonDetailData teacher;
    private List<PersonDetailData> students = new ArrayList<>();

    public CourseDetailData(CourseEntity courseEntity) {
        this.courseId = courseEntity.getCourseId();
        this.name = courseEntity.getName();
        this.price = courseEntity.getPrice();
        this.teacher = new PersonDetailData(courseEntity.getTeacher());

        for(PersonEntity personEntity : courseEntity.getStudents()) {
            students.add(new PersonDetailData(personEntity));
        }
    }

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

    public PersonDetailData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonDetailData teacher) {
        this.teacher = teacher;
    }

    public List<PersonDetailData> getStudents() {
        return students;
    }

    public void setStudents(List<PersonDetailData> students) {
        this.students = students;
    }
}
