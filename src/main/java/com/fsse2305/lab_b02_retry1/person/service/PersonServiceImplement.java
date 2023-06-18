package com.fsse2305.lab_b02_retry1.person.service;

import com.fsse2305.lab_b02_retry1.exception.PersonDetailNullException;
import com.fsse2305.lab_b02_retry1.exception.PersonExistException;
import com.fsse2305.lab_b02_retry1.exception.PersonNotFoundException;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDomainObject;
import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;
import com.fsse2305.lab_b02_retry1.person.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImplement implements PersonService{
    Logger logger = LoggerFactory.getLogger(PersonServiceImplement.class);
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImplement(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDetailData createPerson(PersonDomainObject personDomainObject) {
        if(personDomainObject.getFirstName() == null
                || personDomainObject.getLastName() == null
                || personDomainObject.getHkid() == null) {
            logger.warn("Create Person API: Found Null in the data");
            throw new PersonDetailNullException();
        }
        if(personRepository.existsByHkid(personDomainObject.getHkid())) {
            logger.warn("Create Person API: Person Found " + personDomainObject.getHkid());
            throw new PersonExistException();
        }
        PersonEntity personEntity = new PersonEntity(personDomainObject);
        personRepository.save(personEntity);
        return new PersonDetailData(personEntity);
    }

    @Override
    public List<PersonDetailData> getAllPerson() {
        List<PersonDetailData> personDetailDataList = new ArrayList<>();
        for(PersonEntity personEntity : personRepository.findAll()) {
            personDetailDataList.add(new PersonDetailData(personEntity));
        }
        return personDetailDataList;
    }

    @Override
    public PersonDetailData updatePerson(PersonDomainObject personDomainObject) {
        if(personDomainObject.getFirstName() == null
                || personDomainObject.getLastName() == null
                || personDomainObject.getHkid() == null) {
            logger.warn("Create Person API: Found Null in the data");
            throw new PersonDetailNullException();
        }
        try {
            PersonEntity personEntity = findByHkid(personDomainObject.getHkid());
            personEntity.setFirstName(personDomainObject.getFirstName());
            personEntity.setLastName(personDomainObject.getLastName());
            return new PersonDetailData(personRepository.save(personEntity));
        }
        catch(PersonNotFoundException ex) {
            logger.warn("Update Person API: Person Not Found " + personDomainObject.getHkid());
            throw ex;
        }
    }

    @Override
    public PersonDetailData deletePerson(String hkid) {
        try {
            PersonEntity personEntity = findByHkid(hkid);
            personRepository.delete(personEntity);
            return new PersonDetailData(personEntity);
        }
        catch(PersonNotFoundException ex) {
            logger.warn("Update Person API: Person Not Found " + hkid);
            throw ex;
        }
    }

    @Override
    public List<PersonDetailData> findByLastName(String lastName) {
        List<PersonDetailData> personDetailDataList = new ArrayList<>();
        for(PersonEntity personEntity : personRepository.findPersonByLastName(lastName)) {
            personDetailDataList.add(new PersonDetailData(personEntity));
        }
        return personDetailDataList;
    }

    @Override
    public PersonDetailData getTeacherCourse(String hkid) {
        PersonEntity teacher = findByHkid(hkid);
        PersonDetailData personDetailData = new PersonDetailData(teacher);
        personDetailData.setTeacherCourse(teacher.getTeacherCourse());
        return personDetailData;
    }

    @Override
    public PersonEntity findByHkid(String hkid) {
        PersonEntity personEntity = personRepository.findByHkid(hkid);
        if(personEntity == null) {
            throw new PersonNotFoundException();
        }
        return personEntity;
    }
}
