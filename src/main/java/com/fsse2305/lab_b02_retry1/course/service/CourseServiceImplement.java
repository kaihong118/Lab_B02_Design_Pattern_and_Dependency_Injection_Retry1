package com.fsse2305.lab_b02_retry1.course.service;

import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDetailData;
import com.fsse2305.lab_b02_retry1.course.data.domain_object.CourseDomainObject;
import com.fsse2305.lab_b02_retry1.course.data.entity.CourseEntity;
import com.fsse2305.lab_b02_retry1.course.repository.CourseRepository;
import com.fsse2305.lab_b02_retry1.exception.*;
import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;
import com.fsse2305.lab_b02_retry1.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplement implements CourseService{
    Logger logger = LoggerFactory.getLogger(CourseServiceImplement.class);
    private PersonService personService;
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImplement(PersonService personService, CourseRepository courseRepository) {
        this.personService = personService;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDetailData createCourse(CourseDomainObject courseDomainObject) {
        if(courseDomainObject.getCouseId() == null
                || courseDomainObject.getName() == null
                || courseDomainObject.getPrice() == null
                || courseDomainObject.getTeacherId() == null) {
            logger.warn("Create Course API: Found Null In The Data");
            throw new CourseDetailNullException();
        }
        if(courseRepository.existsByCourseId(courseDomainObject.getCouseId())) {
            logger.warn("Create Course API: Course exists " + courseDomainObject.getCouseId());
            throw new CourseExistException();
        }
        try{
            PersonEntity teacher = personService.findByHkid(courseDomainObject.getTeacherId());
            CourseEntity courseEntity = new CourseEntity(courseDomainObject, teacher);
            return new CourseDetailData(courseRepository.save(courseEntity));
        }
        catch(PersonNotFoundException ex) {
            logger.warn("Create Course API: Teacher Not Found " + courseDomainObject.getTeacherId());
            throw ex;
        }
    }

    @Override
    public List<CourseDetailData> getAllCourse() {
        List<CourseDetailData> courseDetailDataList = new ArrayList<>();
        for(CourseEntity courseEntity : courseRepository.findAll()) {
            courseDetailDataList.add(new CourseDetailData(courseEntity));
        }
        return courseDetailDataList;
    }

    @Override
    public CourseDetailData updateCourse(CourseDomainObject courseDomainObject) {
        if(courseDomainObject.getCouseId() == null
                || courseDomainObject.getName() == null
                || courseDomainObject.getPrice() == null
                || courseDomainObject.getTeacherId() == null) {
            logger.warn("Update Course API: Found Null In The Data");
            throw new CourseDetailNullException();
        }
        try {
            CourseEntity updateCourse = findByCourseId(courseDomainObject.getCouseId());
            updateCourse.setName(courseDomainObject.getName());
            updateCourse.setPrice(courseDomainObject.getPrice());
            updateCourse.setTeacher(personService.findByHkid(courseDomainObject.getTeacherId()));
            return new CourseDetailData(courseRepository.save(updateCourse));
        }
        catch(CourseNotFoundException ex) {
            logger.warn("Update Course API: Course Not Found " + courseDomainObject.getCouseId());
            throw ex;
        }
        catch(PersonNotFoundException ex) {
            logger.warn("Update Course API: Teacher Not Found " + courseDomainObject.getTeacherId());
            throw ex;
        }
    }

    @Override
    @Transactional
    public CourseDetailData deleteCourse(String courseId) {
        try {
            CourseEntity deleteEntity = findByCourseId(courseId);
            courseRepository.delete(deleteEntity);
            return new CourseDetailData(deleteEntity);

        }
        catch(CourseNotFoundException ex) {
            logger.warn("Delete Course API: Course Not Found " + courseId);
            throw ex;
        }
    }

    @Override
    public CourseDetailData addStudent(String courseId, String hkid) {
        try{
            PersonEntity student = personService.findByHkid(hkid);
            CourseEntity targetCourse = findByCourseId(courseId);
            if(hkid.equalsIgnoreCase(targetCourse.getTeacher().getHkid())) {
                logger.warn("Add Student API: Found as teacher of course " + hkid);
                throw new StudentExistException();
            }
            for(PersonEntity personEntity : targetCourse.getStudents()) {
                if(!hkid.equalsIgnoreCase(personEntity.getHkid())) {
                    continue;
                }
                logger.warn("Add Student API: Student Exists " + hkid);
                throw new StudentExistException();
            }
            targetCourse.getStudents().add(student);
            return new CourseDetailData(courseRepository.save(targetCourse));
        }
        catch(PersonNotFoundException ex) {
            logger.warn("Add Student API: Person Not Found " + hkid);
            throw ex;
        }
        catch(CourseNotFoundException ex) {
            logger.warn("Add Student API: Course Not Found " + courseId);
            throw ex;
        }
    }

    @Override
    public CourseDetailData deleteStudent(String courseId, String hkid) {
        try {
            CourseEntity targetCourse = findByCourseId(courseId);
            for(PersonEntity personEntity : targetCourse.getStudents()) {
                if(!hkid.equalsIgnoreCase(personEntity.getHkid())) {
                    continue;
                }
                targetCourse.getStudents().remove(personEntity);
                return new CourseDetailData(courseRepository.save(targetCourse));
            }
            logger.warn("Delete Student API: Student Not Found " + hkid);
            throw new StudentNotFoundException();
        }
        catch(CourseNotFoundException ex) {
            logger.warn("Delete Student API: Course Not Found " + courseId);
            throw ex;
        }
    }

    @Override
    public CourseEntity findByCourseId(String courseId) {
        CourseEntity courseEntity = courseRepository.findByCourseId(courseId);
        if(courseEntity == null) {
            throw new CourseNotFoundException();
        }
        return courseEntity;
    }
}
