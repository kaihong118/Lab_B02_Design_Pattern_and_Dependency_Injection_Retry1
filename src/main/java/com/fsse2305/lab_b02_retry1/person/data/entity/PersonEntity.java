package com.fsse2305.lab_b02_retry1.person.data.entity;

import com.fsse2305.lab_b02_retry1.course.data.entity.CourseEntity;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDomainObject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String hkid;

    @OneToMany(mappedBy = "teacher")
    List<CourseEntity> teacherCourse = new ArrayList<>();

    @ManyToMany(mappedBy = "students")
    List<CourseEntity> courseStudentJoined = new ArrayList<>();

    public PersonEntity() {

    }

    public PersonEntity(PersonDomainObject personDomainObject) {
        this.firstName = personDomainObject.getFirstName();
        this.lastName = personDomainObject.getLastName();
        this.hkid = personDomainObject.getHkid();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHkid() {
        return hkid;
    }

    public void setHkid(String hkid) {
        this.hkid = hkid;
    }
}
