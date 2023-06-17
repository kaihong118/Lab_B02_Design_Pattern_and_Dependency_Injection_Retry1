package com.fsse2305.lab_b02_retry1.person.service;

import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDomainObject;
import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    PersonDetailData createPerson(PersonDomainObject personDomainObject);

    List<PersonDetailData> getAllPerson();

    PersonDetailData updatePerson(PersonDomainObject personDomainObject);

    PersonDetailData deletePerson(String hkid);

    List<PersonDetailData> findByLastName(String lastName);

    PersonEntity findByHkid(String hkid);
}
