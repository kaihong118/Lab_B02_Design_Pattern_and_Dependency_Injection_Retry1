package com.fsse2305.lab_b02_retry1.person.data.domain_object;

import com.fsse2305.lab_b02_retry1.person.data.entity.PersonEntity;

public class PersonDetailData {
    private String firstName;
    private String lastName;
    private String hkid;

    public PersonDetailData(PersonEntity personEntity) {
        this.firstName = personEntity.getFirstName();
        this.lastName = personEntity.getLastName();
        this.hkid = personEntity.getHkid();
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
