package com.fsse2305.lab_b02_retry1.person.repository;

import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    boolean existsByHkid(String hkid);

    PersonEntity findByHkid(String hkid);

    List<PersonEntity> findPersonByLastName(String lastName);
}
