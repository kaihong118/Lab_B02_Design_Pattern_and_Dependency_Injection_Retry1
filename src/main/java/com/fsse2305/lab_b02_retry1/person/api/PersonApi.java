package com.fsse2305.lab_b02_retry1.person.api;

import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDetailData;
import com.fsse2305.lab_b02_retry1.person.data.domain_object.PersonDomainObject;
import com.fsse2305.lab_b02_retry1.person.data.dto.PersonDetailRequestDto;
import com.fsse2305.lab_b02_retry1.person.data.dto.PersonDetailResponseDto;
import com.fsse2305.lab_b02_retry1.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonApi {
    private PersonService personService;

    @Autowired
    public PersonApi(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public PersonDetailResponseDto createPerson(@RequestBody PersonDetailRequestDto personDetailRequestDto) {
        PersonDomainObject personDomainObject = new PersonDomainObject(personDetailRequestDto);
        return new PersonDetailResponseDto(personService.createPerson(personDomainObject));
    }

    @GetMapping()
    public List<PersonDetailResponseDto> getAllPerson() {
        List<PersonDetailResponseDto> personDetailResponseDtoList = new ArrayList<>();
        for(PersonDetailData personDetailData : personService.getAllPerson()) {
            personDetailResponseDtoList.add(new PersonDetailResponseDto(personDetailData));
        }
        return personDetailResponseDtoList;
    }

    @PutMapping()
    public PersonDetailResponseDto updatePerson(@RequestBody PersonDetailRequestDto personDetailRequestDto) {
        PersonDomainObject personDomainObject = new PersonDomainObject(personDetailRequestDto);
        return new PersonDetailResponseDto(personService.updatePerson(personDomainObject));
    }

    @DeleteMapping("/{hkid_number}")
    public PersonDetailResponseDto deletePerson(@PathVariable (value = "hkid_number") String hkid) {
        return new PersonDetailResponseDto(personService.deletePerson(hkid));
    }

    @GetMapping("/{last_name}")
    public List<PersonDetailResponseDto> findPersonByLastName(@PathVariable (value = "last_name") String lastName) {
        List<PersonDetailResponseDto> personDetailResponseDtoList = new ArrayList<>();
        for(PersonDetailData personDetailData : personService.findByLastName(lastName)) {
            personDetailResponseDtoList.add(new PersonDetailResponseDto(personDetailData));
        }
        return personDetailResponseDtoList;
    }
}
