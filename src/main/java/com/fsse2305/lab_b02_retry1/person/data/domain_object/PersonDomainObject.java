package com.fsse2305.lab_b02_retry1.person.data.domain_object;

import com.fsse2305.lab_b02_retry1.person.data.dto.PersonDetailRequestDto;

public class PersonDomainObject {
    private String firstName;
    private String lastName;
    private String hkid;

    public PersonDomainObject(PersonDetailRequestDto personDetailRequestDto) {
        this.firstName = personDetailRequestDto.getFirstName();
        this.lastName = personDetailRequestDto.getLastName();
        this.hkid = personDetailRequestDto.getHkid();
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
