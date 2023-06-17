package com.fsse2305.lab_b02_retry1.course.data.entity;

import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDomainObject;
import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonEntity teacher;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "student_id", nullable = false)
    )
    private List<PersonEntity> students;

    public CourseEntity() {

    }

    public CourseEntity(CourseDomainObject courseDomainObject, PersonEntity teacher) {
        this.courseId = courseDomainObject.getCouseId();
        this.name = courseDomainObject.getName();
        this.price = courseDomainObject.getPrice();
        this.teacher = teacher;
        this.students = new ArrayList<>();
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

    public PersonEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonEntity teacher) {
        this.teacher = teacher;
    }

    public List<PersonEntity> getStudents() {
        return students;
    }

    public void setStudents(List<PersonEntity> students) {
        this.students = students;
    }
}
